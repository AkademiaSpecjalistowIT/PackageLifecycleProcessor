package pl.akademiaspecjalistowit.PaymentService.payment.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PaymentService.payment.dto.PaymentRequestDto;
import pl.akademiaspecjalistowit.PaymentService.payment.model.Transaction;
import pl.akademiaspecjalistowit.PaymentService.user.UserService;

@Service
@Slf4j
public class PaymentServiceInMemoryImpl implements PaymentService {

    private final UserService userService;
    private final Set<Transaction> activatedPayments;

    public PaymentServiceInMemoryImpl(UserService userService) {
        this.userService = userService;
        activatedPayments = new HashSet<>();
    }

    public void confirmPayment(UUID packageId, UUID userId) {
        // TODO znaleść buga
        Transaction transaction = activatedPayments.stream()
            .filter(e -> e.getPackageId().equals(packageId))
            .findAny()
            .orElseThrow(() -> new PaymentServiceException(
                "Cannot correlate payment confirmation for id: " + packageId));

        boolean isPaymentSuccessful = chargeUser(transaction.getTransactionId(), userId);
        if (!isPaymentSuccessful) {
            throw new PaymentServiceException("Payment failed. Please chargeUp you wallet");
        }

        transaction.finalizeTransaction();
        activatedPayments.add(transaction);
        log.info("Payment confirmed for package {}", packageId);
    }

    @Override
    public void registerPayment(PaymentRequestDto paymentRequestDto) {
        activatedPayments.add(new Transaction(
            paymentRequestDto.getPackageId(),
            UUID.randomUUID(),
            paymentRequestDto.getAmount()));
    }

    private boolean chargeUser(UUID transacitonId, UUID userId) {
        return userService.debitCustomerAccount(transacitonId, userId);
    }
}
