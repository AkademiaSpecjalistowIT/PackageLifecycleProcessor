package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity.LabelEntity;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper.LabelMapper;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.repository.LabelRepository;

@Service
@AllArgsConstructor
public class LabelDataService {

    private LabelRepository labelRepository;

    public void save(Label label){
        LabelEntity labelEntity = LabelMapper.INSTANCE.toEntity(label);
        labelRepository.save(labelEntity);
    }

    public Optional<Label> getLabel(UUID packageId){
        return labelRepository.findByPackageId(packageId)
            .map(LabelMapper.INSTANCE::fromEntity);
    }

    public void update(Label label) {
        labelRepository.save(LabelMapper.INSTANCE.toEntity(label));
    }
}
