package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.exception.PaymentRequiredException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.LabelNotFoundException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper.LabelMapper;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PaymentStatus;

@Service
public class LabelServiceInMemoryImpl implements LabelService {

    private Map<UUID, Label> labelMap;

    public LabelServiceInMemoryImpl() {
        this.labelMap = new HashMap<>();
    }

    @Override
    public LabelDto getPackageLabel(UUID packageId) {
        Label label = Optional.ofNullable(labelMap.get(packageId))
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
        labelMap.put(packageId, label);
        return packageId;
    }
}
