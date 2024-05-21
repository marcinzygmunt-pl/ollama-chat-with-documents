package pl.marcinzygmunt.embedings;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.net.URI;

@Component
public class PgVectorEmbedding {

    @Value("${spring.datasource.url")
    String databaseUrl;

    @Value("${spring.datasource.username")
    String databaseUser;

    @Value("${spring.datasource.password")
    String databasePassword;
    @Value("${spring.datasource.table")
    String table;




    private String parseDatabaseUrl(String property){

        String cleanURI = databaseUrl.substring(3);
        URI uri = URI.create(cleanURI);
        switch(property){
            case "port":
                return String.valueOf(uri.getPort());
            case "host":
                return String.valueOf(uri.getHost());
            default:
                return String.valueOf(uri.getPath());

        }
    }


    public EmbeddingStore<TextSegment>  buildEmbeddingStore (){
        return  PgVectorEmbeddingStore.builder()
                .host(parseDatabaseUrl("host"))
                .port(Integer.valueOf(parseDatabaseUrl("port")))
                .database(parseDatabaseUrl("db"))
                .user(databaseUser)
                .password(databasePassword)
                .table(table)
                .dimension(384)
                .build();


    }

}
