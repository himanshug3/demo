/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redisexample.demo;

import java.util.Map;

/**
 *
 * @author Himanshu
 */
public interface CustomerRepository {

    void save(Customer customer);

    Customer find(Long id);

    Map<Long, Customer> findAll();

    void update(Customer customer);

    void delete(Long id);
}
