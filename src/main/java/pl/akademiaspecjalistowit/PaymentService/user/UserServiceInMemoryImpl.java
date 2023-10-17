package pl.akademiaspecjalistowit.PaymentService.user;

import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserServiceInMemoryImpl implements UserService {
    @Override
    public boolean debitCustomerAccount(UUID packageId, UUID userId) {
        return true;
    }
}
