<%-- 
    Document   : classSchedule
    Created on : 2019年12月6日, 上午12:09:59
    Author     : Laughing Lam
--%>

<%@page import="ict.bean.scheduleBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Class schedule</h1>

        <%
            ArrayList<scheduleBean> sbs = (ArrayList<scheduleBean>) request.getAttribute("ScheduleBeans");

            //class schudle design
            //setup data
            String[] days = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
            String[] time = {"8-10", "10-12", "13-15", "15-17"};
            String[] timeIndex={"1","2","3","4"};
            String[][] storeLesson = new String[4][5];

            
            for (int i = 0; i < sbs.size(); i++) {
                scheduleBean sb = sbs.get(i);
                int placeWeekDay = 0;
                int placePeriod = Integer.parseInt(sb.getPeriod())-1;

                
                for (int j = 0; j < 5; j++) {
                    if (sb.getWeekDay().equals(days[j + 1])) {

                        placeWeekDay = j;
                        
                    }
                }
 
                storeLesson[placePeriod][placeWeekDay] = sb.getSubjectName() + "(" + sb.getClassName() + ")";

            }

            out.println("<table border='1' >");
            out.println("<tr>");
            for (int i = 0; i < days.length; i++) {
                out.println("<th>" + days[i] + "</th>");
            }
            out.println("</tr>");

            for (int i = 0; i < 4; i++) {
                //scheduleBean sb = sbs.get(i);
                out.println("<tr>");
                out.println("<td>" + time[i] + "</td>");
                
                for (int j = 0; j < 5; j++){
                    if(storeLesson[i][j]==null){
                    out.println("<td></td>");
                    }else{
                    out.println("<td>" + storeLesson[i][j] + "</td>");
                    }
                }
                
                out.println("</tr>");
            }

            out.println("</table>");

        %>


    </body>
</html>
