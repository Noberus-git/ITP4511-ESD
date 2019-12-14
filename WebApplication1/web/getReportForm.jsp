<%-- 
    Document   : getReportForm
    Created on : 2019年12月13日, 上午01:49:54
    Author     : Laughing Lam
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
            <a class="nav-link text-white " href="teacherIndex.jsp" />Student attendance monitoring system</a>

</nav>
        <div class="container">
        Teacher Name: <jsp:getProperty name="teacherBean" property="name" /> <br>
        Teacher ID: <jsp:getProperty name="teacherBean" property="tid" />
        
        <br>Generate Report
        <form action="GenerateReportController" method="Get" >
            
            <input type="hidden" name="action" value="generateReport" >
            <input type="hidden" name="tid" value="<jsp:getProperty name="teacherBean" property="tid" />">
            <br>
            Class ID: <input type="text" name="classID" class="form">
            <br> <br>
            Subject ID: <input type="text" name="subjectID" class="form">
            <br>
            
             <input type="hidden" name="specialRequest" value="normal" checked>
            
             <br><input type="submit" value="Generate" class="btn btn-primary">
             
        </form></div>
    </body>
</html>
