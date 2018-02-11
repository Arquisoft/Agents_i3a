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
package dbmanagement;

import domain.Agent;

/**
 * Interface service for the database. Current implementation uses Spring Boot
 * Data MongoDB API
 * <a href="https://spring.io/guides/gs/accessing-data-mongodb/">link here</a>
 * 
 * @author Nicolás.
 * @since 14/02/2017
 */
public interface Database {

    /**
     * Updated the information of a given user.
     * 
     * @param user
     *            to update its information.
     */
    void updateInfo(Agent user);

    /**
     * Gets a participant from its email address.
     * 
     * @param id
     *            of the participant to get.
     * @return the participant if exists, null otherwise.
     */
    Agent getParticipant(String id);

}
