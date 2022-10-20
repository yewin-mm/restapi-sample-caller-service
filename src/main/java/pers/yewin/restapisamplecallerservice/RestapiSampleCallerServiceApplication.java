package pers.yewin.restapisamplecallerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler;

import java.time.Duration;

@SpringBootApplication
public class RestapiSampleCallerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapiSampleCallerServiceApplication.class, args);
    }

    // inject restTemplate as bean type avoid not to instantiate in every class and methods.
    @Bean
    public RestTemplate restTemplateBean(){
//        return new RestTemplate();

        // if you don't catch for api error status code, above code line is enough,
        // but if you want to catch for error from api response, you need below code.
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CustomizeRestTemplateErrorHandler()); // add our customizeRestTemplateErrorHandler class in RestTemplate.
        return restTemplate;
    }


    // for global restTemplate extra configuration
    /*@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(60000)) // set api connect timeout to 60 second
                .setReadTimeout(Duration.ofMillis(60000))
                .build();
    }*/

    // declare Basic Auth configuration as global. Every api calling will go with below username and password
    /*@Bean
    RestOperations restTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthentication("username", "password").build();
    }*/
}
