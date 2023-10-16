package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {
    private String city;
    private String postalCode;
    private String addressLine;
}
