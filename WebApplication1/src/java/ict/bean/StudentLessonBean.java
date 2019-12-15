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
public class StudentLessonBean {
    String studId;
    String studName;
    String Lid;
    String Sid;
    String SName;
    String Attendance;
    
    public StudentLessonBean(){}
    public StudentLessonBean(String studId,String studName,String Lid,String Sid,String Attendance,String SName){
        this.studId=studId;
        this.studName=studName;
        this.Lid=Lid;
        this.Attendance=Attendance;
        this.SName=SName;
    }
    
    public String getSName(){
        return SName;
    }
    public void setSName(String SName){
        this.SName=SName;
    }
    
    public String getAttendance(){
        return Attendance;
    }
    public void setAttendance(String Attendance){
        this.Attendance=Attendance;
    }
    public void setLid(String Lid){
        this.Lid=Lid;
    }
    public void setSid(String Sid){
        this.Sid=Sid;
    }
    
    public void setStudID(String studId){
        this.studId=studId;
    }
    
    public void setStudName(String studName){
        this.studName=studName;
    }
    public String getStudID(){
        return studId;
    }
    public String getSid(){
        return Sid;
    }
    public String getStudName(){
        return studName;
    }
    public String getLid(){
        return Lid;
    }
}
