/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redisexample.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Himanshu
 */
@RestController
public class WebController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/save")
    public String save() {
        // save a single Customer
        //lock is set to false as the precondition for creation i.e. empty row should already be checked
        customerRepository.save(new Customer(1, false, "Jack", "Smith"));
        customerRepository.save(new Customer(2, false, "Adam", "Johnson"));
        customerRepository.save(new Customer(3, false, "Kim", "Smith"));
        customerRepository.save(new Customer(4, false, "David", "Williams"));
        customerRepository.save(new Customer(5, false, "Peter", "Davis"));

        return "Done";
    }

    @RequestMapping("/findall")
    public String findAll() {
        String result = "";
        Map<Long, Customer> customers = customerRepository.findAll();

        for (Customer customer : customers.values()) {
            //When one or more objects are locked it means either an update or delete is in progress over them
            if (customer.isLock()) {
                return "All or some rows locked";
            }
            result += customer.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/find")
    public String findById(@RequestParam("id") Long id) {
        String result = "";
        Customer customer = customerRepository.find(id);
        //When object is locked incorrect info wouold be returned
        if (!customer.isLock()) {
            result = customer.toString();
        } else {
            result = "row locked";
        }
        return result;
    }

    @RequestMapping(value = "/uppercase")
    public String postCustomer(@RequestParam("id") Long id) throws InterruptedException {
        Customer customer = customerRepository.find(id);
         //When object is locked we cannot update it
        if (!customer.isLock()) {
            //Lock now to prevent access
            customer.setLock(true);
            customerRepository.update(customer);

            //For checking purpose delay the update
            //Thread.sleep(12000);

            customer.setFirstName(customer.getFirstName().toUpperCase());
            customer.setLastName(customer.getLastName().toUpperCase());
            customer.setLock(false);
            customerRepository.update(customer);
        } else {
            return "row locked";
        }
        return "Done";
    }

    @RequestMapping("/delete")
    public String deleteById(@RequestParam("id") Long id) {
        Customer customer = customerRepository.find(id);
        //When object is locked we cannot delete it
        if (!customer.isLock()) {
            //set lock before delete
            customer.setLock(true);
            customerRepository.update(customer);
            customerRepository.delete(id);
        }else{
            return "row locked";
        }
        return "Done";
    }
}
