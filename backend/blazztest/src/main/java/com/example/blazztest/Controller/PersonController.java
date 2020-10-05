/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.blazztest.Controller;

import com.example.blazztest.Model.Person;
import com.example.blazztest.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 *
 * @author Carlos Lazaro
 */
@CrossOrigin(origins ="https://distracted-tereshkova-30141d.netlify.app" )
@RestController
@RequestMapping(value="/api")
public class PersonController {
    @Autowired
    private PersonRepository personrepository;
    
    @GetMapping(value="/")
    String hello(){
        return "hello ";
    }
    //getAllPerson
    @GetMapping(value="/persons")
    public List<Person> getPersons(){
        return personrepository.findAll();
    }


    //postSavePerson
    @PostMapping(value="/save")
    public ResponseEntity<Person> save(@RequestBody Person person){

        Person obj = personrepository.save(person);

        return  new ResponseEntity<Person>(obj, HttpStatus.OK);

    }

    //putUpdatePerson
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") String id, @RequestBody Person person){
        Optional<Person> personOptional = personrepository.findById(id);

        if (personOptional.isPresent()) {
            Person _person = personOptional.get();
            _person.setFirst_name(person.getFirst_name());
            _person.setLast_name(person.getLast_name());
            _person.setEmail(person.getEmail());
            _person.setPhone(person.getPhone());
            return new ResponseEntity<>(personrepository.save(_person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
