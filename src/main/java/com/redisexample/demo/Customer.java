/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redisexample.demo;

/**
 *
 * @author Himanshu
 */
import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    //to determine whether the object can be accessed from DB or not
    private boolean lock;
    private String firstName;
    private String lastName;

    protected Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Customer(long id, Boolean lock,String firstName, String lastName) {
        this.id = id;
        this.lock = lock;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }
}
