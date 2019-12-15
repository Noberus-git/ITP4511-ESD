<%-- 
    Document   : adminIndex
    Created on : 2019年12月10日, 下午09:51:21
    Author     : 
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
        <jsp:useBean id="adminBean" class="ict.bean.adminBean" scope="session"/>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <h5 class="text-muted">Student attendance monitoring system</h5>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <!--Function 1: create account -->
                        <a class="nav-link text-white " href="handleAdminController?action=createAccount" >Create account</a>
                    </li>
                    <li class="nav-item">
                        <!--Function 2: modify account and role -->
                        <a class="nav-link text-white" href="handleAdminController?action=list" >Modify account</a> 
                    </li>
                    <li class="nav-item">
                        <!--Function 3: setup class -->
                        <a class="nav-link text-white" href="handleAdminController?action=createClass" >Setup new class</a> 
                    </li>
                </ul>
            </div>
        </nav>
        Welcome
        <jsp:getProperty name="adminBean" property="name" /> <br>    


    </body>
</html>
