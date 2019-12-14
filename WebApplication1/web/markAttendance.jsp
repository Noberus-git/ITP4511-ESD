<%-- 
    Document   : viewSubject
    Created on : 2019年12月6日, 上午11:27:58
    Author     : Laughing Lam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.StudentLessonBean"%>
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
        <%
            ArrayList<StudentLessonBean> studlbs = (ArrayList<StudentLessonBean>) request.getAttribute("getStudBean");
            StudentLessonBean basicData =studlbs.get(0);
            
            out.println("Subject Code:"+basicData.getSid() +" Lesson: "+ basicData.getLid()+"<br>");
            out.println("Mark attendance:");
            out.println("<form action='insertEditAttendanceController' method='Post'>");
            out.println("<input type='hidden' name='action' value='markAttendance'>");
            out.println("<input type='hidden' name='Sid' value='"+basicData.getSid()+"'>");
            out.println("<input type='hidden' name='Lid' value='"+basicData.getLid()+"'>");
            
            out.println("<table border='1' class='table table-striped'>");
            out.println("<tr>");
            out.println("<th> StudId</th> <th> Student Name</th><th>Arrive</th ><th>ABS</th >");
            out.println("</tr>");
// loop through the customer array to display each customer record
        
            for (int i = 0; i < studlbs.size(); i++) {
                
               StudentLessonBean sb = studlbs.get(i);
                String arrive="";
                String abs="";
                
                if(sb.getAttendance()==null){
                    
                    
                }else if(sb.getAttendance().equals("1")){
                    arrive="checked";
                }else if(sb.getAttendance().equals("0")){
                    abs="checked";
                }
         
               out.println("<tr>");
                out.println("<td>" + sb.getStudID() + "</td>");
                out.println("<td>" + sb.getStudName() + "</td>");
                out.println("<td>" +  "<input type='radio' name='attandance"+i+"' value='1'"+arrive+">" 
                        + "</td>"+"<td>" +  "<input type='radio' name='attandance"+i+"' value='0'"+abs+" >" + "</td>");
                
                
                out.println(  "<input type='hidden' name='studID' value='"+sb.getStudID()+"'>");
                out.println("</tr>");
            }
//
            out.println("</table>");
            out.println("<input type='submit' value='confirm'>");
        
           out.println("</form>");
            

        %>
    </body>
</html>
