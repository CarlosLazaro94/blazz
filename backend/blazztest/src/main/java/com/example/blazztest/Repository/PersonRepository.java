
package com.example.blazztest.Repository;

import com.example.blazztest.Model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
    
}
