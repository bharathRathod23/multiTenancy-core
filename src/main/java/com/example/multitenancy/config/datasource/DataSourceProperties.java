package com.example.multitenancy.config.datasource;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

}
