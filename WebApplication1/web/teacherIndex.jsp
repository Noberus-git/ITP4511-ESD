<%-- 
    Document   : teacherIndex
    Created on : 2019年12月5日, 下午02:09:36
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
        
        <jsp:useBean id="teacherBean" class="ict.bean.teacherBean" scope="session"/>
            Welcome
                <jsp:getProperty name="teacherBean" property="name" />
                <br>
                
                <!--view what teach teach and what class he handle -->
                <!--Function 1 -->
                <a href="handleTeacherController?action=viewSchedule& tId=<jsp:getProperty name="teacherBean" property="tid" />">Class Schedule</a>
                <!-- function2 -->
                <a href="handleTeacherController?action=viewSubject& tId=<jsp:getProperty name="teacherBean" property="tid" />">View your Course</a>
        
    </body>
</html>
