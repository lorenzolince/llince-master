/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.dto;

/**
 *
 * @author loren
 */
public class UserDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String idUser;
    private String lastName;
    private String firstName;
    private String idComapanie;
    private String companieName;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIdComapanie() {
        return idComapanie;
    }

    public void setIdComapanie(String idComapanie) {
        this.idComapanie = idComapanie;
    }

    public String getCompanieName() {
        return companieName;
    }

    public void setCompanieName(String companieName) {
        this.companieName = companieName;
    }

}
