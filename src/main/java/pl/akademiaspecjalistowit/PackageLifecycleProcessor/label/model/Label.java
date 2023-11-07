package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Label {
    private Long id;
    private UUID packageId;
    private PackageSize packageSize;
    private User receiver;
    private User sender;
    private PaymentStatus paymentStatus;

    @Default
    public Label(UUID packageId, PackageSize packageSize,
                 User receiver, User sender) {
        this.packageId = packageId;
        this.packageSize = packageSize;
        this.receiver = receiver;
        this.sender = sender;
        this.paymentStatus = PaymentStatus.PENDING;
    }


    public Label updatePaymentStatusCompleted() {
        this.paymentStatus = PaymentStatus.COMPLETED;
        return this;
    }

}
