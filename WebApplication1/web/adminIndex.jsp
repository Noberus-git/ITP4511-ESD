<%-- 
    Document   : adminIndex
    Created on : 2019年12月10日, 下午09:51:21
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="adminBean" class="ict.bean.adminBean" scope="session"/>
        Welcome
        <jsp:getProperty name="adminBean" property="name" /> <br>    
        
        <!--Function 1: create account -->
        <a href="handleAdminController?action=createAccount" >Create account</a>
    </body>
</html>
