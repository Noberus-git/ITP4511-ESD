<%-- 
    Document   : getStudentAttendance
    Created on : 2019年12月15日, 下午01:57:11
    Author     : Laughing Lam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.StudentLessonBean"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
                // hard code studID 
                out.println("<td>" + sb.getAttendance()+ "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        %>
    </body>
</html>
