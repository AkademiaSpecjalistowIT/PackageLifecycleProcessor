package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.model;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Label {
    private UUID packageId;
    private String packageSize;
    private User receiver;
    private User sender;

    public Label(UUID packageId,
                 String packageSize,
                 User receiver,
                 User sender) {
        this.packageId = packageId;
        this.packageSize = packageSize;
        this.receiver = receiver;
        this.sender = sender;
    }

}
