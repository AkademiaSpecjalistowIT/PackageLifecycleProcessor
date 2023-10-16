package pl.akademiaspecjalistowit.PackageLifecycleProcessor.exception;

public class PaymentRequiredException extends RuntimeException {
    public PaymentRequiredException(String message) {
        super(message);
    }
}
