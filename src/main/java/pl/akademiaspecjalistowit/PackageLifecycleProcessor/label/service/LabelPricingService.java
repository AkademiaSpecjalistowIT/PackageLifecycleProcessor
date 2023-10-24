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

    private Map<PackageSize, BigDecimal> packagePricingSelector;

    public LabelPricingService() {
        packagePricingSelector = new HashMap<>();
        packagePricingSelector.put(PackageSize.SMALL, new BigDecimal("9.99"));
        packagePricingSelector.put(PackageSize.MEDIUM, new BigDecimal("13.99"));
        packagePricingSelector.put(PackageSize.LARGE, new BigDecimal("19.99"));
    }

    public BigDecimal calculatePriceForPackage(Label label) {
        return Optional.ofNullable(packagePricingSelector.get(label.getPackageSize()))
            .orElseThrow(MissingPackagePricingConfigurationException::new);
    }
}
