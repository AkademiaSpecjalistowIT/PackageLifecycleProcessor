package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.AddressDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.UserDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.service.LabelService;

@RestController
@RequestMapping("/package")
@AllArgsConstructor
public class PackageController {

    private LabelService labelService;

    @GetMapping("/label/{packageId}")
    public LabelDto getPackageLabel(@PathVariable UUID packageId) {

        AddressDto address = new AddressDto(
            "Łódź",
            "91-610",
            "Piotrkowska 123");

        return new LabelDto(packageId,
            "M",
            new UserDto(address, "444-444-444", "asdf@mail.com"),
            new UserDto(address, "333-333-444", "qwer@mail.com"));
    }


    @PostMapping("/label")
    public UUID registerPackage(@RequestBody LabelDto labelDto) {
        return labelService.registerPackage(labelDto);
    }
}
