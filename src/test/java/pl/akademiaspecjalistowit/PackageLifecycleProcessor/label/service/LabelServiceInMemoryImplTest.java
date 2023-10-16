package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.exception.PaymentRequiredException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.AddressDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.UserDto;

class LabelServiceInMemoryImplTest {

    private LabelService labelService;

    @BeforeEach
    void setUp() {
        labelService = new LabelServiceInMemoryImpl();
    }

    @Test
    public void label_should_not_be_available_if_payment_is_not_complete() {

        //given
        LabelDto labelDto = prepareValidLabelDto();
        UUID packageId = labelService.registerPackage(labelDto);

        //when
        Executable e = () -> labelService.getPackageLabel(packageId);

        //then
        assertThrows(PaymentRequiredException.class, e);
    }

    private LabelDto prepareValidLabelDto() {
        UUID packageId = UUID.randomUUID();
        String packageSize = "M";
        AddressDto from = new AddressDto("Lodz", "91-600", "ul. Piotrkowska 256b");
        AddressDto to = new AddressDto("Warszawa", "00-100", "ul. Marszałkowska 10/34");
        UserDto sender = new UserDto(from, "444-444-444", "adam@example.com");
        UserDto receiver = new UserDto(to, "555-555-555", "olek@example.com");
        return new LabelDto(packageId, packageSize, receiver, sender);
    }

}