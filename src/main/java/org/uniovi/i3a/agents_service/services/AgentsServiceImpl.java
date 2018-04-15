package org.uniovi.i3a.agents_service.services;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniovi.i3a.agents_service.repositories.AgentsRepository;
import org.uniovi.i3a.agents_service.types.Agent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgentsServiceImpl implements AgentsService {

    @Autowired
    AgentsRepository repository;

    @Override
    public Agent getAgent(String identifier, String password, int kindOfAgent) {
	Agent agent = repository.findById(identifier);

	if (agent != null && new StrongPasswordEncryptor().checkPassword(password, agent.getPassword()) && agent.getKindCode() == kindOfAgent) {
	    log.info("Returning information of: " + agent.toString());
	    return agent;
	}

	log.warn("No information found for: " + identifier + ", " + password + ", " +kindOfAgent);
	return null;
    }

    @Override
    public void save(Agent agent) {
	repository.save(agent);
    }

    @Override
    public void delete(Agent agent) {
	repository.delete(agent);
    }

}
