/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.adminBean;
import ict.bean.teacherBean;
import ict.db.SAMSDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Laughing Lam
 */
@WebServlet(name = "loginController", urlPatterns = {"/loginController"})
public class loginController extends HttpServlet {

    private SAMSDB db;
    String userType = "teacher";
    String username;
    String password;

    String targetURL;

    public void init() {

        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new SAMSDB(dbUrl, dbUser, dbPassword);
        db.createTables();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        init();
        handleLogin(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        init();
        handleLogin(request, response);
    }

    public void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        userType = request.getParameter("userType");
        if (userType.equals("teacher")) {
            teacherLogin(request, response);
        }
        if (userType.equals("admin")) {
            adminLogin(request, response);
        }
    }

    public void teacherLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        username = request.getParameter("username");
        password = request.getParameter("password");

        boolean loginValid = false;
        loginValid = db.teacherLoginIsValid(username, password);

        if (loginValid) {
            HttpSession session = request.getSession(true);
            teacherBean tb = db.getteacherBeanByTid(username);
            session.setAttribute("teacherBean", tb);
            targetURL = "teacherIndex.jsp";
        } else {
            targetURL = "index.jsp";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    public void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        username = request.getParameter("username");
        password = request.getParameter("password");

        boolean loginValid = false;
        loginValid = db.adminLoginIsValid(username, password);

        if (loginValid) {
            HttpSession session = request.getSession(true);
            adminBean ab = db.getAdminBeanByaId(username);
            session.setAttribute("adminBean", ab);
            targetURL = "adminIndex.jsp";
        } else {
            targetURL = "index.jsp";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
