package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class LabelDto {
    private UUID packageId;
    private String packageSize;
    private UserDto receiver;
    private UserDto sender;

    public LabelDto(UUID packageId,
                    String packageSize,
                    UserDto receiver,
                    UserDto sender) {
        this.packageId = packageId;
        this.packageSize = packageSize;
        this.receiver = receiver;
        this.sender = sender;
    }

}
