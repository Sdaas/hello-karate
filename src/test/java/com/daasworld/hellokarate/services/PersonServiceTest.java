package com.daasworld.hellokarate.services;

import com.daasworld.hellokarate.entities.Person;
import org.junit.*;


import static org.junit.Assert.*;

// Plain simple unit test ....
public class PersonServiceTest {

    private PersonService personService = new PersonService();

    @Test
    public void noSuchPerson(){
        assertNull("should have returned null", personService.getById(-100));
    }

    @Test
    public void addAPerson(){
        Person p = new Person();
        assertEquals("should have returned id=0", 0, personService.add(p));
    }

    @Test
    public void addTwoPersons(){
        Person p = new Person();
        assertEquals("should have returned id=0", 0, personService.add(p));
        assertEquals("should have returned id=1", 1, personService.add(p));
    }

    @Test
    public void readAndWrite(){
        personService.add( new Person());
        personService.add( new Person("John", "Doe", 30));
        personService.add( new Person());
        Person p = personService.getById(1);
        assertEquals("expected first name John", "John", p.getFirstName());
        assertEquals("expected last name Doe", "Doe", p.getLastName());
        assertEquals("expected age 30", 30, p.getAge());
    }
}
