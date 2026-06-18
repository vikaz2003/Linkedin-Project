package com.vikas.linkedin.connectionsService.ConnectionsService.controller;


import com.vikas.linkedin.connectionsService.ConnectionsService.entity.Person;
import com.vikas.linkedin.connectionsService.ConnectionsService.services.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService service;


    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId){
        List<Person> personList=service.getFirstDegreeConnectionsOfUser(userId);
        return ResponseEntity.ok(personList);
    }


}
