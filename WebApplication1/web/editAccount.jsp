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
    teacherBean tb = (teacherBean) request.getAttribute("t");
    adminBean ab = (adminBean) request.getAttribute("a");
    ArrayList<ClassBean> classes = (ArrayList<ClassBean>)request.getAttribute("c");
    String id="", name="", tel="", password="", className="";
    if (type.equals("student")) {
        
        id = sb.getStudId();
        name = sb.getName();
        tel = sb.getTel();
        password = sb.getPassword();
        className = sb.getclassName();
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Edit account</h2>
        <form method="get" action="handleEditAc">
            <input type="hidden" name="action" value="edit" />
           ID <input type="text" name="id" value="<%=id %>"readonly/> <br>
           Username <input type="text" name="name" value="<%=name %>" /> <br>
           Password <input type="text" name="password" value="<%=password %>" /> <br>
           Telephone no. <input type="text" name="tel" value="<%=tel %>" /> <br>
           <% if (type.equals("student")) {
               
               out.println("Class <select name=\"c\">" );
               for (int i = 0; i < classes.size();i++) { 
                   %>
                   <option value ="<%= classes.get(i).getcId() %>"><%=classes.get(i).getClassName() %>
                   </option>
                   <%
               }  
out.println("</select><br><br>");
} %> <br>
           Account type <br>
           Student <input type="radio" name="type" value="student" <% if (type.equals("student")) {
               out.println("checked"); }%> /> 
           Teacher <input type="radio" name="type" value="teacher" <% if (type.equals("teacher")) {
               out.println("checked"); }%>/> 
           Administrator <input type="radio" name="type" value="admin" <% if (type.equals("admin")) {
               out.println("checked"); }%> /> <br>
           <input type="submit" value="Confirm changes" />
           <input type="reset" value="Clear all" />
        </form>
    </body>
</html>
