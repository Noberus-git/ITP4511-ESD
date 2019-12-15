<%-- 
    Document   : studentAttendance
    Created on : 2019年12月15日, 下午01:26:09
    Author     : Laughing Lam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.scheduleBean"%>
<%@page import="ict.bean.scheduleBean"%>
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
        ArrayList<scheduleBean> sbs = (ArrayList<scheduleBean>) request.getAttribute("getStudentScheduleBeanlist");
            String studid = request.getParameter("studid");
            out.println("<table border='1' class='table table-striped'>");
            out.println("<tr>");
            out.println("<th> Sid</th> <th> Subject name</th><th>View attendance</th>");
            out.println("</tr>");


            for (int i = 0; i < sbs.size(); i++) {
                scheduleBean sb = sbs.get(i);
                out.println("<tr>");
                out.println("<td>" + sb.getSid()+ "</td>");
                out.println("<td>" + sb.getSubjectName() + "</td>");
                 
                out.println("<td>" + "<a href='studentContorller?action=viewAttendance&sid="+sb.getSid()+"&studid="+studid+"'>View attendance</a>"  + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        %>
    </body>
</html>
