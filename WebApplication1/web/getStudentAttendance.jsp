<%-- 
    Document   : getStudentAttendance
    Created on : 2019年12月15日, 下午01:57:11
    Author     : Laughing Lam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.StudentLessonBean"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="nav-link text-white " href="studentIndex.jsp" />Student attendance monitoring system</a>
            
  
</nav>
        <%
        ArrayList<StudentLessonBean> sbs = (ArrayList<StudentLessonBean>) request.getAttribute("getStudentLessonBeanlist");
            
            out.println("<table border='1' class='table table-striped'>");
            out.println("<tr>");
            out.println("<th> Sid</th><th>Lid</th> <th> Subject name</th><th>Attendance</th>");
            out.println("</tr>");


            for (int i = 0; i < sbs.size(); i++) {
                StudentLessonBean sb = sbs.get(i);
                out.println("<tr>");
                out.println("<td>" + sb.getSid()+ "</td>");
                out.println("<td>" + sb.getLid()+ "</td>");
                out.println("<td>" + sb.getSName() + "</td>");
                if (sb.getAttendance().equals("0"))
                    out.println("<td>Present</td>");
                if (sb.getAttendance().equals("1"))
                    out.println("<td>Absent</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        %>
    </body>
</html>
