/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.ClassBean;
import ict.bean.StudentBean;
import ict.bean.adminBean;
import ict.bean.teacherBean;
import ict.db.SAMSDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "handleAdminController", urlPatterns = {"/handleAdminController"})
public class HandleAdminController extends HttpServlet {

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
        db.createTables();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        PrintWriter out = response.getWriter();
        //create account page
        if (action.equals("createAccount")) {
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/createAccount.jsp");
            rd.forward(request, response);
        } else if (action.equals("list")) {
            ArrayList<StudentBean> students = db.queryStudent();
            request.setAttribute("students", students);
            ArrayList<teacherBean> teachers = db.queryTeacher();
            request.setAttribute("teachers", teachers);
            ArrayList<adminBean> admins = db.queryAdmin();
            request.setAttribute("admins", admins);
            RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/listAccounts.jsp");
            rd.forward(request, response);
        } else if (action.equals("getEditAccount")) {
            String id = request.getParameter("id"); 
            String type = request.getParameter("type");
            
            if ("student".equalsIgnoreCase(type)) {
                StudentBean s = db.queryStudentByID(id);
                request.setAttribute("s", s);
                ArrayList<ClassBean> classes = db.queryClass();
                request.setAttribute("c", classes);
                
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/editAccount.jsp");
                rd.forward(request, response); 
            } else if ("admin".equalsIgnoreCase(type)) {
                adminBean a = db.queryAdminByID(id);
                request.setAttribute("a", a);
                
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/editAccount.jsp");
                rd.forward(request, response);
            } else if ("teacher".equalsIgnoreCase(type)) {
                teacherBean t = db.queryTeacherByID(id);
                request.setAttribute("t", t);
                
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/editAccount.jsp");
                rd.forward(request, response);
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        init();
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        init();
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}