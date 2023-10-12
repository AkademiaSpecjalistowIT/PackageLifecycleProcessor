package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.service;

import java.util.Optional;
import java.util.UUID;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.LabelDto;

public interface LabelService {

    Optional<LabelDto> getPackageLabel(UUID packageId);

    UUID registerPackage(LabelDto labelDto);
}
