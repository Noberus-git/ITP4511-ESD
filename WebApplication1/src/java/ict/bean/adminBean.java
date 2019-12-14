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
    String tel;
    String password;
    

    public adminBean() {}

    public adminBean(String aId, String name, String tel, String password) {
        this.aId = aId;
        this.name = name;
        this.tel = tel;
        this.password = password;
    }

    

    public void setaId(String aId) {
        this.aId = aId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getaId() {
        return aId;
    }

    public String getName() {
        return name;
    }

}
