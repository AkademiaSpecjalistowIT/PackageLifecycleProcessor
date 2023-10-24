package pl.akademiaspecjalistowit.PackageLifecycleProcessor.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.exception.RegisterPaymentException;

@Service
public class PaymentServiceRestImpl implements PaymentService {

    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private String paymentServiceUrl;
    private String paymentServiceRegisterPaymentPath;

    public PaymentServiceRestImpl(HttpClient httpClient,
                                  ObjectMapper objectMapper,
                                  @Value("${prop.http.payment-service.url}")
                                      String paymentServiceUrl,
                                  @Value("${prop.http.payment-service.register-payment-path}")
                                      String paymentServiceRegisterPaymentPath) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.paymentServiceUrl = paymentServiceUrl;
        this.paymentServiceRegisterPaymentPath = paymentServiceRegisterPaymentPath;
    }

    public void registerPaymentCommitment(UUID packageId, BigDecimal amount) {

        try {
            String paymentRequestDtoSerialized = preparePaymentRequest(packageId, amount);

            HttpRequest confirmPaymentRequest = HttpRequest.newBuilder()
                .uri(URI.create(paymentServiceUrl + paymentServiceRegisterPaymentPath))
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(paymentRequestDtoSerialized))
                .build();

            HttpResponse<Void> response =
                httpClient.send(confirmPaymentRequest, HttpResponse.BodyHandlers.discarding());

            if (HttpStatus.OK.value() != response.statusCode()) {
                throw new RegisterPaymentException("Payment is not registered");
            }

        } catch (Exception e) {
            throw new RegisterPaymentException("Technical issue occurred", e);
        }

    }

    private String preparePaymentRequest(UUID packageId, BigDecimal amount) throws JsonProcessingException {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(packageId, amount);
        String paymentRequestDtoSerialized = objectMapper.writeValueAsString(paymentRequestDto);
        return paymentRequestDtoSerialized;
    }
}
