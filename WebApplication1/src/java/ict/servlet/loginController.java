/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.SAMSDB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laughing Lam
 */
@WebServlet(name="loginController", urlPatterns={"/loginController"})
public class loginController extends HttpServlet{
    private SAMSDB db;
    
    public void init() {
        
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new SAMSDB(dbUrl,dbUser,dbPassword);
        db.createTables();
        
        
        
        
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        
        init();
        
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response ){
        init();
        
    }
    
    
    
}
