package com.example.springbocs.service;

import com.example.springbocs.model.entity.Person;
import com.example.springbocs.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepo personRepo;

    /**
     *
     * @return all available user within the user table
     */
    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    /**
     * get a person information based on its ID
     * @param id
     * @return between present and absent depends on the availability of the filter
     */
    public Optional<Person> getPerson(Integer id) {
        return personRepo.findById(id);
    }
}
