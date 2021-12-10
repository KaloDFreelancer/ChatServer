package bg.kmitev.ChatServer.service;

import bg.kmitev.ChatServer.model.Message;
import bg.kmitev.ChatServer.model.MessageData;
import bg.kmitev.ChatServer.model.MessageType;
import bg.kmitev.ChatServer.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(@Valid MessageData messageData, MessageType type) {
        Message bean = new Message();
        BeanUtils.copyProperties(messageData, bean);
        bean.setType(type);
        messageRepository.save(bean);
    }
}
