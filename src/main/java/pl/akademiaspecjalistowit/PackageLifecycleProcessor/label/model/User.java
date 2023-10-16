package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private Address address;
    private String phoneNumber;
    private String email;
}
