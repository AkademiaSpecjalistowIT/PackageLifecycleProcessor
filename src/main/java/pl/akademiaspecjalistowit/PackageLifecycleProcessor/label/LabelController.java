package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service.LabelService;

@RestController
@RequestMapping("/package")
@AllArgsConstructor
public class LabelController {

    private LabelService labelService;

    @GetMapping("/label/{packageId}")
    public LabelDto getPackageLabel(@PathVariable UUID packageId) {
       return  labelService.getPackageLabel(packageId)
            .orElseThrow(LabelNotFoundException::new);
    }

    @PostMapping("/label")
    public UUID registerPackage(@RequestBody LabelDto labelDto) {
        return labelService.registerPackage(labelDto);
    }
}
