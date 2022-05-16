package com.snack.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({DataSourceConfig.class, TransactionConfig.class})
@Configuration
@ComponentScan({"com.snack.business.service"})
public class RootConfiguration {
}
