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
public class ClassBean {
    String cId, className;

    public ClassBean() {
    }

    public ClassBean(String cId, String className) {
        this.cId = cId;
        this.className = className;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    
}
