package com.vikas.linkedin.connectionsService.ConnectionsService.services;


import com.vikas.linkedin.connectionsService.ConnectionsService.entity.Person;
import com.vikas.linkedin.connectionsService.ConnectionsService.repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionsService {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnectionsOfUser(Long userId){
        log.info("Getting first degree connections of user with id: {}",userId);
        return personRepository.getFirstDegreeConnections(userId);
    }


}
