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
import org.springframework.stereotype.Service;
import pl.marcinzygmunt.configuration.AppConfigurationProperties;
import pl.marcinzygmunt.embedings.PgVectorEmbedding;

@RequiredArgsConstructor
@Service
public class EmbeddingStoreService {

    private final PgVectorEmbedding pgVectorEmbedding;
    private final AppConfigurationProperties appConfigurationProperties;
    @Getter
    private final EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    public EmbeddingStore<TextSegment> getEmbeddingStore() {
        if (appConfigurationProperties.storageType().equals("inMemory")) {
            return new InMemoryEmbeddingStore<>();
        } else {
            return pgVectorEmbedding.buildEmbeddingStore();
        }

    }


    public EmbeddingStoreIngestor getEmbeddingStoreIngestor() {

        return EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(300, 0))
                .embeddingModel(embeddingModel)
                .embeddingStore(getEmbeddingStore())
                .build();
    }


}
