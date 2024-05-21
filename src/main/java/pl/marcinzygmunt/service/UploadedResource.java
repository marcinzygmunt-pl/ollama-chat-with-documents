package pl.marcinzygmunt.service;

import dev.langchain4j.data.document.DocumentSource;
import dev.langchain4j.data.document.Metadata;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class UploadedResource implements DocumentSource {
    public final Resource resource;

    public UploadedResource(Resource resource) {
        this.resource = resource;
    }

    public static UploadedResource from(Resource resource) {
        return new UploadedResource(resource);
    }

    @Override
    public InputStream inputStream() throws IOException {
        return resource.getInputStream();
    }

    @Override
    public Metadata metadata() {
            return (new Metadata()).add("file_name", this.resource.getFilename()).add("absolute_directory_path", "/");
    }

}
