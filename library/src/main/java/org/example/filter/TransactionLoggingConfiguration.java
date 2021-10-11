package org.example.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.filter")
@EnableAutoConfiguration
public class TransactionLoggingConfiguration {
    @Autowired
    private ApplicationContext context;

}
