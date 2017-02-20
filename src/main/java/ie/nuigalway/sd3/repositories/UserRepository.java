package ie.nuigalway.sd3.repositories;

import ie.nuigalway.sd3.entities.User;

import java.util.List;

public interface UserRepository {

    User getUser( Long id );

    User getUserByEmailAndPasshash( String email, String passhash );

    void updateDtUpdated( Long id );
}
