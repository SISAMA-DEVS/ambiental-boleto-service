package br.com.miltecti.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;
import feign.Logger;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
                30,
                TimeUnit.SECONDS,
                60,
                TimeUnit.SECONDS,
                false // NÃO seguir redirects automaticamente
        );
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}