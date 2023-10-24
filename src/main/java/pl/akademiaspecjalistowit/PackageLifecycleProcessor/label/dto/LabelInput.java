package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PackageSize;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class LabelInput {
    private PackageSize packageSize;
    private UserDto receiver;
    private UserDto sender;

    public LabelInput(PackageSize packageSize,
                      UserDto receiver,
                      UserDto sender) {
        this.packageSize = packageSize;
        this.receiver = receiver;
        this.sender = sender;
    }

}
