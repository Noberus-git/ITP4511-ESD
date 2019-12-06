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
public class lessonBean {
    String sid;
    String SubjectName;
    
    String cid;
    String className;
    
    String Lid;
    String date;

  
    public lessonBean(){}
    
    public lessonBean(String cid, String Lid,  String SubjectName, String sid,String className ,String date){
        this.cid=cid;
        this.Lid=Lid;

        this.SubjectName=SubjectName;
        this.sid=sid;
        this.className= className;

        this.date=date;
    }

   
    public void setCid(String cid){
       this.cid=cid;
       
   } 
   public String getCid(){
       return cid;
   } 
   
    public void setLid(String Lid){
       this.Lid=Lid;
       
   } 
   public String getLid(){
       return Lid;
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
   public String getClassName(){
       return className;
   }
   public void setClassName(String className){
       this.className=className;
       
   } 
   public void setDate(String date){
       this.date=date;
       
   } 
   public String getDate(){
       return date;
   }
}
