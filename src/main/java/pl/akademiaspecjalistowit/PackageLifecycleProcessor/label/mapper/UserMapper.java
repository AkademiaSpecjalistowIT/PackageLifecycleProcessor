package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.UserDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity.UserEntity;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "address", target = "address")
    UserEntity toEntity(User user);

    @Mapping(source = "address", target = "address")
    User fromEntity(UserEntity entity);

    @Mapping(source = "address", target = "address")
    UserDto toDto(User user);

    @Mapping(source = "address", target = "address")
    User fromDto(UserDto user);
}
