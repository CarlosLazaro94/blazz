
package com.example.blazztest.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Carlos Lazaro
 */
@Document(collection = "persons")
public class Person {

    private String first_name, last_name, email;
    private Number phone;
    @Id /*@GeneratedValue*/ private String id;

    public Person() {
    }

    public Person(String first_name, String last_name, String email, Number phone, String id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Number getPhone() {
        return phone;
    }

    public void setPhone(Number phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}