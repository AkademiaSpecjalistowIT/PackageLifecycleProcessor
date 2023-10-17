package pl.akademiaspecjalistowit.PaymentService.user;

import java.util.UUID;

public interface UserService {
    boolean debitCustomerAccount(UUID packageId, UUID userId);
}
