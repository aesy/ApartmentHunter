package org.aesy.apartment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "apartment")
public class ApartmentProperties {
    @NotNull
    private List<ApartmentHunter> hunter;
}
