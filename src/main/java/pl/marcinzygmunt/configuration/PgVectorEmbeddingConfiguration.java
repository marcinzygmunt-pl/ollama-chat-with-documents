package pl.marcinzygmunt.configuration;

import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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

    @Bean(name = "pgVectorEmbeddingStore")
    public EmbeddingStore<TextSegment> pgVectorEmbeddingStore() {
        return PgVectorEmbeddingStore.builder()
                .host(host)
                .port(port)
                .database(db)
                .user(databaseUser)
                .password(databasePassword)
                .table(table)
                .dimension(384)
                .build();
    }



}
