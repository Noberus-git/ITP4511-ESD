<%-- 
    Document   : showReport
    Created on : 2019年12月13日, 下午01:59:11
    Author     : Laughing Lam
--%>


<%@page import="java.util.ArrayList"%>
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
            ArrayList<scheduleBean> studlbs = (ArrayList<scheduleBean>) request.getAttribute("getScheduleBeanlist");
            scheduleBean basicData=studlbs.get(0);
            out.println("Subject Name:"+basicData.getSubjectName() +"<br>");
            
            out.println("Class Name:"+basicData.getClassName() +"<br>");
            out.println("<table border='1' >");
           out.println("<tr>");
           
            out.println("<th></th>");
            for(int i=0; i<studlbs.size();i++){
                
                scheduleBean sb=studlbs.get(i);
                out.println("<th>" +sb.getLid() + "</th>");
            }
            out.println("</tr>");
            out.println("</table>");

        %>
    </body>
</html>
