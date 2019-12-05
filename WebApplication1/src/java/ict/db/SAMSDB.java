/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
            //create user table(student, teacher, )
            String sql
                    = "CREATE TABLE IF NOT EXISTS `Student` ("
                    + "studId varchar(10) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "tel varchar(10) NOT NULL,"
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY (studId)"
                    + ");";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Teacher` ("
                    + "tId varchar(10) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
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
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY (aId)"
                    + ");";
            stmnt.execute(sql);
            
            //create class table
            sql
                    = "CREATE TABLE IF NOT EXISTS `Class` ("
                    + "classId varchar(10) NOT NULL,"
                    + "className varchar(25) NOT NULL,"
                    + "noOfStudent varchar(10) NOT NULL,"
                    + "PRIMARY KEY (classId)"
                    + ");";

            stmnt.execute(sql);
            //create subject table
            sql
                    = "CREATE TABLE IF NOT EXISTS `Subject` ("
                    + "sId varchar(10) NOT NULL,"
                    + "tId varchar(10) NOT NULL,"
                    + "subjectName varchar(25) NOT NULL,"
                    + "PRIMARY KEY (sId),"
                    + "FOREIGN KEY (tId) REFERENCES Teacher(tId))";
            stmnt.execute(sql);
            
            //Student study in which class.
            sql
                    = "CREATE TABLE IF NOT EXISTS `StudStudyClass` ("
                    + "scId varchar(10) NOT NULL,"
                    + "studId varchar(10) NOT NULL,"
                    + "tId varchar(10) NOT NULL,"
                    + "PRIMARY KEY (scId),"
                    + "FOREIGN KEY (studId) REFERENCES Student(studId),"
                    + "FOREIGN KEY (tId) REFERENCES Teacher(tId))";
            stmnt.execute(sql);
            
            
            
            //student study subjects.
            sql
                    = "CREATE TABLE IF NOT EXISTS `StudStudySubject` ("
                    + "ssId varchar(10) NOT NULL,"
                    + "studId varchar(10) NOT NULL,"
                    + "sId varchar(10) NOT NULL,"
                    + "attendance varchar(10) NOT NULL,"
                    + "PRIMARY KEY (ssId),"
                    + "FOREIGN KEY (studId) REFERENCES Student(studId),"
                    + "FOREIGN KEY (sId) REFERENCES Subject(sId));";
            stmnt.execute(sql);
            
            //teacher tutors subjects.
            sql
                    = "CREATE TABLE IF NOT EXISTS `TeacherTutorSubject` ("
                    + "ttId varchar(10) NOT NULL,"
                    + "tId varchar(10) NOT NULL,"
                    + "sId varchar(10) NOT NULL,"
                    + "PRIMARY KEY (ttId),"
                    + "FOREIGN KEY (tId) REFERENCES Teacher(tId),"
                    + "FOREIGN KEY (sId) REFERENCES Subject(sId));";
            stmnt.execute(sql);
            //what classes a teacher tutors.
            sql
                    = "CREATE TABLE IF NOT EXISTS `TeacherTutorClass` ("
                    + "tcId varchar(10) NOT NULL,"
                    + "tId varchar(10) NOT NULL,"
                    + "classId varchar(10) NOT NULL,"
                    + "PRIMARY KEY (tcId),"
                    + "FOREIGN KEY (tId) REFERENCES Teacher(tId),"
                    + "FOREIGN KEY (classId) REFERENCES Class(classId))";
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

}
