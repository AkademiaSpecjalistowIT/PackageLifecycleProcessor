package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PaymentStatus;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID packageId;

    private String packageSize;

    @OneToOne
    private UserEntity receiver;

    @OneToOne
    private UserEntity sender;

    private PaymentStatus paymentStatus;


}
