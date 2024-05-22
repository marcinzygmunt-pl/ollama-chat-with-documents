package pl.marcinzygmunt.service;

import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.marcinzygmunt.configuration.AppConfigurationProperties;

@RequiredArgsConstructor
@Service
public class EmbeddingStoreService {

    private final AppConfigurationProperties appConfigurationProperties;
    @Getter
    private final EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    private final EmbeddingStore<TextSegment> pgVectorEmbeddingStore;

     private final InMemoryEmbeddingStore<TextSegment> inMemoryEmbeddingStore = new InMemoryEmbeddingStore<>();

    public EmbeddingStore<TextSegment> getEmbeddingStore() {
        if (appConfigurationProperties.storageType().equals("inMemory")) {
            return inMemoryEmbeddingStore;
        } else {
            return pgVectorEmbeddingStore;
        }

    }

    public EmbeddingStoreIngestor getStoreIngestor() {

        return EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(300, 0))
                .embeddingModel(embeddingModel)
                .embeddingStore(getEmbeddingStore())
                .build();
    }

}
