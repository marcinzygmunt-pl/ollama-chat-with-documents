package pl.marcinzygmunt.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "pl.marcinzygmunt")
public record AppConfigurationProperties (
        String ollamaHost,
        int ollamaPort,
        String ollamaLanguageModel,
        String storageType


){}
