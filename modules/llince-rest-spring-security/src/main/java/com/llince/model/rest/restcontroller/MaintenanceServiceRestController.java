/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.restcontroller;

import com.llince.model.rest.dto.CustomerDTO;
import com.llince.model.rest.service.CostumerService;
import com.llince.model.rest.util.Emcriptacion;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author loren
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MaintenanceServiceRestController {
 @Autowired
    CostumerService costumerServiceImp;
   @RequestMapping(value = "/service/rest/listCustomer", method = RequestMethod.GET)
    public @ResponseBody
    List<CustomerDTO> getAllCustomer() {
        System.out.println("###### getAllCustomer ######## ");
       List<CustomerDTO> licus= costumerServiceImp.list();
        return licus;
    } 
     @RequestMapping(value = "/service/admin/deleteCustomer", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<CustomerDTO>> deleteCustomer(@RequestBody CustomerDTO customer) {
        costumerServiceImp.delete(Integer.valueOf(customer.getCustomerId()));
       List<CustomerDTO> licus= costumerServiceImp.list();
        return new ResponseEntity<List<CustomerDTO>>(licus, HttpStatus.OK);
    }
      @RequestMapping(value = "/service/rest/updteCustomer", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<CustomerDTO>> updteCustomer(@RequestBody CustomerDTO customer) {
        costumerServiceImp.update(customer);
        List<CustomerDTO> licus= costumerServiceImp.list();
        return new ResponseEntity<List<CustomerDTO>>(licus, HttpStatus.OK);
    }
        @RequestMapping(value = "/service/rest/saveCustomer", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<CustomerDTO>> saveCustomer(@RequestBody CustomerDTO customer, HttpServletRequest request,
            HttpServletResponse response, HttpSession session) {
            System.out.println("###### saveCustomer ######## ");
         costumerServiceImp.save(customer);
        List<CustomerDTO> licus= costumerServiceImp.list();
        return new ResponseEntity<List<CustomerDTO>>(licus, HttpStatus.OK);
    }
          @RequestMapping(value = "/service/rest/getCustomer", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CustomerDTO> getCustomer(@RequestBody CustomerDTO customer) {
       CustomerDTO cus = costumerServiceImp.get(Integer.valueOf(customer.getCustomerId()));
        return new ResponseEntity<CustomerDTO>(cus, HttpStatus.OK);
    }
}
