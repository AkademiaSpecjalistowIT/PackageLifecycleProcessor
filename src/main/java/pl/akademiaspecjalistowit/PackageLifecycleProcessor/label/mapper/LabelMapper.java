package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelInput;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity.LabelEntity;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;

@Mapper
public interface LabelMapper {

    LabelMapper INSTANCE = Mappers.getMapper(LabelMapper.class);

    LabelEntity toEntity(Label label);

    Label fromEntity(LabelEntity labelEntity);

    LabelDto toDto(Label label);

    Label fromInput(LabelInput labelInput, UUID packageId);
}
