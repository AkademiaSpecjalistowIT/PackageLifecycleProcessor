package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private AddressDto address;
    private String phoneNumber;
    private String email;
}