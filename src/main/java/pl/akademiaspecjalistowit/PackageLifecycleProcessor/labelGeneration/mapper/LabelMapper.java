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

    public static LabelDto toDto(Label label) {
        return new LabelDto(label.getPackageId(),
            label.getPackageSize(),
            toDto(label.getReceiver()),
            toDto(label.getSender()));
    }

    private static UserDto toDto(User user) {
        return new UserDto(toDto(user.getAddress()), user.getPhoneNumber(), user.getEmail());
    }

    private static AddressDto toDto(Address address) {
        return new AddressDto(address.getCity(), address.getPostalCode(), address.getAddressLine());
    }

}
