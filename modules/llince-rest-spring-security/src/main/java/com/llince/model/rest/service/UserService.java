/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.service;

import com.llince.model.rest.dto.UserDto;
import com.llince.model.rest.entity.Usuario;
import java.util.List;

/**
 *
 * @author loren
 */
public interface UserService {
  long save(Usuario user);
   Usuario get(long  id);
   UserDto get(String  name);
   List<Usuario> list();
   void update(Usuario user);
   void delete(long id);     
}
