/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.StudentLessonBean;
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
@WebServlet(name = "studentContorller", urlPatterns = {"/studentContorller"})
public class studentContorller extends HttpServlet {

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
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String targetURL;
        String action = request.getParameter("action");
        String studid = request.getParameter("studid");

        PrintWriter out = response.getWriter();
        out.println("Action: " + action);
        out.println("studid: " + studid);

        if (action.equals("viewSubjects")) {
            ArrayList<scheduleBean> sb;

            //normal get schedule
            sb = db.getScheduleBeanBysId(studid);

            //get student beans
            if (sb != null) {
                out.println("Bean size: " + sb.size());
                request.setAttribute("getStudentScheduleBeanlist", sb);
                targetURL = "studentAttendance.jsp";
            } else {
                targetURL = "studentIndex.jsp";
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(request, response);

        } else if (action.equals("viewAttendance")) {
            String sid = request.getParameter("sid");
            ArrayList<StudentLessonBean> sb;

            sb = db.getStudentLessonBeanBysidAndStudid(sid, studid);
            out.println("sid: " + sid);
            out.println("Bean size: " + sb.size());
            if (sb != null) {
                //out.println("Bean size: "+sb.size());
                request.setAttribute("getStudentLessonBeanlist", sb);
                targetURL = "getStudentAttendance.jsp";
            } else {
                targetURL = "studentIndex.jsp";
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(request, response);
        }
    }
}
