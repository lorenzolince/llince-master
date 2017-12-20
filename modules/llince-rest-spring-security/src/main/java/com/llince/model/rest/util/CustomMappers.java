/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.util;

import org.dozer.CustomConverter;

/**
 *
 * @author loren
 */
public class CustomMappers implements CustomConverter{

   public Object convert(Object destination, Object source, 
      Class destClass, Class sourceClass) {
    if (source == null) {
      return null;
    }
    byte [] dest = null;
    
    if (source instanceof String) {
     String strSource =source.toString();
       System.out.println(strSource);
      if (destination == null) {
        dest = strSource.getBytes();
      } 
    }
     return dest;
   }
   }
