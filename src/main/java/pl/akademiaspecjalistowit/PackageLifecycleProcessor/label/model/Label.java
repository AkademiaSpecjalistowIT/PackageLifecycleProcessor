package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Label {
    private UUID packageId;
    private String packageSize;
    private User receiver;
    private User sender;
    private PaymentStatus paymentStatus;

    public Label(UUID packageId,
                 String packageSize,
                 User receiver,
                 User sender) {
        this.packageId = packageId;
        this.packageSize = packageSize;
        this.receiver = receiver;
        this.sender = sender;
        this.paymentStatus = PaymentStatus.PENDING;
    }

}
