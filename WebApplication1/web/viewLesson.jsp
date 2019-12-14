<%-- 
    Document   : classSchedule
    Created on : 2019年12月6日, 上午12:09:59
    Author     : Laughing Lam
--%>

<%@page import="ict.bean.scheduleBean"%>
<%@page import="java.util.ArrayList"%>
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
            <a class="nav-link text-white " href="teacherIndex.jsp" />Student attendance monitoring system</a>
            
  
</nav>
        <h1>Mark lesson attendance</h1>
        
        <%
            ArrayList<scheduleBean> sbs = (ArrayList<scheduleBean>) request.getAttribute("ScheduleBean");
            
            out.println("<table border='1' class='table table-striped'>");
            out.println("<tr>");
            out.println("<th> Lid</th> <th> Subject name</th><th> Class name</th ><th> Date</th><th> Mark attendance</th>");
            out.println("</tr>");


            for (int i = 0; i < sbs.size(); i++) {
                scheduleBean sb = sbs.get(i);
                out.println("<tr>");
                out.println("<td>" + sb.getLid() + "</td>");
                out.println("<td>" + sb.getSubjectName() + "</td>");
                
                out.println("<td>" + sb.getClassName() + "</td>");
                out.println("<td>" + sb.getDate() + "</td>");
                out.println("<td>" + "<a href='handleTeacherController?action=showStudLesson&Lid="+sb.getLid()+"&Sid="+sb.getSid()+"&cid="+sb.getCid()+"'>View/Edit Class</a>"  + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            
            
        %>
        
    
    </body>
</html>
