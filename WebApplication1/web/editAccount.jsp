<%-- 
    Document   : editAccount
    Created on : 2019年12月14日, 下午12:58:27
    Author     : Helen&Nangen
--%>
<%--<jsp:useBean id="c" scope="request" class="ict.bean.CustomerBean" />--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Create a new account</h2>
        <form method="get" action="handleEditAc">
            <input type="hidden" name="action" value="add" />
           ID <input type="text" name="id" /> <br>
           Username <input type="text" name="name" /> <br>
           Password <input type="text" name="password" /> <br>
           Telephone no. <input type="text" name="tel" /> <br>
           Account type <br>
           Student <input type="radio" name="type" id="student" checked/> 
           Teacher <input type="radio" name="type" id="teacher" /> 
           Administrator <input type="radio" name="type" id="admin" /> <br>
           <input type="submit" value="Create account" />
           <input type="reset" value="Clear all" />
        </form>
    </body>
</html>
