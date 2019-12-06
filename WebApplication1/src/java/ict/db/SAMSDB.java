/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.StudentLessonBean;
import ict.bean.scheduleBean;
import ict.bean.subjectBean;
import ict.bean.teacherBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laughing Lam
 */
public class SAMSDB {

    String dbUrl;
    String dbUser;
    String dbPassword;

    public SAMSDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            //  System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SAMSDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public void createDB(String createDB) {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();  // the connection 
            stmnt = cnnct.createStatement();  // create statement
            String sql
                    = "CREATE DATABASE " + createDB;
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // create user table
    public void createTables() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();  // the connection 
            stmnt = cnnct.createStatement();  // create statement

            String sql
                    = "CREATE TABLE IF NOT EXISTS `Class` ("
                    + "cId varchar(10) NOT NULL,"
                    + "Classname varchar(25) NOT NULL,"
                    + "PRIMARY KEY (cId)"
                    + ");";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Student` ("
                    + "studId varchar(10) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "password varchar(25) NOT NULL,"
                    + "cId varchar(10) NOT NULL,"
                    + "tel varchar(10) NOT NULL,"
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY (studId),"
                    + "FOREIGN KEY (cId) REFERENCES Class(cId)"
                    + ");";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Teacher` ("
                    + "tId varchar(10) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "password varchar(25) NOT NULL,"
                    + "tel varchar(10) NOT NULL,"
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY (tId)"
                    + ");";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Admin` ("
                    + "aId varchar(10) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "tel varchar(10) NOT NULL,"
                    + "password varchar(25) NOT NULL,"
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY (aId)"
                    + ");";
            stmnt.execute(sql);
            sql
                    = "CREATE TABLE IF NOT EXISTS `Subject` ("
                    + "sId varchar(10) NOT NULL,"
                    + "SubjectName varchar(10) NOT NULL,"
                    + "tId varchar(10) NOT NULL,"
                    + "PRIMARY KEY (sId),"
                    + "FOREIGN KEY (tId) REFERENCES Teacher(tId))";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Lesson` ("
                    + "LId varchar(10) NOT NULL,"
                    + "sId varchar(10) NOT NULL,"
                    + "cId varchar(10) NOT NULL,"
                    + "Date date NOT NULL,"
                    + "PRIMARY KEY (LId,sId),"
                    + "FOREIGN KEY (sId) REFERENCES Subject(sId),"
                    + "FOREIGN KEY (cId) REFERENCES Class(cId));";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `StudLesson` ("
                    + "LId varchar(10) NOT NULL,"
                    + "studId varchar(10) NOT NULL,"
                    + "sId varchar(10) NOT NULL,"
                    + "attendance varchar(10) NOT NULL,"
                    + "PRIMARY KEY (LId,sId,studId),"
                    +" FOREIGN KEY (LId) REFERENCES Lesson(LId),"
                    +" FOREIGN KEY (sId) REFERENCES Subject(sId),"
                    + "FOREIGN KEY (studId) REFERENCES Student(studId));";
            stmnt.execute(sql);

            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean teacherLoginIsValid(String tid, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        
        try {
            //get max sid
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  `teacher` where tid=? and password=?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, tid);
            pStmnt.setString(2, password);
            
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            
            if (rs.next()) {
                // set the record detail to the customer bean
                
                isSuccess=true;
                
            }
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public teacherBean getteacherBeanByTid(String tid){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        teacherBean tb= new teacherBean();
        
        try {
            //get max sid
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  `teacher` where tid=?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, tid);
            
            
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                // set the record detail to the customer bean
                tb.setTid(rs.getString(1));
                tb.setName(rs.getString(2));

            }
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tb;
                
    }
    
    public ArrayList getScheduleBeanByTid(String tid){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        ArrayList arrayRs= new ArrayList();
        try {
            
            cnnct = getConnection();
            String preQueryStatement 
                    = "SELECT * FROM `class`, `lesson` , `teacher`,`subject` "
                    + "WHERE `teacher`.`tId`=`subject`.`tId` AND "
                    + "`lesson`.`sId`=`subject`.`sId` AND"
                    + " `class`.`cId`= `Lesson`.`cId` AND `teacher`.`tId`=1 ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs = pStmnt.executeQuery();
            

            while (rs.next()) {
                scheduleBean sb= new scheduleBean();
                // set the record detail to the customer bean
                sb.setCid(rs.getString("cid"));
                sb.setClassName(rs.getString("Classname"));
                sb.setLid(rs.getString("Lid"));
                sb.setDate(rs.getString("Date"));
                sb.setSubjectName(rs.getString("SubjectName"));
                sb.setSid(rs.getString("sId"));

                arrayRs.add(sb);
            }
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;
    } 
    public ArrayList getSubjectBeanByTid(String tid){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        ArrayList arrayRs= new ArrayList();
        try {
            
            cnnct = getConnection();
            String preQueryStatement 
                    = "SELECT * FROM `subject` "
                    + "WHERE  `subject`.`tId`="+tid+" ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs = pStmnt.executeQuery();
            

            while (rs.next()) {
                subjectBean sb= new subjectBean();
                // set the record detail to the customer bean
                sb.setTid(rs.getString("tId"));
                sb.setSubjectName(rs.getString("SubjectName"));
                sb.setSid(rs.getString("sId"));
                

                arrayRs.add(sb);
            }
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;
    } 
    
    public ArrayList getStudBean(String Lid,String Sid, String Cid){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        ArrayList arrayRs= new ArrayList();
        try {
            
            cnnct = getConnection();
            String preQueryStatement 
                    = "SELECT * FROM `studlesson`, `Student` "
                    + "WHERE  `student`.`studId`=`studlesson`.`studId` and `studlesson`.`Sid`="+Sid
                    +" And `studlesson`.`Lid`="+ Lid+" ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            ResultSet rs = pStmnt.executeQuery();
            

            while (rs.next()) {
                StudentLessonBean sb= new StudentLessonBean();
                // set the record detail to the customer bean
                sb.setStudID(rs.getString("sId"));
                sb.setStudName(rs.getString("name"));
                
                arrayRs.add(sb);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;
    } 
    
}
