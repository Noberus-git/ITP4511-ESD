/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author Helen&Nangen
 */
public class adminBean {

    String aId;
    String name;

    public adminBean() {}

    public adminBean(String aId, String name) {
        this.aId = aId;
        this.name = name;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getaId() {
        return aId;
    }

    public String getName() {
        return name;
    }

}
