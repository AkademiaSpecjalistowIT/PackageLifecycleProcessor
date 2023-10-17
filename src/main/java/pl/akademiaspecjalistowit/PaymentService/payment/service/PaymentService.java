package pl.akademiaspecjalistowit.PaymentService.payment.service;

import java.util.UUID;
import pl.akademiaspecjalistowit.PaymentService.payment.dto.PaymentRequestDto;

public interface PaymentService {
    void confirmPayment(UUID packageId, UUID userId);

    void registerPayment(PaymentRequestDto paymentRequestDto);
}
