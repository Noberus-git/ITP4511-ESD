<%-- 
    Document   : createClass
    Created on : 2019年12月15日, 下午03:42:37
    Author     : Helen&Nangen
--%>

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
    <div class="container">
        <h2>Setup a new class</h2>
        
        <form method="get" action="handleEditClass">
            <input type="hidden" name="action" value="createClass" />
            Class ID <input type="text" name="id" /> <br>
            Class name <input type="text" name="name" /> <br><br>
            <input type="submit" value="Create class" />
           <input type="reset" value="Clear all" />
        </form>
        </div>
    </body>
</html>
