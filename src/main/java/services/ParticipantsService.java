package services;

import domain.User;

/**
 * Created by Nicolás on 14/02/2017.
 * Façade for the business layer implementation
 */

public interface ParticipantsService {

    /**
     * Given the data of a user, checks if there's such an user, and if the password matches
     * @param email The login email for the user
     * @param password The password given on the credentials
     * @return Either the proper user, if the user exists and the password matches. Null otherwise
     */
    User getParticipant(String email, String password);

    /**
     * Updates the password for the given user
     * @param user The given user
     * @param newPassword The new password
     */
    void updateInfo(User user, String newPassword);


}
