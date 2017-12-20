/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.service;

import com.llince.model.rest.dao.CostumerDaoI;
import com.llince.model.rest.dto.CustomerDTO;
import com.llince.model.rest.entity.Customer;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Llince
 */
@Service
public class CostumerServiceImp implements CostumerService {

    @Autowired
    CostumerDaoI costumerDaoI;
    @Autowired
    Mapper dozerMapper;

    @Transactional
    @Override
    public long save(CustomerDTO customer) {
        Customer cos = dozerMapper.map(customer, Customer.class);
        costumerDaoI.save(cos);
        return cos.getCustomerId();
    }

    @Override
    public CustomerDTO get(long id) {
      Customer cus =costumerDaoI.get(id);
      CustomerDTO costumer = dozerMapper.map(cus, CustomerDTO.class);
      return costumer;
    }

    @Override
    public List<CustomerDTO> list() {
       List<Customer> listCus = costumerDaoI.list();
       List<CustomerDTO> list =dozerMapper.map(listCus, List.class);
       return list;
    }

    @Transactional
    @Override
    public void update(CustomerDTO customer) {
        Customer cos = dozerMapper.map(customer, Customer.class);
        costumerDaoI.update(cos);
    }

    @Transactional
    @Override
    public void delete(long id) {
       costumerDaoI.delete(id);
    }

}
