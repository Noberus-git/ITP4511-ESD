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
public class subjectBean {
    String sid;
    String SubjectName;
    String tid;
    
    public subjectBean(){}
    
    public subjectBean(String SubjectName, String sid,String tid){
        this.SubjectName=SubjectName;
        this.tid=tid;
        this.sid=sid;
        
    }
    public void setTid(String tid){
       this.tid=tid;
       
   } 
   public String getTid(){
       return tid;
   } 
   public void setSubjectName(String SubjectName){
       this.SubjectName=SubjectName;
       
   } 
   public String getSubjectName(){
       return SubjectName;
   } 
   public void setSid(String sid){
       this.sid=sid;
       
   } 
   public String getSid(){
       return sid;
   }
}
