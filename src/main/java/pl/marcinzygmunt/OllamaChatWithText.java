package pl.marcinzygmunt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.io.IOException;


@RequiredArgsConstructor
@SpringBootApplication
@ConfigurationPropertiesScan
@OpenAPIDefinition(info=@Info(title="Ollama Document AI Assistant API"))
public class OllamaChatWithText {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(OllamaChatWithText.class, args);
    }
}

