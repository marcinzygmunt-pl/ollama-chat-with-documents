package pl.marcinzygmunt;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;


public class Main {
    static String baseUrl() {
        return String.format("http://%s:%d", "localhost", 11434);
    }

    static String MODEL_NAME = "llama3:latest"; // try "mistral", "llama2", "codellama", "phi" or "tinyllama"

    public static void main(String[] args) throws IOException {

        ClassPathResource res = new ClassPathResource("src/main/resources/file.txt");


        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(300, 0))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();


        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl(baseUrl())
                .modelName(MODEL_NAME)
                .build();

        Document document = loadDocument(res.getPath(), new TextDocumentParser());
        ingestor.ingest(document);

        ConversationalRetrievalChain chain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(model)
                .retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
                // .chatMemory() // you can override default chat memory
                // .promptTemplate() // you can override default prompt template
                .build();

        String answer = chain.execute("which metal is used in forging?");
        System.out.println(answer); // Charlie is a cheerful carrot living in VeggieVille...
    }
}

