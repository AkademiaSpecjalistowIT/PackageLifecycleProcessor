package pl.akademiaspecjalistowit.PaymentService.payment.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceInMemoryImpl implements PaymentService {

    private final Map<UUID, Boolean> activatedPayments;

    public PaymentServiceInMemoryImpl() {
        activatedPayments = new HashMap<>();
    }

    public void confirmPayment(UUID packageId) {
        if (!activatedPayments.containsKey(packageId)) {
            throw new PaymentServiceException(
                "Cannot correlate payment confirmation for id: " + packageId);
        }
        activatedPayments.put(packageId, true);
        log.info("Payment confirmed for package {}", packageId);
    }
}
