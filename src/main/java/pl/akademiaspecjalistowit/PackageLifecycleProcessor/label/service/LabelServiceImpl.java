package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelInput;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.exception.LabelNotFoundException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.exception.PaymentNotSettledException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper.LabelMapper;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PaymentStatus;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.payment.PaymentService;

@Service
@AllArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelDataService labelDataService;
    private final PaymentService paymentService;
    private final LabelPricingService labelPricingService;

    @Override
    public LabelDto getPackageLabel(UUID packageId) {
        Label label = labelDataService.getLabel(packageId)
            .orElseThrow(LabelNotFoundException::new);
        verifyThatPackageIsPaid(label);
        return LabelMapper.INSTANCE.toDto(label);
    }

    @Override
    public UUID registerPackage(LabelInput labelInput) {
        //todo dtoValidation before save inside Label Object.
        Label label = saveLabel(labelInput);
        BigDecimal priceForPackage = labelPricingService.calculatePriceForPackage(label);
        paymentService.registerPaymentCommitment(label.getPackageId(), priceForPackage);
        labelDataService.save(label);
        return label.getPackageId();
    }

    @Override
    @Transactional
    public void updatePackagePaymentStatus(UUID packageId) {
        Label label = labelDataService.getLabel(packageId)
            .map(Label::updatePaymentStatusCompleted)
            .orElseThrow(LabelNotFoundException::new);
        labelDataService.update(label);
    }

    private void verifyThatPackageIsPaid(Label label) {
        if (!PaymentStatus.COMPLETED.equals(label.getPaymentStatus())) {
            throw new PaymentNotSettledException();
        }
    }

    private Label saveLabel(LabelInput labelInput) {
        UUID packageId = UUID.randomUUID();
        Label label = LabelMapper.INSTANCE.fromInput(labelInput, packageId);
        return label;
    }
}
