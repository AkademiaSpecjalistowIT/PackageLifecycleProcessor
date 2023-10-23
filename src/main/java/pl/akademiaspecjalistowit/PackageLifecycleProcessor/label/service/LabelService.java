package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import java.util.UUID;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelInput;

public interface LabelService {

    LabelDto getPackageLabel(UUID packageId);

    UUID registerPackage(LabelInput labelInput);
}
