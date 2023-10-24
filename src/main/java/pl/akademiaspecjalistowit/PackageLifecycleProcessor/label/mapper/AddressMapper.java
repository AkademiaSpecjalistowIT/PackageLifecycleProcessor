package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.AddressDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity.AddressEntity;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Address;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressEntity toEntity(Address address);

    Address fromEntity(AddressEntity addressEntity);

    AddressDto toDto(Address address);

    Address fromDto(AddressDto addressDto);

}
