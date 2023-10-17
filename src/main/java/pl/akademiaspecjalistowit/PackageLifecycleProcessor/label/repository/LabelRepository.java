package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.repository;

import java.util.Optional;
import java.util.UUID;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;

public interface LabelRepository {

    void save(Label label);

    Optional<Label> findByPackageId(UUID packageId);
}
