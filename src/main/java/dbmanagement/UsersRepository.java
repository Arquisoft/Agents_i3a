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

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import domain.Agent;

/**
 * Repository interface from the
 * <a href="https://spring.io/guides/gs/accessing-data-mongodb/">MongoDB Spring
 * API</a>
 * 
 * @author Damian.
 * @since 06/02/2017
 */
@Repository
public interface UsersRepository extends MongoRepository<Agent, ObjectId> {

    /**
     * Find a user by its email address.
     * 
     * @param id
     *            address of the user to look for.
     * @return the user if exists, null otherwise.
     */
    Agent findById(String id);

    /**
     * Find a user by its email and password.
     * 
     * @param email
     *            of the user to look for.
     * @param password
     *            of the user to look for.
     * @return the user if login data is correct, null otherwise.
     */
    Agent findByEmailAndPassword(String email, String password);
}
