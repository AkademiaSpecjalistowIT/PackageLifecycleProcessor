package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {
    private String city;
    private String postalCode;
    private String addressLine;
}
