package org.uniovi.i3a.agents_service.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.uniovi.i3a.agents_service.types.Agent;


public interface AgentsRepository extends MongoRepository<Agent, ObjectId> {
    
    /**
     * Find a user by its email address.
     * 
     * @param id
     *            address of the user to look for.
     * @return the user if exists, null otherwise.
     */
    Agent findById(String id);
    
}
