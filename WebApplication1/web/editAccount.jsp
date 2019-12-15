<%-- 
    Document   : editAccount
    Created on : 2019年12月14日, 下午12:58:27
    Author     : Helen&Nangen
--%>
<%@page import="ict.bean.adminBean"%>
<%@page import="ict.bean.teacherBean"%>
<%@page import="ict.bean.StudentBean"%>

<%@page import="java.util.ArrayList" %>
<%@page import="ict.bean.ClassBean" %>

<%
    String type = request.getParameter("type");
    StudentBean sb = (StudentBean) request.getAttribute("s");
    StudentBean nsb = (StudentBean) request.getAttribute("sb");
    teacherBean tb = (teacherBean) request.getAttribute("t");
    adminBean ab = (adminBean) request.getAttribute("a");
    ArrayList<ClassBean> classes = (ArrayList<ClassBean>) request.getAttribute("c");
    String id = "", name = "", tel = "", password = "", className = "";
    if (type.equals("student")) {

        id = sb.getStudId();
        name = sb.getName();
        tel = sb.getTel();
        password = sb.getPassword();
        className = sb.getclassName();
    } else if (type.equals("newStudent")) {
        id = nsb.getStudId();
        name = nsb.getName();
        tel = nsb.getTel();
        password = nsb.getPassword();
        className = nsb.getclassName();
    } else if (type.equals("teacher")) {
        id = tb.getTid();
        name = tb.getName();
        tel = tb.getTel();
        password = tb.getPassword();
    } else if (type.equals("admin")) {
        id = ab.getaId();
        name = ab.getName();
        tel = ab.getTel();
        password = ab.getPassword();
    }
%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="nav-link text-white " href="adminIndex.jsp" />Student attendance monitoring system</a>

    </nav>
    <div class="container">
        <h2>Edit account</h2>
        <form method="get" action="handleEditAc">
            <input type="hidden" name="action" value="edit" />
            ID <input type="text" name="id" value="<%=id%>"readonly/> <br>
            Username <input type="text" name="name" value="<%=name%>" /> <br>
            Password <input type="text" name="password" value="<%=password%>" /> <br>
            Telephone no. <input type="text" name="tel" value="<%=tel%>" /> <br>
            <% if (type.equals("student") || type.equals("newStudent")) {

                    out.println("Class <select name=\"c\">");
                    for (int i = 0; i < classes.size(); i++) {
            %>
            <option value ="<%= classes.get(i).getcId()%>"<% if (type.equals("student")
                        && className.equals(classes.get(i).getClassName())) {
                    out.println("selected");
                }

                    %>>
                <%=classes.get(i).getClassName()%>
            </option>
            <%
                    }
                    out.println("</select><br><br>");
                } %> <br>
            Account type <br>
            Student <input type="radio" name="type" value="student" <% if (type.equals("student")
                        || type.equals("newStudent")) {
                    out.println("checked");
            }%> /> 
            Teacher <input type="radio" name="type" value="teacher" <% if (type.equals("teacher")) {
                               out.println("checked");
                           }%>/> 
            Administrator <input type="radio" name="type" value="admin" <% if (type.equals("admin")) {
                                     out.println("checked");
                                 }%> /> <br>
            <input type="submit" value="Confirm changes" />
            <input type="reset" value="Clear all" />
        </form>
    </div>
</body>
</html>
