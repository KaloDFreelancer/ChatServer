package bg.kmitev.ChatServer.service;

import bg.kmitev.ChatServer.model.Message;
import bg.kmitev.ChatServer.model.MessageData;
import bg.kmitev.ChatServer.model.MessageType;
import bg.kmitev.ChatServer.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(MessageData messageData, MessageType messageType) {
        Message bean = new Message();
        BeanUtils.copyProperties(messageData, bean);
        bean.setType(messageType);
        messageRepository.save(bean);
    }
}
