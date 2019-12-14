<%-- 
    Document   : listAccounts
    Created on : 2019年12月14日, 下午06:03:34
    Author     : Helen&Nangen
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="ict.bean.StudentBean" %>
<%@page import="ict.bean.teacherBean" %>
<%@page import="ict.bean.adminBean" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<StudentBean> students = (ArrayList<StudentBean>) request.getAttribute("students");
            ArrayList<teacherBean> teachers = (ArrayList<teacherBean>) request.getAttribute("teachers");
            ArrayList<adminBean> admins = (ArrayList<adminBean>) request.getAttribute("admins");
            out.println("<h1>Users list</h1>");
            out.println("<h4>Students</h4>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Id</th>  <th> Name</th><th> Phone number</th><th> Class</th >");
            out.println("</tr>");
            
            for (int i = 0; i < students.size(); i++) {
                StudentBean s = students.get(i);
                out.println("<tr>");

                out.println("<td>" + s.getStudId() + "</td>");
                out.println("<td>" + s.getName() + "</td>");
                out.println("<td>" + s.getTel() + "</td>");
                out.println("<td>" + s.getclassName() + "</td>");
                out.println("<td>" + "<a href=\"handleAdminController?action=getEditAccount&type=student&id=" + s.getStudId() + "\">edit</a>" + "</td>");
                out.println("</tr>");

            }
            out.println("</table>");

            
            out.println("<h4>Teachers</h4>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Id</th>  <th> Name</th><th> Phone number</th>");
            out.println("</tr>");
            
            for (int i = 0; i < teachers.size(); i++) {
                teacherBean t = teachers.get(i);
                out.println("<tr>");

                out.println("<td>" + t.getTid() + "</td>");
                out.println("<td>" + t.getName() + "</td>");
                out.println("<td>" + t.getTel() + "</td>");
                out.println("<td>" + "<a href=\"handleAdminController?action=getEditAccount&type=teacher&id=" + t.getTid() + "\">edit</a>" + "</td>");
                out.println("</tr>");

            }
            out.println("</table>");
            

            out.println("<h4>Administrators</h4>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Id</th>  <th> Name</th><th> Phone number</th>");
            out.println("</tr>");
            
            for (int i = 0; i < admins.size(); i++) {
                adminBean a = admins.get(i);
                out.println("<tr>");

                out.println("<td>" + a.getaId()+ "</td>");
                out.println("<td>" + a.getName()+ "</td>");
                out.println("<td>" + a.getTel()+ "</td>");
                out.println("<td>" + "<a href=\"handleAdminController?action=getEditAccount&type=admin&id=" + a.getaId() + "\">edit</a>" + "</td>");
                out.println("</tr>");

            }
            out.println("</table>");
        %>
    </body>
</html>
