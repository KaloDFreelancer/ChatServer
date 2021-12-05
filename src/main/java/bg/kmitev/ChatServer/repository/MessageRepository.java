package bg.kmitev.ChatServer.repository;


import bg.kmitev.ChatServer.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}