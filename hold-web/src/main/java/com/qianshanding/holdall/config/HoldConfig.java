package com.qianshanding.holdall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by fish
 */
@ConfigurationProperties(prefix = "hold")
@PropertySource("classpath:/config/hold.properties")
@Component
@Data
public class HoldConfig {
    private String basePath;
}
