<%-- 
    Document   : createAccount
    Created on : 2019年12月13日, 下午01:31:38
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
