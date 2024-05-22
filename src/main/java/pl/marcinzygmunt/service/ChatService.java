package pl.marcinzygmunt.service;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import pl.marcinzygmunt.configuration.OllamaModelConfiguration;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final OllamaModelConfiguration ollamaModel;
    private final EmbeddingStoreService embeddingStoreService;

    private ConversationalRetrievalChain getConversationalRetrievalChain() {
        return ConversationalRetrievalChain.builder()
                .chatLanguageModel(ollamaModel.ollamaModel())
                .retriever(EmbeddingStoreRetriever.from(embeddingStoreService.getEmbeddingStore(), embeddingStoreService.getEmbeddingModel()))
                // .chatMemory() // you can override default chat memory
                // .promptTemplate() // you can override default prompt template
                .build();
    }

    public String execute(String question) {
        log.info("Execute request: {}",question);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String answer = getConversationalRetrievalChain().execute(question);
        stopWatch.stop();
        log.info("Answer: {} in {} ms", answer, stopWatch.getTotalTimeMillis());
        return answer;
    }
}
