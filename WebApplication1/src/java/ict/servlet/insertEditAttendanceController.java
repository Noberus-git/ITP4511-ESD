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
           String[] getStudentsID= request.getParameterValues("studID");
           //String[] ArrivedStudents= request.getParameterValues("attandance");
           String Sid= request.getParameter("Sid");
           String Lid= request.getParameter("Lid");
           
           for(int i=0; i<getStudentsID.length;i++){
              String studStatic= request.getParameter("attandance"+i);
              out.println("\n StudentID :"+getStudentsID[i]);
              out.println("\n Attendance :"+studStatic);
              boolean updateTabledb=false;
              if(studStatic.equals("1")){
                updateTabledb=db.makeAttendance(Lid,Sid,getStudentsID[i]);
 
              }else if(studStatic.equals("0")){
                updateTabledb=db.cancelAttendance(Lid,Sid,getStudentsID[i]);
              }
              
              if(updateTabledb){
                    out.println("\n Attendance is taken");
              }else{
                  out.println("\n Attendance do not been taken");
              }
           }
           String targetURL = "teacherIndex.jsp";
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(request, response);
        }
        
    }
    
    
    


}
