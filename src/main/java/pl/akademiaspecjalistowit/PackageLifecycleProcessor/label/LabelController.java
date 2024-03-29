package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelInput;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service.LabelService;

@Slf4j
@RestController
@RequestMapping("/package")
@AllArgsConstructor
public class LabelController {

    private LabelService labelService;

    @GetMapping("/label/{packageId}")
    public LabelDto getPackageLabel(@PathVariable UUID packageId) {
        return labelService.getPackageLabel(packageId);
    }

    @PostMapping("/label")
    public UUID registerPackage(@RequestBody LabelInput labelInput) {
        return labelService.registerPackage(labelInput);
    }

    @PostMapping("/label/confirm/{packageId}")//todo patch
    public void confirmPackagePayment(@PathVariable UUID packageId) {
        log.info("Payment confirmation for package {} received", packageId);
        labelService.updatePackagePaymentStatus(packageId);
    }
}
