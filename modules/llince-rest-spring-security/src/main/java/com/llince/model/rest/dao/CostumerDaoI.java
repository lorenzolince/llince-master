/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.dao;

import com.llince.model.rest.entity.Customer;
import java.util.List;

/**
 *
 * @author Llince
 */
public interface CostumerDaoI {

    long save(Customer customer);

    Customer get(long id);

    List<Customer> list();

    void update(Customer customer);

    void delete(long id);

}
