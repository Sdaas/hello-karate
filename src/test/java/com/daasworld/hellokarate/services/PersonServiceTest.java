package com.daasworld.hellokarate.services;

import com.daasworld.hellokarate.entities.Person;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Plain simple unit test ....
public class PersonServiceTest {

    private PersonService personService = new PersonService();

    @Test
    public void noSuchPerson(){
        assertNull(personService.getById(-100), "should have returned null");
    }

    @Test
    public void addAPerson(){
        Person p = new Person();
        assertEquals(0, personService.add(p), "should have returned id=0");
    }

    @Test
    public void addTwoPersons(){
        Person p = new Person();
        assertEquals(0, personService.add(p), "should have returned id=0");
        assertEquals(1, personService.add(p), "should have returned id=1");
    }

    @Test
    public void readAndWrite(){
        personService.add( new Person());
        personService.add( new Person("John", "Doe", 30));
        personService.add( new Person());
        Person p = personService.getById(1);
        assertEquals( "John", p.getFirstName(), "expected first name John");
        assertEquals( "Doe", p.getLastName(), "expected last name Doe");
        assertEquals(30, p.getAge(), "expected age 30");
    }
}
