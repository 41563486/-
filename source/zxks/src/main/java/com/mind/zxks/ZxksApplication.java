package com.mind.zxks;

import com.mind.zxks.configuration.property.SystemConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(value = { SystemConfig.class})
public class ZxksApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZxksApplication.class, args);
    }
}
