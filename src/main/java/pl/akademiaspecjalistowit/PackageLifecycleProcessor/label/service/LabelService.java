package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import java.util.Optional;
import java.util.UUID;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;

public interface LabelService {

    Optional<LabelDto> getPackageLabel(UUID packageId);

    UUID registerPackage(LabelDto labelDto);
}
