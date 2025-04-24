package it.at.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        HttpClient httpClient=HttpClient.create();
        boolean proxyBool=false;

        //SE SERVE DA IMPOSTARE UN PROXY
        if(proxyBool==true) {
            httpClient=httpClient.proxy(proxy -> proxy
                .type(ProxyProvider.Proxy.HTTP)
                .host("HOST DEL PROXY")
                .port(9800) //PORTA DEL PROXY
            );
        }

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();

    }
}
