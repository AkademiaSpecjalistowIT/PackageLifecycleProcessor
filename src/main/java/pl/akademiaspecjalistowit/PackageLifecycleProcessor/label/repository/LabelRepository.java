package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity.LabelEntity;

@Repository
public interface LabelRepository extends JpaRepository<LabelEntity, Long> {

    Optional<LabelEntity> findByPackageId(UUID packageId);
}
