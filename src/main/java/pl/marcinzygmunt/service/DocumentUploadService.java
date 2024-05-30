package pl.marcinzygmunt.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentUploadService {

   private final EmbeddingStoreService embeddingStoreService;

    private Document customDocumentLoader(Resource resource, ApacheTikaDocumentParser documentParser) {
        return DocumentLoader.load(UploadedResource.from(resource), documentParser);
    }

    public void uploadDocumentToStorage(Resource resource) {
        EmbeddingStoreIngestor ingestor = embeddingStoreService.storeIngestor();
        Document document = customDocumentLoader(resource, new ApacheTikaDocumentParser());
        ingestor.ingest(document);
    }


}
