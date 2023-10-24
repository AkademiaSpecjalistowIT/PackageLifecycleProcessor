package pl.akademiaspecjalistowit.PackageLifecycleProcessor.payment;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentService {
    void registerPaymentCommitment(UUID packageId, BigDecimal amount);
}
