package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.Message;

import java.util.List;
import java.util.Map;

public interface MessageRepository {

    List<Map<String,Object>> getMessagesByThreadId( Long threadId );

    Long addMessageToThread( Long threadId, Long userId, String comment );
}
