package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.exception.MissingPackagePricingConfigurationException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Address;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PackageSize;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PaymentStatus;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.User;

class LabelPricingServiceTest {

    LabelPricingService labelPricingServiceSuT;

    @BeforeEach
    void setUp() {
        labelPricingServiceSuT = new LabelPricingService();
    }

    @ParameterizedTest
    @MethodSource
    public void should_return_price_adequate_to_package_size(PackageSize packageSize,
                                                             String expectedPrice) {

        //given
        //PackageSize packageSize;
        Label label = prepareValidLabelWithPackageSize(packageSize);

        //when
        BigDecimal priceForPackage = labelPricingServiceSuT.calculatePriceForPackage(label);

        //then
        assertThat(priceForPackage).isEqualTo(expectedPrice);
    }

    @Test
    public void givenUndefinedPackageSizeLabel_whenCalculatePrice_thenThrowsException() {
        // Given
        Label mockLabel = Mockito.mock(Label.class);
        Mockito.when(mockLabel.getPackageSize()).thenReturn(null); // or any undefined size

        // When
        Executable e = () -> labelPricingServiceSuT.calculatePriceForPackage(mockLabel);

        // Then
        assertThrows(MissingPackagePricingConfigurationException.class, e);
    }

    private static Stream<Arguments> should_return_price_adequate_to_package_size() {
        return Stream.of(
            Arguments.of(PackageSize.SMALL, LabelPricingService.SMALL_PACKAGE_PRICE),
            Arguments.of(PackageSize.MEDIUM, LabelPricingService.MEDIUM_PACKAGE_PRICE),
            Arguments.of(PackageSize.LARGE, LabelPricingService.LARGE_PACKAGE_PRICE));
    }

    private Label prepareValidLabelWithPackageSize(PackageSize packageSize) {
        Address from = new Address("Lodz", "91-600", "ul. Piotrkowska 256b");
        Address to = new Address("Warszawa", "00-100", "ul. Marszałkowska 10/34");
        User sender = new User(from, "444-444-444", "adam@example.com");
        User receiver = new User(to, "555-555-555", "olek@example.com");
        return new Label(null, UUID.randomUUID(), packageSize, receiver, sender, PaymentStatus.PENDING);
    }

}