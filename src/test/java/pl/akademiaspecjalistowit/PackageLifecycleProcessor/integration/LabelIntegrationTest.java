package pl.akademiaspecjalistowit.PackageLifecycleProcessor.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.AddressDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.LabelInput;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto.UserDto;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.entity.LabelEntity;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PackageSize;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PaymentStatus;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.repository.LabelRepository;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service.LabelPricingService;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service.LabelService;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.payment.PaymentService;

@SpringBootTest
public class LabelIntegrationTest {

    @Autowired
    private LabelService labelServiceSuT;

    @Autowired
    private LabelRepository labelRepository;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void should_register_medium_package_successfully() {
        //given
        String priceForMediumPackage = LabelPricingService.MEDIUM_PACKAGE_PRICE;
        LabelInput labelInput = prepareValidLabelInput();

        //when
        UUID uuid = labelServiceSuT.registerPackage(labelInput);

        //then
        LabelEntity labelEntity = labelIsSavedInTheDataBase(uuid);
        statusIsPending(labelEntity);
        labelInputValuesMathesThoseSavedInTheDataBase(labelInput, labelEntity);

        //and
        appropiateAmountWasChargedForMediumPackage(priceForMediumPackage, uuid);
    }

    private void appropiateAmountWasChargedForMediumPackage(String priceForMediumPackage, UUID uuid) {
        Mockito.verify(paymentService).registerPaymentCommitment(uuid, new BigDecimal(priceForMediumPackage));
    }

    private void statusIsPending(LabelEntity labelEntity) {
        assertThat(labelEntity.getPaymentStatus()).isEqualTo(PaymentStatus.PENDING);
    }

    private void labelInputValuesMathesThoseSavedInTheDataBase(LabelInput labelInput, LabelEntity labelEntity) {
        assertThat(labelEntity.getPackageSize()).isEqualTo(labelInput.getPackageSize());

        assertThat(labelEntity.getReceiver().getEmail()).isEqualTo(labelInput.getReceiver().getEmail());
        assertThat(labelEntity.getReceiver().getPhoneNumber()).isEqualTo(labelInput.getReceiver().getPhoneNumber());
        assertThat(labelEntity.getReceiver().getAddress().getAddressLine())
            .isEqualTo(labelInput.getReceiver().getAddress().getAddressLine());
        assertThat(labelEntity.getReceiver().getAddress().getCity())
            .isEqualTo(labelInput.getReceiver().getAddress().getCity());
        assertThat(labelEntity.getReceiver().getAddress().getPostalCode())
            .isEqualTo(labelInput.getReceiver().getAddress().getPostalCode());

        assertThat(labelEntity.getSender().getEmail()).isEqualTo(labelInput.getSender().getEmail());
        assertThat(labelEntity.getSender().getPhoneNumber()).isEqualTo(labelInput.getSender().getPhoneNumber());
        assertThat(labelEntity.getSender().getAddress().getAddressLine())
            .isEqualTo(labelInput.getSender().getAddress().getAddressLine());
        assertThat(labelEntity.getSender().getAddress().getCity())
            .isEqualTo(labelInput.getSender().getAddress().getCity());
        assertThat(labelEntity.getSender().getAddress().getPostalCode())
            .isEqualTo(labelInput.getSender().getAddress().getPostalCode());
    }

    private LabelEntity labelIsSavedInTheDataBase(UUID uuid) {
        LabelEntity labelEntity = labelRepository.findByPackageId(uuid).get();
        assertThat(labelEntity).isNotNull();
        return labelEntity;
    }

    private LabelInput prepareValidLabelInput() {
        PackageSize packageSize = PackageSize.MEDIUM;
        AddressDto from = new AddressDto("Lodz", "91-600", "ul. Piotrkowska 256b");
        AddressDto to = new AddressDto("Warszawa", "00-100", "ul. Marszałkowska 10/34");
        UserDto sender = new UserDto(from, "444-444-444", "adam@example.com");
        UserDto receiver = new UserDto(to, "555-555-555", "olek@example.com");
        return new LabelInput(packageSize, receiver, sender);
    }
}
