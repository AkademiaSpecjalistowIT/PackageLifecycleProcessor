package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PackageSize;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class LabelDto {
    private UUID packageId;
    private PackageSize packageSize;
    private UserDto receiver;
    private UserDto sender;
}
