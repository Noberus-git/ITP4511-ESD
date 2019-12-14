<%-- 
    Document   : login
    Created on : 2019年12月4日, 上午10:39:58
    Author     : Laughing Lam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="loginController" method="post">
            <input type="hidden" name="action" value="login">
            <br>
            Username:
            <input type="text" name="username" value="">
            <br><br>Password:
            <input type="text" name="password" value="">
            <br><br>Log in as: <br>
            Student<input type="radio" name="userType" value="student">
            Teacher<input type="radio" name="userType" value="teacher">
            Admin<input type="radio" name="userType" value="admin">
            <br><br><input type="submit" value="Log in">
        </form>
    </body>
</html>
