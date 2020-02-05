package com.daasworld.hellokarate.controllers;

import com.daasworld.hellokarate.entities.Person;
import com.daasworld.hellokarate.services.PersonService;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.*;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerSpringTests {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    public PersonService mockPersonService;

    @Test
    public void createPerson(){

        // setup the mock ...
        Person p = new Person("John", "Doe", 30);
        when(mockPersonService.add( p )).thenReturn(0);

        // send the request ....
        String postUrl = "/api/person";
        ResponseEntity<Integer> response = template.postForEntity(postUrl,p,Integer.class);

        // check the response ...
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Integer id = response.getBody();
        assertEquals("expected Id to be 0", 0L, (long)id);

        // Now verify the mock ...
        verify(mockPersonService).add( p ); // verify that the mock was called
    }

    @Test
    public void getPerson(){

        // setup the mock ...
        Person p = new Person("John", "Doe", 30);
        when(mockPersonService.getById(0)).thenReturn(p);

        // send the request ....
        String getUrl = "/api/person/0";
        ResponseEntity<Person> entity = template.getForEntity(getUrl, Person.class);

        // check the response ...
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        p =  entity.getBody();
        assertEquals("expected first name John", "John", p.getFirstName());
        assertEquals("expected last name Doe", "Doe", p.getLastName());
        assertEquals("expected age 30", 30, p.getAge());

        // Now verify the mock ...
        verify(mockPersonService).getById(0); // verify that the mock was called
    }

    @Test
    public void getNonExistentPerson(){

        // setup the mock ...
        when(mockPersonService.getById(12345)).thenReturn(null);

        // send the request ....
        String getUrl = "/api/person/12345";
        ResponseEntity<Person> entity = template.getForEntity(getUrl, Person.class);

        // check the response ...
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

        // Now verify the mock ...
        verify(mockPersonService).getById(12345); // verify that the mock was called
    }
}
