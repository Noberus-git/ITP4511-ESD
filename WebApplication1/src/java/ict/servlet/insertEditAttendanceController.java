/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.scheduleBean;
import ict.db.SAMSDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.print.Printer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laughing Lam
 */
@WebServlet(name = "insertEditAttendanceController", urlPatterns = {"/insertEditAttendanceController"})
public class insertEditAttendanceController extends HttpServlet {
    SAMSDB db;
    
    
        public void init() {   
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new SAMSDB(dbUrl,dbUser,dbPassword);
        db.createTables();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
         init();
         processRequest(request,response);
    }
     protected void doGet(HttpServletRequest request, HttpServletResponse response)throws  ServletException, IOException{
         init();
         processRequest(request,response);
    }
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        String action= request.getParameter("action");
        
        
        out.println(action);
        if(action.equals("markAttendance")){
           String[] ArrivedStudents= request.getParameterValues("attandance");
           String Sid= request.getParameter("Sid");
           String Lid= request.getParameter("Lid");
           
           for(int i=0; i<ArrivedStudents.length;i++){
              db.makeAttendance(Lid,Sid,ArrivedStudents[i]);
              
           }
           String targetURL = "teacherIndex.jsp";
           RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(request, response);
        }
        
    }
    
    
    


}
