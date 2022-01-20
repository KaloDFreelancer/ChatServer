package bg.kmitev.ChatServer.service;

import bg.kmitev.ChatServer.model.Message;
import bg.kmitev.ChatServer.model.MessageType;
import bg.kmitev.ChatServer.model.TextMessageData;
import bg.kmitev.ChatServer.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {
    public static final String VALID_PAYLOAD = "text valid payload";
    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Test
    void testCreate_withValidatedParams_shouldSaveMessageToDb() {
        //Arrange
        TextMessageData messageData = new TextMessageData();
        messageData.setPayload(VALID_PAYLOAD);

        Message expectedMessage = new Message();
        expectedMessage.setPayload(VALID_PAYLOAD);
        expectedMessage.setType(MessageType.TEXT);

        //Act
        messageService.save(messageData, MessageType.TEXT);

        //Assert
        verify(messageRepository).save(expectedMessage);
    }
}