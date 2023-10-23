package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelInput;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.UserDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity.LabelEntity;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.mapper.LabelMapper;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.repository.LabelRepository;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.payment.PaymentService;

class LabelServiceTest {

    private LabelService labelServiceSuT;
    private LabelRepository labelRepositoryMock;


    @BeforeEach
    void setUp() {
        labelRepositoryMock = Mockito.mock(LabelRepository.class);
        PaymentService paymentService = Mockito.mock(PaymentService.class);
        LabelDataService labelDataService = new LabelDataService(labelRepositoryMock);
        labelServiceSuT = new LabelServiceImpl(labelDataService, paymentService);
    }

    @Test
    public void label_should_not_be_available_if_payment_is_not_complete() {

        //given
        LabelInput labelInput = prepareValidLabelInput();
        UUID packageId = labelServiceSuT.registerPackage(labelInput);
        Mockito.when(labelRepositoryMock.findByPackageId(packageId))
            .thenReturn(Optional.of(prepareValidLabelEntityWithPaymentInStatusPending(packageId)));

        //when
        Executable e = () -> labelServiceSuT.getPackageLabel(packageId);

        //then
        assertThrows(PaymentRequiredException.class, e);
    }

    @Test
    public void should_return_package_label_data_when_payment_is_completed() {

        //given
        LabelInput labelInput = prepareValidLabelInput();
        UUID packageId = labelServiceSuT.registerPackage(labelInput);
        Mockito.when(labelRepositoryMock.findByPackageId(packageId))
            .thenReturn(Optional.of(prepareValidLabelEntityWithPaymentInStatusCompleted(packageId)));

        //when
        LabelDto packageLabel = labelServiceSuT.getPackageLabel(packageId);

        //then
        assertThat(packageLabel).isNotNull();
        assertThat(packageLabel.getPackageId()).isEqualTo(packageId);
        assertThat(packageLabel.getReceiver()).isEqualTo(labelInput.getReceiver());
        assertThat(packageLabel.getSender()).isEqualTo(labelInput.getSender());
        assertThat(packageLabel.getPackageSize()).isEqualTo(labelInput.getPackageSize());
    }

    private LabelInput prepareValidLabelInput() {
        String packageSize = "M";
        AddressDto from = new AddressDto("Lodz", "91-600", "ul. Piotrkowska 256b");
        AddressDto to = new AddressDto("Warszawa", "00-100", "ul. Marszałkowska 10/34");
        UserDto sender = new UserDto(from, "444-444-444", "adam@example.com");
        UserDto receiver = new UserDto(to, "555-555-555", "olek@example.com");
        return new LabelInput(packageSize, receiver, sender);
    }

    private LabelEntity prepareValidLabelEntityWithPaymentInStatusPending(UUID packageId) {
        LabelInput labelInput = prepareValidLabelInput();
        Label label = LabelMapper.INSTANCE.fromInput(labelInput, packageId);
        return LabelMapper.INSTANCE.toEntity(label);
    }

    private Label prepareValidLabelWithPaymentInStatusPending(UUID packageId) {
        LabelInput labelInput = prepareValidLabelInput();
        return LabelMapper.INSTANCE.fromInput(labelInput, packageId); //todo add missing packageId
    }

    private LabelEntity prepareValidLabelEntityWithPaymentInStatusCompleted(UUID packageId) {
        Label label = prepareValidLabelWithPaymentInStatusPending(packageId);
        label.updatePaymentStatusCompleted();
        return LabelMapper.INSTANCE.toEntity(label);
    }

}