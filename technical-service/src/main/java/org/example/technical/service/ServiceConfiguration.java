package org.example.technical.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example.technical.service")
@EntityScan("org.example.technical.service.entity")
public class ServiceConfiguration {
}
