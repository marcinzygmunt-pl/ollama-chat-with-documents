package pl.marcinzygmunt.configuration;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
@ConditionalOnProperty(name = "pl.marcinzygmunt.storage-type", havingValue = "pgVector")
public class PgVectorEmbeddingConfiguration {

    private final EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
    @Value("${pl.marcinzygmunt.pgvector.host}")
    String host;
    @Value("${pl.marcinzygmunt.pgvector.port}")
    int port;
    @Value("${pl.marcinzygmunt.pgvector.db}")
    String db;
    @Value("${pl.marcinzygmunt.pgvector.username}")
    String databaseUser;
    @Value("${pl.marcinzygmunt.pgvector.password}")
    String databasePassword;
    @Value("${pl.marcinzygmunt.pgvector.table}")
    String table;

    OllamaModelConfiguration ollamaModel;

    @Bean(name = "embeddingStore")
    public EmbeddingStore<TextSegment> embeddingStore() {
        log.info("Application start with pgVector embedding store");
        return PgVectorEmbeddingStore.builder().host(host).port(port).database(db).user(databaseUser).password(databasePassword).table(table).dimension(384).build();
    }


}
