package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.exception.PaymentRequiredException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.AddressDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.UserDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper.LabelMapper;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.repository.LabelRepository;

class LabelServiceInMemoryImplTest {

    private LabelService labelServiceSuT;
    private LabelRepository labelRepositoryMock;

    @BeforeEach
    void setUp() {
        labelRepositoryMock = Mockito.mock(LabelRepository.class);
        labelServiceSuT = new LabelServiceInMemoryImpl(labelRepositoryMock);
    }

    @Test
    public void label_should_not_be_available_if_payment_is_not_complete() {

        //given
        LabelDto labelDto = prepareValidLabelDto();
        UUID packageId = labelServiceSuT.registerPackage(labelDto);
        Mockito.when(labelRepositoryMock.findByPackageId(packageId))
            .thenReturn(Optional.of(prepareValidLabelWithPaymentInStatusPending(packageId)));

        //when
        Executable e = () -> labelServiceSuT.getPackageLabel(packageId);

        //then
        assertThrows(PaymentRequiredException.class, e);
    }

    @Test
    public void should_return_package_label_data_when_payment_is_completed() {

        //given
        LabelDto labelDto = prepareValidLabelDto();
        UUID packageId = labelServiceSuT.registerPackage(labelDto);
        Mockito.when(labelRepositoryMock.findByPackageId(packageId))
            .thenReturn(Optional.of(prepareValidLabelWithPaymentInStatusCompleted(packageId)));

        //when
        LabelDto packageLabel = labelServiceSuT.getPackageLabel(packageId);

        //then
//        packageLabel todo asserts
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

    private Label prepareValidLabelWithPaymentInStatusPending(UUID packageId){
        LabelDto labelDto = prepareValidLabelDto();
        Label label = LabelMapper.fromDto(labelDto, packageId);
        return label;
    }

    private Label prepareValidLabelWithPaymentInStatusCompleted(UUID packageId){
        Label label = prepareValidLabelWithPaymentInStatusPending(packageId);
        label.updatePaymentStatusCompleted();
        return label;
    }

}