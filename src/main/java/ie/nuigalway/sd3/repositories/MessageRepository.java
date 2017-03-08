package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.Message;

import java.util.List;

public interface MessageRepository {

    List<Message> getMessagesByThreadId( Long threadId );

    Long addMessageToThread( Long threadId, Long userId, String comment );
}
