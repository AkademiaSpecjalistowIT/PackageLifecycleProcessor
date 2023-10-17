package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.exception.PaymentRequiredException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.LabelNotFoundException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper.LabelMapper;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PaymentStatus;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.repository.LabelRepository;

@Service
public class LabelServiceInMemoryImpl implements LabelService {

    private LabelRepository labelRepository;

    public LabelServiceInMemoryImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public LabelDto getPackageLabel(UUID packageId) {
        Label label = labelRepository.findByPackageId(packageId)
            .orElseThrow(LabelNotFoundException::new);

        if (!PaymentStatus.COMPLETED.equals(label.getPaymentStatus())) {
            throw new PaymentRequiredException("Payment for this label is not COMPLETE yet.");
        }

        return LabelMapper.toDto(label);
    }

    @Override
    public UUID registerPackage(LabelDto labelDto) {
        UUID packageId = UUID.randomUUID();
        Label label = LabelMapper.fromDto(labelDto, packageId);
        labelRepository.save(label);
        return packageId;
    }
}
