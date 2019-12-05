/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    + "className varchar(10) NOT NULL,"
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
            sql
                    = "CREATE TABLE IF NOT EXISTS `Lesson` ("
                    + "LId varchar(10) NOT NULL,"
                    + "SubjectName varchar(10) NOT NULL,"
                    + "tId varchar(10) NOT NULL,"
                    + "Date date NOT NULL,"
                    + "PRIMARY KEY (LId),"
                    + "FOREIGN KEY (tId) REFERENCES Teacher(tId))"
                   ;
            stmnt.execute(sql);
            
            sql
                    = "CREATE TABLE IF NOT EXISTS `StudLesson` ("
                    + "sLId varchar(10) NOT NULL,"
                    + "studId varchar(10) NOT NULL,"
                    + "sId varchar(10) NOT NULL,"
                    + "attendance varchar(10) NOT NULL,"
                    + "PRIMARY KEY (sLId),"
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
    
public boolean addStudRecord( String studId, String name, String tel, int age,String classid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT  INTO  student  VALUES  (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, studId);
            pStmnt.setString(2, name);
            pStmnt.setString(3, tel);
            pStmnt.setInt(4, age);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
                addStudToClassRecord(studId,classid);
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

 
public boolean addStudToClassRecord( String studId ,String classid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        
        int maxScID=0;
        try {
            //get max sid
            cnnct = getConnection();
            String preQueryStatement = "SELECT MAX(scId) FROM  `CUSTOMER`;";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
           
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                maxScID=Integer.parseInt(rs.getString(1));
                maxScID+=1;
            }

             preQueryStatement = "INSERT  INTO studclass VALUES  (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, String.valueOf(maxScID));
            pStmnt.setString(2, studId);
            pStmnt.setString(3, classid);
            
            pStmnt.executeUpdate();
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
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
}
