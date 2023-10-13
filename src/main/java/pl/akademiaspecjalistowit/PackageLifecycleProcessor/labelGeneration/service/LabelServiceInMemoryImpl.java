package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.mapper.LabelMapper;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.model.Label;

@Service
public class LabelServiceInMemoryImpl implements LabelService {

    private Map<UUID, Label> labelMap;

    public LabelServiceInMemoryImpl() {
        this.labelMap = new HashMap<>();
    }

    @Override
    public Optional<LabelDto> getPackageLabel(UUID packageId) {
        return Optional.ofNullable(labelMap.get(packageId))
            .map(LabelMapper::toDto);
    }

    @Override
    public UUID registerPackage(LabelDto labelDto) {
        UUID packageId = UUID.randomUUID();
        Label label = LabelMapper.fromDto(labelDto, packageId);
        labelMap.put(packageId, label);
        return packageId;
    }
}
