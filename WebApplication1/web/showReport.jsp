<%-- 
    Document   : showReport
    Created on : 2019年12月13日, 下午01:59:11
    Author     : Laughing Lam
--%>


<%@page import="ict.bean.StudentLessonBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.scheduleBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<scheduleBean> lessonB = (ArrayList<scheduleBean>) request.getAttribute("getScheduleBeanlist");

            ArrayList less60Stud = new ArrayList();

            scheduleBean basicData = lessonB.get(0);
            out.println("Subject Name:" + basicData.getSubjectName() + "<br>");

            out.println("Class Name:" + basicData.getClassName() + "<br>");

            out.println("Total lesson:" + lessonB.size() + "<br>");

            out.println("<table border='1' >");
            out.println("<tr>");

            out.println("<th> Student Name</th>");
            for (int i = 0; i < lessonB.size(); i++) {
                scheduleBean sb = lessonB.get(i);
                out.println("<th>" + sb.getLid() + "</th>");
            }

            out.println("<th>Attendance</th>");
            out.println("</tr>");

            ArrayList<StudentLessonBean> studlbs = (ArrayList<StudentLessonBean>) request.getAttribute("getStudentLessonBean");
            String sid = studlbs.get(0).getStudID();

            out.println("<tr>");
            out.println("<th>  "+ studlbs.get(0).getStudName() + "</th>");

            long studArriveNo = 0;

            for (int j = 0; j < studlbs.size(); j++) {

                StudentLessonBean slb = studlbs.get(j);

                if (!sid.equals(slb.getStudID())) {
                    long studAttendance = studArriveNo;
                    long resultAttendance=studAttendance * 100 / 3;
                    out.println("<th> " + resultAttendance + "%</th>");
                    studArriveNo = 0;
                   if (resultAttendance<60) {
                        less60Stud.add(studlbs.get(j-1));
                    }

                    out.println("</tr> ");
                    out.println("<tr>");
                    out.println("<th>  " +  slb.getStudName() + "</th>");

                }
                out.println("<th>  " + slb.getAttendance() + "</th>");
                if (slb.getAttendance().equals("1")) {
                    studArriveNo++;
                }

                if (j == studlbs.size() - 1) {
                    long studAttendance = studArriveNo;
                    long resultAttendance=studAttendance * 100 / 3;
                    out.println("<th> " + resultAttendance + "%</th>");
                    if (resultAttendance<60) {
                        less60Stud.add(studlbs.get(j-1));
                    }
                    out.println("</tr> ");
                }

                sid = slb.getStudID();
            }

            
            out.println("<div id='showLess60Table' style='display: none'>");
            out.println("</table>");

            out.println("These Students's attendance is less than 60%");
            out.println("<table border='1'>");
            
            out.println("<tr><th>Student Id</th><th>Student Name</th></tr>");
            for(int i = 0; i < less60Stud.size(); i++){
                StudentLessonBean retakeStud = (StudentLessonBean) less60Stud.get(i);
                
                  out.println("<tr><th> " + retakeStud.getStudID()+ "</th>");
                  
                  out.println("<th> " + retakeStud.getStudName() + "</th></tr>");
            }
            out.println("</table>");
            out.println("</div>");
        %>
    </body>
</html>
