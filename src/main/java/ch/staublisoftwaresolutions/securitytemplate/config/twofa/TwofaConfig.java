package ch.staublisoftwaresolutions.securitytemplate.config.twofa;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twofa")
@Data
@NoArgsConstructor
public class TwofaConfig {

    private Boolean enableTwofa;

}
