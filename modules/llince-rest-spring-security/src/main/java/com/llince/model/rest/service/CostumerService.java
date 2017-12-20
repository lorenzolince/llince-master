/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.service;

import com.llince.model.rest.dto.CustomerDTO;
import java.util.List;

/**
 *
 * @author Llince
 */
public interface CostumerService {
   long save(CustomerDTO customer);

    CustomerDTO get(long id);

    List<CustomerDTO> list();

    void update(CustomerDTO customer);

    void delete(long id);  
}
