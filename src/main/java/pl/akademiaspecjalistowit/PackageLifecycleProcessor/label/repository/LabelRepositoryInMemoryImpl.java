package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;

@Repository
public class LabelRepositoryInMemoryImpl implements LabelRepository {

    private Map<UUID, Label> labelMap;

    public LabelRepositoryInMemoryImpl() {
        labelMap = new HashMap<>();
    }

    @Override
    public void save(Label label) {
        labelMap.put(label.getPackageId(), label);
    }

    @Override
    public Optional<Label> findByPackageId(UUID packageId) {
        return Optional.ofNullable(labelMap.get(packageId));
    }
}
