<%-- 
    Document   : classSchedule
    Created on : 2019年12月6日, 上午12:09:59
    Author     : Laughing Lam
--%>

<%@page import="ict.bean.scheduleBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Class schedule</h1>
        
        <%
            ArrayList<scheduleBean> sbs = (ArrayList<scheduleBean>) request.getAttribute("ScheduleBeans");
            
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th> Lid</th> <th> Subject name</th><th> Class name</th ><th> Date</th><th> Mark attendance</th>");
            out.println("</tr>");
// loop through the customer array to display each customer record

            for (int i = 0; i < sbs.size(); i++) {
                scheduleBean sb = sbs.get(i);
                out.println("<tr>");
                out.println("<td>" + sb.getLid() + "</td>");
                out.println("<td>" + sb.getSubjectName() + "</td>");
                
                out.println("<td>" + sb.getClassName() + "</td>");
                out.println("<td>" + sb.getDate() + "</td>");
                out.println("<td>" + "<a href='handleTeacherController?action=showStudLesson&Lid="+sb.getLid()+"&Sid="+sb.getSid()+"&Cid="+sb.getCid()+"'>View Classmates</a>"  + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            

        %>
        
    
    </body>
</html>
