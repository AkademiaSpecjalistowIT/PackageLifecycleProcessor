package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressDto {
    private String city;
    private String postalCode;
    private String addressLine;
}
