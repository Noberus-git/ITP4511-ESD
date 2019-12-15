<%-- 
    Document   : studentAttendance
    Created on : 2019年12月15日, 下午01:26:09
    Author     : Laughing Lam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.scheduleBean"%>
<%@page import="ict.bean.scheduleBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        ArrayList<scheduleBean> sbs = (ArrayList<scheduleBean>) request.getAttribute("getStudentScheduleBeanlist");
            
            out.println("<table border='1' class='table table-striped'>");
            out.println("<tr>");
            out.println("<th> Sid</th> <th> Subject name</th><th>View attendance</th>");
            out.println("</tr>");


            for (int i = 0; i < sbs.size(); i++) {
                scheduleBean sb = sbs.get(i);
                out.println("<tr>");
                out.println("<td>" + sb.getSid()+ "</td>");
                out.println("<td>" + sb.getSubjectName() + "</td>");
                // hard code studID 
                out.println("<td>" + "<a href='studentContorller?action=viewAttendance&sid="+sb.getSid()+"&studid="+"1"+"'>View attendance</a>"  + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        %>
    </body>
</html>
