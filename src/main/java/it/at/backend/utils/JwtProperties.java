

package it.at.backend.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("jwt")
@Data
public class JwtProperties {
    private String secret;
}
