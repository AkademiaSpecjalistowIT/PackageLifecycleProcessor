package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.UserDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity.UserEntity;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(User user);

    User fromEntity(UserEntity entity);

    UserDto toDto(User user);

    User fromDto(UserDto user);
}
