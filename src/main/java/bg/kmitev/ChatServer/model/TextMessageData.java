package bg.kmitev.ChatServer.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TextMessageData implements MessageData {
    @Size(min = 1, max = 160)
    private String payload;
}
