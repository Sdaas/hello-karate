package com.daasworld.hellokarate.services;

import com.daasworld.hellokarate.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class Persons {

    private HashMap<Integer,Person> map = new HashMap<>();
    private int currentId=0;

    public Person getById(int id) {
        return map.get(id);
    }

    public int add(Person p) {
        map.put(currentId,p);
        return currentId++;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "map=" + map +
                ", currentId=" + currentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons persons = (Persons) o;
        return currentId == persons.currentId &&
                Objects.equals(map, persons.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, currentId);
    }
}
