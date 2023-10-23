package pl.akademiaspecjalistowit.PackageLifecycleProcessor.payment;

import java.net.http.HttpClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceRestImpl implements PaymentService {

//    private final HttpClient httpClient;

    @Override
    public void registerPaymentCommitment() {

    }
}
