<%-- 
    Document   : createClass
    Created on : 2019年12月15日, 下午03:42:37
    Author     : Helen&Nangen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Setup a new class</h2>
        <form method="get" action="handleEditClass">
            <input type="hidden" name="action" value="createClass" />
            Class ID <input type="text" name="id" /> <br>
            Class name <input type="text" name="name" /> <br>
            <input type="submit" value="Create class" />
           <input type="reset" value="Clear all" />
        </form>
    </body>
</html>
