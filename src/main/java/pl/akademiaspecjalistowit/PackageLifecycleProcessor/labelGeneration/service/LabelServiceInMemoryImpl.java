package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.exception.NotImplementedException;

@Service
public class LabelServiceInMemoryImpl implements LabelService {

    private Map<UUID, LabelDto> labelDtoMap;


    public LabelServiceInMemoryImpl() {
        this.labelDtoMap = Map.of();
    }

    @Override
    public Optional<LabelDto> getPackageLabel(UUID packageId) {
        throw new NotImplementedException("GetLabel feature not implemented");
    }

    @Override
    public UUID registerPackage(LabelDto labelDto) {
        throw new NotImplementedException("registerPackage feature not implemented");
    }
}
