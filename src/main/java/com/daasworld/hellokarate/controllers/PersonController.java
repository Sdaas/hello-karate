package com.daasworld.hellokarate.controllers;

import com.daasworld.hellokarate.entities.Person;
import com.daasworld.hellokarate.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // $ curl localhost:8080/api/person/1
    @GetMapping(value = "/api/person/{id}", produces = "application/json")
    public ResponseEntity<Person> getById(@PathVariable int id){
        logger.info("getById() called");
        Person p = personService.getById(id);
        if(p == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
    }

    // $ curl -X POST localhost:8080/api/person -H 'Content-type:application/json' -d '{"firstName": "John", "lastName" : "Doe", "age" : 30}'
    @PostMapping(value = "/api/person", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createPerson(@RequestBody Person p) {
        logger.info("createPerson() called");
        int id =  personService.add(p);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

