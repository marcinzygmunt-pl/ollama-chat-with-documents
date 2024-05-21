package pl.marcinzygmunt;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.io.IOException;


@RequiredArgsConstructor
@SpringBootApplication
@ConfigurationPropertiesScan
public class OllamaChatWithText {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(OllamaChatWithText.class, args);


    }
}

