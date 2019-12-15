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
            <a class="nav-link text-white " href="adminIndex.jsp" />Student attendance monitoring system</a>


    </nav>
    <%
        ArrayList<StudentBean> students = (ArrayList<StudentBean>) request.getAttribute("students");
        ArrayList<teacherBean> teachers = (ArrayList<teacherBean>) request.getAttribute("teachers");
        ArrayList<adminBean> admins = (ArrayList<adminBean>) request.getAttribute("admins");
        ArrayList<StudentBean> newStudents = (ArrayList<StudentBean>) request.getAttribute("newStudents");
        out.println("<h1>Users list</h1>");
        out.println("<h4>Students</h4>");
        out.println("<table border='1' class='table table-striped'>");
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

        out.println("<h4>New students</h4>");
        out.println("<table border='1' class='table table-striped'>");
        out.println("<tr>");
        out.println("<th>Id</th>  <th> Name</th><th> Phone number</th>");
        out.println("</tr>");

        for (int i = 0; i < newStudents.size(); i++) {
            StudentBean sb = newStudents.get(i);
            out.println("<tr>");

            out.println("<td>" + sb.getStudId() + "</td>");
            out.println("<td>" + sb.getName() + "</td>");
            out.println("<td>" + sb.getTel() + "</td>");
            out.println("<td>" + "<a href=\"handleAdminController?action=getEditAccount&type=newStudent&id=" + sb.getStudId() + "\">edit</a>" + "</td>");
            out.println("</tr>");

        }
        out.println("</table>");

        out.println("<h4>Teachers</h4>");
        out.println("<table border='1' class='table table-striped'>");
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
        out.println("<table border='1' class='table table-striped'>");
        out.println("<tr>");
        out.println("<th>Id</th>  <th> Name</th><th> Phone number</th>");
        out.println("</tr>");

        for (int i = 0; i < admins.size(); i++) {
            adminBean a = admins.get(i);
            out.println("<tr>");

            out.println("<td>" + a.getaId() + "</td>");
            out.println("<td>" + a.getName() + "</td>");
            out.println("<td>" + a.getTel() + "</td>");
            out.println("<td>" + "<a href=\"handleAdminController?action=getEditAccount&type=admin&id=" + a.getaId() + "\">edit</a>" + "</td>");
            out.println("</tr>");

        }
        out.println("</table>");
    %>
</body>
</html>
