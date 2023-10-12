package pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.Address;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.labelGeneration.dto.UserDto;

@RestController
@RequestMapping("/package")
public class PackageController {

    @GetMapping("/label/{packageId}")
    public LabelDto getPackageLabel(@PathVariable UUID packageId) {

        Address address = new Address(
            "Łódź",
            "91-610",
            "Piotrkowska 123");

        return new LabelDto(packageId,
            "M",
            new UserDto(address, "444-444-444", "asdf@mail.com"),
            new UserDto(address, "333-333-444", "qwer@mail.com"));
    }

    //TODO create method for registering label.
}
