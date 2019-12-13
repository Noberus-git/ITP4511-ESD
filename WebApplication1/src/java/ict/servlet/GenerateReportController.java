/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.StudentLessonBean;
import ict.bean.SubjectClassReportBean;
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
@WebServlet(name = "GenerateReportController", urlPatterns = {"/GenerateReportController"})
public class GenerateReportController extends HttpServlet{
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
        String tid = request.getParameter("tid");
        
        PrintWriter out = response.getWriter();
        out.println("Action: "+action);
        out.println("Tid: "+tid);
        //
        if(action.equals("generateReport")){
            
            String getTid= tid;
            String getClassID= request.getParameter("classID");
            String getSubjectID= request.getParameter("subjectID");
            
            //check do user have special requirment
            boolean sRequirment= false;
            if(request.getParameter("specialRequest").equals("lessThan60")){
                sRequirment=true;
            }
            out.println("<br>ClassID: "+getClassID);
            out.println("<br>SubjectID: "+getSubjectID);
            ArrayList<scheduleBean> sb;
            
            //normal get schedule

            sb = db.getScheduleBeanByTidAndCidAndSiDToShowLesson(getTid,getClassID,getSubjectID);
            
            if (sb != null) {
                out.println("Bean size: "+sb.size());
                request.setAttribute("getScheduleBeanlist", sb);
                targetURL = "showReport.jsp";
            }else {
                targetURL = "teacherIndex.jsp";
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/" + targetURL);
            rd.forward(request, response);
            
        }

    }
}
