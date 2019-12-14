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
public class StudentBean {
    
    String studId;
    String name;
    String password;
    String className;
    String tel;
    public StudentBean() {}

    public StudentBean(String studId, String name, String password, String className, String tel) {
        this.studId = studId;
        this.name = name;
        this.password = password;
        this.className = className;
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getclassName() {
        return className;
    }

    public void setclassName(String className) {
        this.className = className;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
