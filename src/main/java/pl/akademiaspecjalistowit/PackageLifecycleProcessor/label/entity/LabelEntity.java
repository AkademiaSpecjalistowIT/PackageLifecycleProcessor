package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PackageSize;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PaymentStatus;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID packageId;

    private PackageSize packageSize;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    @JoinColumn(name ="sender_id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private UserEntity sender;

    private PaymentStatus paymentStatus;


}
