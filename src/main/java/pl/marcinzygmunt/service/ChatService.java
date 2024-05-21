package pl.marcinzygmunt.service;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final OllamaModelService ollamaModelService;
    private final EmbeddingStoreService embeddingStoreService;

    private ConversationalRetrievalChain getConversationalRetrievalChain() {
        return ConversationalRetrievalChain.builder()
                .chatLanguageModel(ollamaModelService.getOllamaModel())
                .retriever(EmbeddingStoreRetriever.from(embeddingStoreService.getEmbeddingStore(), embeddingStoreService.getEmbeddingModel()))
                // .chatMemory() // you can override default chat memory
                // .promptTemplate() // you can override default prompt template
                .build();
    }

    public String execute(String question) {
        log.info("Execute request: {}",question);
        return getConversationalRetrievalChain().execute(question);
    }
}
