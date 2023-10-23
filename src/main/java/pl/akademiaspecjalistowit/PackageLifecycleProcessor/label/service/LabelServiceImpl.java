package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelInput;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper.LabelMapper;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.payment.PaymentService;

@Service
@AllArgsConstructor
public class LabelServiceImpl implements LabelService {

    private  LabelDataService labelDataService;
    private  PaymentService paymentService;

    @Override
    public LabelDto getPackageLabel(UUID packageId) {
        return LabelMapper.INSTANCE.toDto(labelDataService.getLabel(packageId));
    }

    @Override
    public UUID registerPackage(LabelInput labelInput) {
        //todo dtoValidation
        UUID packageId = UUID.randomUUID();
        Label label = LabelMapper.INSTANCE.fromInput(labelInput, packageId);
        paymentService.registerPaymentCommitment();
        labelDataService.save(label);
        return null;
    }
}
