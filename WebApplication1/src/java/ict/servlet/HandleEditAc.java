/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.SAMSDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Helen&Nangen
 */
@WebServlet(name = "HandleEditAc", urlPatterns = {"/handleEditAc"})
public class HandleEditAc extends HttpServlet {
    private SAMSDB db;
    private String action;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new SAMSDB(dbUrl, dbUser, dbPassword);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        init();
        processRequest(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        init();
        processRequest(request, response);
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        action = request.getParameter("action");
        String type = request.getParameter("type");
        PrintWriter out = response.getWriter();
        out.println("Type: "+type);
        out.println("<br>id"+request.getParameter("id"));
        out.println("<br>name"+request.getParameter("name"));
        out.println("<br>tel"+request.getParameter("tel"));
        out.println("<br>password"+request.getParameter("password"));
        
        if (action.equals("add")) {

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String tel = request.getParameter("tel");
            String password = request.getParameter("password");
            
            boolean s=db.addAccount(id, name, tel, password, type);
            out.print(s);
  
            response.sendRedirect("adminIndex.jsp");
        } else if (action.equals("edit")) {
            
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String tel = request.getParameter("tel");
            String password = request.getParameter("password");
            String className = "";
            if (type.equals("student"))
                className = request.getParameter("c");
                
            db.editAccount(id, name, tel, password, type, className);
            response.sendRedirect("adminIndex.jsp");
        }
    }



}
