/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.service;

import com.llince.model.rest.dao.UserDaoI;
import com.llince.model.rest.dto.UserDto;
import com.llince.model.rest.entity.Usuario;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author loren
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    @Autowired
    UserDaoI userDaoImpl;
    @Autowired
    Mapper dozerMapper;
    @Transactional
    @Override
    public long save(Usuario user) {
        return userDaoImpl.save(user);
    }
    @Override
    public Usuario get(long id) {
        return userDaoImpl.get(id);
    }
     @Override
    public UserDto get(String name) {
        UserDto userDto = dozerMapper.map(userDaoImpl.get(name), UserDto.class);
        return userDto;
    }
    @Override
    public List<Usuario> list() {
        return userDaoImpl.list();
    }
    @Transactional
    @Override
    public void update(Usuario user) {
        userDaoImpl.update(user);
    }
    @Transactional
    @Override
    public void delete(long id) {
        userDaoImpl.delete(id);
    }
    
}
