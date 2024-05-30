package pl.marcinzygmunt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.marcinzygmunt.model.ChatRequest;
import pl.marcinzygmunt.service.ChatService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    @Operation(summary = "Send Request to AI Assistant", description = "Returns AI Assistant answer for provided request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully processed"),
    })
    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody ChatRequest chatRequest) throws Exception {
        return ResponseEntity.ok( chatService.execute(chatRequest.getRequest()));
    }

}
