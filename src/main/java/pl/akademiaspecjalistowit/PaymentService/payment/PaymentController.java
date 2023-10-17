package pl.akademiaspecjalistowit.PaymentService.payment;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.PaymentService.payment.dto.PaymentRequestDto;
import pl.akademiaspecjalistowit.PaymentService.payment.service.PaymentServiceInMemoryImpl;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

    private PaymentServiceInMemoryImpl paymentService;

    @PostMapping("/confirm/{packageId}/{userId}")
    public void confirmPayment(@PathVariable UUID packageId,
                               @PathVariable UUID userId) {
        paymentService.confirmPayment(packageId, userId);
    }

    @PostMapping("/register")
    public void registerPayment(@RequestBody PaymentRequestDto paymentRequest) {
        paymentService.registerPayment(paymentRequest);
    }
}
