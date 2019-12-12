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
public class SubjectClassReportBean {
    String sid;
    String subjectName;
    String classID;
    String className;
    int attendance;
    
    public SubjectClassReportBean(){
    }
    public SubjectClassReportBean(String sid,String subjectName, String classID,String className,int attendance){
        this.sid=sid;
        this.subjectName=subjectName;
        this.classID=classID;
        this.className=className;
        this.attendance=attendance;
    }
    
    public void setSid(String sid){
       this.sid=sid;
       
   } 
   public String getSid(){
       return sid;
   } 
   
    public void setSubjectName(String subjectName){
       this.subjectName=subjectName;
       
   } 
   public String getSubjectName(){
       return subjectName;
   } 
    
    public void setClassID(String classID){
       this.classID=classID;
       
   } 
   public String getClassID(){
       return classID;
   } 
    public void setClassName(String className){
       this.className=className;
       
   } 
   public String getClassName(){
       return className;
   } 
     public void setAttendance(int attendance){
       this.attendance=attendance;
       
   } 
   public int getAttendance(){
       return attendance;
   } 

   
}
