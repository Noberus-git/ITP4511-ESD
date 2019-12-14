<%-- 
    Document   : showReport
    Created on : 2019年12月13日, 下午01:59:11
    Author     : Laughing Lam
--%>


<%@page import="ict.bean.StudentLessonBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.scheduleBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
                <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="nav-link text-white " href="teacherIndex.jsp" />Student attendance monitoring system</a>
</nav>
        <%
            ArrayList<scheduleBean> lessonB = (ArrayList<scheduleBean>) request.getAttribute("getScheduleBeanlist");

            ArrayList less60Stud = new ArrayList();

            scheduleBean basicData = lessonB.get(0);
            out.println("<div class='container'>Subject Name:" + basicData.getSubjectName() + "");

            out.println("Class Name:" + basicData.getClassName() + "");

            out.println("Total lesson:" + lessonB.size() + "");

            out.println("<table  class='table table-striped' >");
            out.println("<tr>");

            out.println("<th> Student Name</th>");
            for (int i = 0; i < lessonB.size(); i++) {
                scheduleBean sb = lessonB.get(i);
                out.println("<th>" + sb.getLid() + "</th>");
            }

            out.println("<th>Attendance</th>");
            out.println("</tr>");

            ArrayList<StudentLessonBean> studlbs = (ArrayList<StudentLessonBean>) request.getAttribute("getStudentLessonBean");
            String sid = studlbs.get(0).getStudID();

            out.println("<tr>");
            out.println("<th>  " + studlbs.get(0).getStudName() + "</th>");

            long studArriveNo = 0;

            for (int j = 0; j < studlbs.size(); j++) {

                StudentLessonBean slb = studlbs.get(j);

                if (!sid.equals(slb.getStudID())) {
                    long studAttendance = studArriveNo;
                    long resultAttendance = studAttendance * 100 / 3;
                    out.println("<th> " + resultAttendance + "%</th>");
                    studArriveNo = 0;
                    if (resultAttendance < 60) {
                        less60Stud.add(studlbs.get(j - 1));
                    }

                    out.println("</tr> ");
                    out.println("<tr>");
                    out.println("<th>  " + slb.getStudName() + "</th>");

                }
                out.println("<th>  " + slb.getAttendance() + "</th>");
                if (slb.getAttendance().equals("1")) {
                    studArriveNo++;
                }

                if (j == studlbs.size() - 1) {
                    long studAttendance = studArriveNo;
                    long resultAttendance = studAttendance * 100 / 3;
                    out.println("<th> " + resultAttendance + "%</th>");
                    if (resultAttendance < 60) {
                        less60Stud.add(studlbs.get(j - 1));
                    }
                    out.println("</tr> ");
                }

                sid = slb.getStudID();
            }

            
            out.println("</table>");

            if (less60Stud.size() == 0) {
                out.println("<br>");
                out.println("All students have enough attendance.");
            } else {
                out.println("<button type='button' class='btn btn-primary' data-toggle='collapse' data-target='#demo'>Only show table which only show the students who Attendance is less than 60%  </button>");
                out.println("<div id='demo' class='collapse'>");
                out.println("These Students's attendance is less than 60%");
                out.println("<table class='table table-striped' >");

                out.println("<tr><th>Student Id</th><th>Student Name</th></tr>");

                for (int i = 0; i < less60Stud.size(); i++) {
                    StudentLessonBean retakeStud = (StudentLessonBean) less60Stud.get(i);

                    out.println("<tr><th> " + retakeStud.getStudID() + "</th>");

                    out.println("<th> " + retakeStud.getStudName() + "</th></tr>");
                }
                out.println("</table>");
            }
            out.println("</div>");
            out.println("</div>");
        %>
        
    </body>
    
</html>
