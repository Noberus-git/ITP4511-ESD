<%-- 
    Document   : studentIndex
    Created on : 2019年12月15日, 下午01:25:46
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
        <!--view attendance record-->
        <a href="studentContorller?studid=<jsp:getProperty  name="studentBean" property="studId" />&action=viewSubjects">View attendance</a>
    </body>
</html>
