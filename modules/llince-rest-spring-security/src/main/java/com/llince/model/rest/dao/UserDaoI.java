/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.dao;

import com.llince.model.rest.entity.Usuario;
import java.util.List;

/**
 *
 * @author loren
 */
public interface UserDaoI {
 long save(Usuario user);
   Usuario get(long  id);
    Usuario get(String  name);
   List<Usuario> list();
   void update(Usuario user);
   void delete(long id);    
}
