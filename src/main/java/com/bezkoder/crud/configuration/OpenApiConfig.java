package com.bezkoder.crud.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class OpenApiConfig {

    @Value ("${bezkoder.openapi.dev-url}")
    private String devUrl;

    @Value ("${bezkoder.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myAPI(){

        Server serverDev = new Server();
        serverDev.url(devUrl);
        serverDev.description("Serveur UAT ");


        Server serverProd = new Server();
        serverProd.url(prodUrl);
        serverProd.description("Serveur Prod");

        Contact contact = new Contact();
        contact.setEmail("bezkoder@gmail.com");
        contact.setName("BezKoder");
        contact.setUrl("https://www.bezkoder.com");

        //License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        /*Info info = new Info()
        .title("Tutorial Management API")
        .version("1.0")
        .contact(contact)
        .description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.bezkoder.com/terms")
        .license(mitLicense);*/
        

    return new OpenAPI().servers(List.of(serverDev, serverProd));



    }
    
}
