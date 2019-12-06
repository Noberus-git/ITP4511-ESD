<%-- 
    Document   : viewSubject
    Created on : 2019年12月6日, 上午11:27:58
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
            ArrayList<StudentLessonBean> studlbs = (ArrayList<StudentLessonBean>) request.getAttribute("getStudBean");
            StudentLessonBean basicData =studlbs.get(0);
            
            out.println("Subject Code:"+basicData.getSid() +" Lesson: "+ basicData.getLid()+"<br>");
            out.println("<form action='insertEditAttendanceController' method='Post'>");
            out.println("<input type='hidden' name='action' value='markAttendance'>");
            out.println("<input type='hidden' name='Sid' value='"+basicData.getSid()+"'>");
            out.println("<input type='hidden' name='Lid' value='"+basicData.getLid()+"'>");
            
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th> StudId</th> <th> Student Name</th><th> Mark Attendance</th >");
            out.println("</tr>");
// loop through the customer array to display each customer record
        
            for (int i = 0; i < studlbs.size(); i++) {
                StudentLessonBean sb = studlbs.get(i);
                out.println("<tr>");
                out.println("<td>" + sb.getStudID() + "</td>");
                out.println("<td>" + sb.getStudName() + "</td>");
                out.println("<td>" +  "<input type='checkbox' name='attandance' value='"+sb.getStudID()+"'>" + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<input type='submit' value='confirm'>");
        
            out.println("</form>");
            

        %>
    </body>
</html>
