package com.example.multitenancy.config.datasource;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@NoArgsConstructor
@Slf4j
@ConfigurationProperties(prefix = "datasource-config")
public class DataSourceConfig {
    private Map<String, DataSourceProperties> tenants;

    @PostConstruct
    public void toPrint(){
        log.info("Data Source = "+tenants);
    }

}
