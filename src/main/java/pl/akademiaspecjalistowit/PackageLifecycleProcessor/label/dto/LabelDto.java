package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LabelDto {
    private UUID packageId;
    private String packageSize;
    private UserDto receiver;
    private UserDto sender;
}
