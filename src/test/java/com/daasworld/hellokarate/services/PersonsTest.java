package com.daasworld.hellokarate.services;

import com.daasworld.hellokarate.entities.Person;
import org.junit.*;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// Plain simple unit test ....
public class PersonsTest {

    private Persons persons = new Persons();

    @Test
    public void noSuchPerson(){
        assertNull("should have returned null", persons.getById(-100));
    }

    @Test
    public void addAPerson(){
        Person p = new Person();
        assertEquals("should have returned id=0", 0, persons.add(p));
    }

    @Test
    public void addTwoPersons(){
        Person p = new Person();
        assertEquals("should have returned id=0", 0, persons.add(p));
        assertEquals("should have returned id=1", 1, persons.add(p));
    }

    @Test
    public void readAndWrite(){
        persons.add( new Person());
        persons.add( new Person("John", "Doe", 30));
        persons.add( new Person());
        Person p = persons.getById(1);
        assertEquals("expected first name John", "John", p.getFirstName());
        assertEquals("expected last name Doe", "Doe", p.getLastName());
        assertEquals("expected age 30", 30, p.getAge());
    }
}
