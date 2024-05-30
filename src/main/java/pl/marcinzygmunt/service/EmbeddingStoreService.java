package pl.marcinzygmunt.service;

import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.marcinzygmunt.configuration.model.AppConfigurationProperties;

@RequiredArgsConstructor
@Service
public class EmbeddingStoreService {

    private final AppConfigurationProperties appConfigurationProperties;
    @Getter
    private final EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
    @Getter
    private final EmbeddingStore<TextSegment> embeddingStore;

    public EmbeddingStoreIngestor storeIngestor() {

        return EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(300, 0))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();
    }

}
