package bg.kmitev.ChatServer.model;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EmotionMessageData implements MessageData {
    @Size(min = 2, max = 10)
    @Pattern(regexp = "\\D*") // no digits
    private String payload;
}
