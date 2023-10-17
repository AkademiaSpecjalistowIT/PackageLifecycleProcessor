package pl.akademiaspecjalistowit.PaymentService.payment.service;

import java.util.UUID;

public interface PaymentService {
    void confirmPayment(UUID packageId);
}
