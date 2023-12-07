package ru.shasoka.dcis.prac_5.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static ru.shasoka.dcis.prac_5.utilities.Logger.log;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClient(@Value("${api.host}") String apiHost) {
        return WebClient.builder()
                .baseUrl(apiHost)
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log("Request: " + clientRequest.method() + " " + clientRequest.url(), "Client");
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log("Response status: " + clientResponse.statusCode(), "Client");
            return Mono.just(clientResponse);
        });
    }
}