/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author Laughing Lam
 */
public class teacherBean {
    String tid;
    String name;
    String password;
    String tel;
    
    public teacherBean(){}

    public teacherBean(String tid, String name, String password, String tel) {
        this.tid = tid;
        this.name = name;
        this.password = password;
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
   public void setTid(String tid){
       this.tid=tid;
       
   } 
   public String getTid(){
       return tid;
   } 
   
    public void setName(String name){
       this.name=name;
   } 
public String getName(){
       return name;
   } 
}
