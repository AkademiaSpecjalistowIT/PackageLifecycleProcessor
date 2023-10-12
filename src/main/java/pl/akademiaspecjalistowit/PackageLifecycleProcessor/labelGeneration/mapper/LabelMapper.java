package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.mapper;

import java.util.UUID;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.AddressDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.UserDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.model.Address;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.model.User;

public class LabelMapper {

    public static Label fromDto(LabelDto labelDto, UUID packageId) {
        return new Label(packageId,
            labelDto.getPackageSize(),
            fromDto(labelDto.getReceiver()),
            fromDto(labelDto.getSender()));
    }

    private static User fromDto(UserDto userDto) {
        return new User(fromDto(userDto.getAddress()), userDto.getPhoneNumber(), userDto.getEmail());
    }

    private static Address fromDto(AddressDto addressDto) {
        return new Address(addressDto.getCity(), addressDto.getPostalCode(), addressDto.getAddressLine());
    }
}
