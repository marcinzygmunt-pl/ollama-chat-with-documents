package pl.marcinzygmunt.configuration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@Slf4j
public class OllamaModelConfiguration {

    @Value("${pl.marcinzygmunt.ollama-host}")
    String ollamaHost;

    @Value("${pl.marcinzygmunt.ollama-port}")
    int ollamaPort;

    @Value("${pl.marcinzygmunt.ollama-language-model}")
    String ollamaLanguageModel;


    private String baseUrl() {
        return String.format("http://%s:%d", ollamaHost, ollamaPort);
    }

    @Bean(name = "ollamaModel")
    public ChatLanguageModel ollamaModel() {
        log.info("Building chat model: {},{}", baseUrl(), ollamaLanguageModel);
        return OllamaChatModel.builder()
                .baseUrl(baseUrl())
                .modelName(ollamaLanguageModel)
                .timeout(Duration.ofHours(1)) //important i slower machines
                .build();
    }


}
