package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.exception.MissingPackagePricingConfigurationException;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.Label;
import pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.model.PackageSize;

@Service
public class LabelPricingService {

    public static final String SMALL_PACKAGE_PRICE = "9.99";
    public static final String MEDIUM_PACKAGE_PRICE = "13.99";
    public static final String LARGE_PACKAGE_PRICE = "19.99";

    private Map<PackageSize, BigDecimal> packagePricingSelector;

    public LabelPricingService() {
        packagePricingSelector = new HashMap<>();
        packagePricingSelector.put(PackageSize.SMALL, new BigDecimal(SMALL_PACKAGE_PRICE));
        packagePricingSelector.put(PackageSize.MEDIUM, new BigDecimal(MEDIUM_PACKAGE_PRICE));
        packagePricingSelector.put(PackageSize.LARGE, new BigDecimal(LARGE_PACKAGE_PRICE));
    }

    public BigDecimal calculatePriceForPackage(Label label) {
        return Optional.ofNullable(packagePricingSelector.get(label.getPackageSize()))
            .orElseThrow(MissingPackagePricingConfigurationException::new);
    }
}
