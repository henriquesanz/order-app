package br.com.enterprise.orders.config;

import feign.Contract;
import feign.Logger;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class FeignConfig {

    @Value("${application.retry.period:100}")
    int period;

    @Value("${application.retry.maxPeriod:1000}")
    int maxPeriod;

    @Value("${application.retry.maxAttempts:3}")
    int maxAttempts;

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(period, maxPeriod, maxAttempts); // Inicialmente espera 100ms, depois 1s, tenta 3 vezes.
    }

    @Bean
    public static MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
