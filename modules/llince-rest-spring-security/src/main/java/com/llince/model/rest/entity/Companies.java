/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author loren
 */
@Entity(name = "Companies")
public class Companies implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE_COMPANY_ID")
    private Integer typeCompnyId;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "companies")
   private List<Usuario> users = new ArrayList<Usuario>();
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeCompnyId() {
        return typeCompnyId;
    }

    public void setTypeCompnyId(Integer typeCompnyId) {
        this.typeCompnyId = typeCompnyId;
    }

    public List<Usuario> getUsers() {
        return users;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Companies{" + "id=" + id + ", name=" + name + ", typeCompnyId=" + typeCompnyId + " " +  + '}';
    }

}
