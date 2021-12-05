package bg.kmitev.ChatServer.controller;

import bg.kmitev.ChatServer.model.EmotionMessageData;
import bg.kmitev.ChatServer.model.MessageType;
import bg.kmitev.ChatServer.model.TextMessageData;
import bg.kmitev.ChatServer.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/messages/")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send_text")
    ResponseEntity<String> sendText(@Valid @RequestBody TextMessageData data) {
        messageService.save(data, MessageType.TEXT);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @PostMapping("/send_emotion")
    ResponseEntity<String> sendEmotion(@Valid @RequestBody EmotionMessageData data) {
        messageService.save(data, MessageType.EMOTION);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

}
