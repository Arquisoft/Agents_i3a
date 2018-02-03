/*
 * This source file is part of the Agents_i3a open source project.
 *
 * Copyright (c) 2017 Agents_i3a project authors.
 * Licensed under MIT License.
 *
 * See /LICENSE for license information.
 * 
 * This class is based on the AlbUtil project.
 * 
 */
package services;

import domain.Agent;

/**
 * Facade for the business layer implementation.
 *
 * @author Nicolás
 * @since 14/02/2017
 */
public interface ParticipantsService {

    /**
     * Given the data of a user, checks if there's such an user, and if the password
     * matches
     * 
     * @param identifier
     *            The login email for the user
     * @param password
     *            The password given on the credentials
     * @return Either the proper user, if the user exists and the password matches.
     *         Null otherwise
     */
    Agent getParticipant(String identifier, String password, int kindOfAgent);

    /**
     * Updates the password for the given user
     * 
     * @param user
     *            The given user
     * @param newPassword
     *            The new password
     */
    void updateInfo(Agent user, String newPassword);

}
