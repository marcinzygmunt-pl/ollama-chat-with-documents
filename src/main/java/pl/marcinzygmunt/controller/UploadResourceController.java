package pl.marcinzygmunt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.marcinzygmunt.service.DocumentUploadService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UploadResourceController {

    private final DocumentUploadService documentUploadService;

    @Operation(summary = "Upload to repository", description = "Uploads your document to AI Assistant repository. Accepts almost all popular like: formats .pdf, .txt, .doc and many others")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully uploaded !"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong ! Check application logs !"),
    })
    @PostMapping(value="/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
       documentUploadService.uploadDocumentToStorage(file.getResource());
       return ResponseEntity.ok("File Uploaded !");
    }
}
