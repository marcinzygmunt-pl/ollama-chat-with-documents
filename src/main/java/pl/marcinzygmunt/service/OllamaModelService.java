package pl.marcinzygmunt.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pl.marcinzygmunt.configuration.AppConfigurationProperties;

@Service
@RequiredArgsConstructor
@Slf4j
public class OllamaModelService {

 private final AppConfigurationProperties appConfigurationProperties;
    private String baseUrl() {
        return String.format("http://%s:%d", appConfigurationProperties.ollamaHost(), appConfigurationProperties.ollamaPort());
    }


    public ChatLanguageModel getOllamaModel (){
            log.info("Building chat model: {},{}",baseUrl(),appConfigurationProperties.ollamaLanguageModel());
            return OllamaChatModel.builder()
                .baseUrl(baseUrl())
                .modelName(appConfigurationProperties.ollamaLanguageModel())
                .build();
    }


}
