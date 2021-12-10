package bg.kmitev.ChatServer.controller;

import bg.kmitev.ChatServer.model.MessageType;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, MessageType> {
    @Override
    public MessageType convert(String source) {
        return MessageType.valueOf(source.toUpperCase());
    }
}