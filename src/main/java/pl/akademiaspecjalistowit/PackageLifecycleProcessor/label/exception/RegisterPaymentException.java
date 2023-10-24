package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.exception;

public class RegisterPaymentException extends RuntimeException {
    public RegisterPaymentException(String message) {
        super(message);
    }

    public RegisterPaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
