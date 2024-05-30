package pl.marcinzygmunt.configuration;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ConditionalOnProperty(name = "pl.marcinzygmunt.storage-type", havingValue = "inMemory", matchIfMissing = true)
public class InMemoryEmbeddingConfiguration {

    @Bean(name = "embeddingStore")
    public EmbeddingStore<TextSegment> embeddingStore() {
        log.info("Application start with inMemory embedding store");
        return new InMemoryEmbeddingStore<>();
    }


}
