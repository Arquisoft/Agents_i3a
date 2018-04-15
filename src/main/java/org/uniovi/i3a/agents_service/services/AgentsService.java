package org.uniovi.i3a.agents_service.services;

import org.uniovi.i3a.agents_service.types.Agent;

public interface AgentsService {
    
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
    Agent getAgent(String identifier, String password, int kindOfAgent);
    
    void save(Agent agent);
    
    void delete(Agent agent);

}
