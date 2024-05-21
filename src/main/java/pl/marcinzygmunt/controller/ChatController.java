package pl.marcinzygmunt.controller;

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

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody ChatRequest chatRequest) throws Exception {
        return ResponseEntity.ok( chatService.execute(chatRequest.getRequest()));
    }

}
