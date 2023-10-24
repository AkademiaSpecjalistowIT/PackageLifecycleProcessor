package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Address address;
    private String phoneNumber;
    private String email;
}
