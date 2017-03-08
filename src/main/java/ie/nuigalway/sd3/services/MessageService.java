package ie.nuigalway.sd3.services;


import ie.nuigalway.sd3.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    //a repository is chosen depending on which profile is run (dev or prod)

    public Long addMessageToThread( Long threadId, Long userId, String comment ){

        return messageRepository.addMessageToThread( threadId, userId, comment );
    }

}
