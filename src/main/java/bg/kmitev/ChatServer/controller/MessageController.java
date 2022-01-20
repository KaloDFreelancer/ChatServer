package bg.kmitev.ChatServer.controller;

import bg.kmitev.ChatServer.model.MessageData;
import bg.kmitev.ChatServer.model.MessageDataFactory;
import bg.kmitev.ChatServer.model.MessageRequestPayload;
import bg.kmitev.ChatServer.model.MessageType;
import bg.kmitev.ChatServer.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages/")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/send_{type}")
    ResponseEntity<String> create(@PathVariable MessageType type, @RequestBody MessageRequestPayload requestPayload) {
        MessageData messageData = MessageDataFactory.createInstance(type);
        messageData.setPayload(requestPayload.getPayload());
        messageService.save(messageData, type);

        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
