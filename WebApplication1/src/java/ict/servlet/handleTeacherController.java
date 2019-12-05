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
@WebServlet(name = "handleTeacherController", urlPatterns = {"/handleTeacherController"})
public class handleTeacherController extends HttpServlet {

    SAMSDB db;
    String action;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new SAMSDB(dbUrl, dbUser, dbPassword);
        db.createTables();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        handleAction(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        handleAction(request, response);
    }

    public void handleAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String targetURL;
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        out.println(action);

        if (action.equals("viewSchedule")) {
            ArrayList<scheduleBean> sb;
            String tid = request.getParameter("tId");
            sb = db.getScheduleBeanByTid(tid);
            if (sb != null) {
                out.println(sb.size());
                request.setAttribute("ScheduleBeans", sb);
                targetURL = "classSchedule.jsp";
            } else {
                targetURL = "teacherIndex.jsp";
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(request, response);
        }
        if (action.equals("viewSubject")) {
            ArrayList<scheduleBean> sb;
            String tid = request.getParameter("tId");
            sb = db.getScheduleBeanByTid(tid);
            if (sb != null) {
                out.println(sb.size());
                request.setAttribute("ScheduleBeans", sb);
                targetURL = "classSchedule.jsp";
            } else {
                targetURL = "teacherIndex.jsp";
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(request, response);

        }

    }

}
