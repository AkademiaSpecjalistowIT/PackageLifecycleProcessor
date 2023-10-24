package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.exception.LabelNotFoundException;

@Slf4j
@ControllerAdvice
public class LabelControllerAdvice {

    @ExceptionHandler(LabelNotFoundException.class)
    public ResponseEntity<Void> labelNotFoundExceptionHandler(LabelNotFoundException e) {
        log.error("Someone searched for not existing label", e);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
