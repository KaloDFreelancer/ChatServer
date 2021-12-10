package bg.kmitev.ChatServer.model;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MessageDataFactory {
    private static final Map<String, MessageData> handler = new HashMap<>();

    @PostConstruct
    private Map<String, MessageData> init() {
        handler.put(MessageType.TEXT.toString(),new TextMessageData());
        handler.put(MessageType.EMOTION.toString(), new EmotionMessageData());
        return handler;
    }

    public static MessageData createInstance(MessageType messageType) {
        return Optional.ofNullable(handler.get(messageType.toString())).orElseThrow(() -> new IllegalArgumentException("Invalid message type received"));
    }
}