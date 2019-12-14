<%-- 
    Document   : teacherIndex
    Created on : 2019年12月5日, 下午02:09:36
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
            <h5 class="text-muted">Student attendance monitoring system</h5>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
          <!--view what teach teach and what class he handle -->
          <!--Function 1 show class schedule-->
        <a class="nav-link text-white " href="handleTeacherController?action=viewSchedule&tid=<jsp:getProperty name="teacherBean" property="tid" />">Class Schedule</a>
      </li>
      <li class="nav-item">
          <!-- function2 mark attendance-->
        <a class="nav-link text-white" href="handleTeacherController?action=viewLesson&tid=<jsp:getProperty name="teacherBean" property="tid" />">Mark attendance</a>
      </li>
      <li class="nav-item">
          <!-- function3 generate report -->
        <a class="nav-link text-white" href="getReportForm.jsp">Generate Report</a>
      </li>    
    </ul>
  </div>  
</nav>
    Welcome to

        <jsp:useBean id="teacherBean" class="ict.bean.teacherBean" scope="session"/>
            Welcome
                <jsp:getProperty name="teacherBean" property="name" />
                <br>


    </body>
</html>
