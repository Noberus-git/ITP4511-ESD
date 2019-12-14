<%-- 
    Document   : getReportForm
    Created on : 2019年12月13日, 上午01:49:54
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
        <a href="teacherIndex.jsp">Back to home page</a> <br>
        Teacher Name: <jsp:getProperty name="teacherBean" property="name" /> <br>
        Teacher ID: <jsp:getProperty name="teacherBean" property="tid" />
        
        <br>Generate Report
        <form action="GenerateReportController" method="Get">
            <input type="hidden" name="action" value="generateReport">
            <input type="hidden" name="tid" value="<jsp:getProperty name="teacherBean" property="tid" />">
            <br>
            Class ID: <input type="text" name="classID">
            <br> <br>
            Subject ID: <input type="text" name="subjectID">
            <br>
            
             <input type="hidden" name="specialRequest" value="normal" checked>
            
             <br><input type="submit" value="Generate">
        </form>
    </body>
</html>
