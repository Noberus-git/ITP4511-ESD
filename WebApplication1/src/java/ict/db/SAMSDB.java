package ict.db;

import ict.bean.ClassBean;
import ict.bean.StudentBean;
import ict.bean.StudentLessonBean;
import ict.bean.SubjectClassReportBean;
import ict.bean.adminBean;
import ict.bean.lessonBean;
import ict.bean.scheduleBean;
import ict.bean.subjectBean;
import ict.bean.teacherBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laughing Lam
 */
public class SAMSDB {

    String dbUrl;
    String dbUser;
    String dbPassword;

    public SAMSDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            //  System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SAMSDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public void createDB(String createDB) {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();  // the connection 
            stmnt = cnnct.createStatement();  // create statement
            String sql
                    = "CREATE DATABASE " + createDB;
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // create user table
    public void createTables() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();  // the connection 
            stmnt = cnnct.createStatement();  // create statement

            String sql
                    = "CREATE TABLE IF NOT EXISTS `Class` ("
                    + "cId varchar(10) NOT NULL,"
                    + "Classname varchar(25) NOT NULL,"
                    + "PRIMARY KEY (cId)"
                    + ");";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Student` ("
                    + "studId varchar(10) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "password varchar(25) NOT NULL,"
                    + "cId varchar(10) NOT NULL,"
                    + "tel varchar(10) NOT NULL,"
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY (studId),"
                    + "FOREIGN KEY (cId) REFERENCES Class(cId)"
                    + ");";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Teacher` ("
                    + "tId varchar(10) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "password varchar(25) NOT NULL,"
                    + "tel varchar(10) NOT NULL,"
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY (tId)"
                    + ");";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Admin` ("
                    + "aId varchar(10) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "tel varchar(10) NOT NULL,"
                    + "password varchar(25) NOT NULL,"
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY (aId)"
                    + ");";
            stmnt.execute(sql);
            sql
                    = "CREATE TABLE IF NOT EXISTS `Subject` ("
                    + "sId varchar(10) NOT NULL,"
                    + "SubjectName varchar(10) NOT NULL,"
                    + "tId varchar(10) NOT NULL,"
                    + "PRIMARY KEY (sId),"
                    + "FOREIGN KEY (tId) REFERENCES Teacher(tId))";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `Lesson` ("
                    + "LId varchar(10) NOT NULL,"
                    + "sId varchar(10) NOT NULL,"
                    + "cId varchar(10) NOT NULL,"
                    + "period varchar(10) NOT NULL,"
                    + "weekDay varchar(10) NOT NULL,"
                    + "Date date NOT NULL,"
                    + "PRIMARY KEY (LId,sId,cId),"
                    + "FOREIGN KEY (sId) REFERENCES Subject(sId),"
                    + "FOREIGN KEY (cId) REFERENCES Class(cId));";
            stmnt.execute(sql);

            sql
                    = "CREATE TABLE IF NOT EXISTS `StudLesson` ("
                    + "LId varchar(10) NOT NULL,"
                    + "studId varchar(10) NOT NULL,"
                    + "sId varchar(10) NOT NULL,"
                    + "attendance varchar(10) NULL,"
                    + "PRIMARY KEY (LId,sId,studId),"
                    + " FOREIGN KEY (LId) REFERENCES Lesson(LId),"
                    + " FOREIGN KEY (sId) REFERENCES Subject(sId),"
                    + "FOREIGN KEY (studId) REFERENCES Student(studId));";
            stmnt.execute(sql);

            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean teacherLoginIsValid(String tid, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            //get max sid
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  `teacher` where tid=? and password=?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, tid);
            pStmnt.setString(2, password);

            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                // set the record detail to the customer bean

                isSuccess = true;

            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean adminLoginIsValid(String aId, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            //get max sid
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  `admin` where aId=? and password=?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, aId);
            pStmnt.setString(2, password);

            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                // set the record detail to the customer bean
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean addAccount(String id, String name, String tel, String password, String type) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            
            if (type.equals("student")) {
               String preQueryStatement = "INSERT INTO `STUDENT` VALUES (?,?,?,Null,?);";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, id);
                pStmnt.setString(2, name);
                pStmnt.setString(3, password);
                pStmnt.setString(4, tel);
                int rowCount = pStmnt.executeUpdate();
                if (rowCount >= 1) {
                    isSuccess = true;
                }
                pStmnt.close();
                cnnct.close();
            }
            if (type.equals("admin")) {
                String preQueryStatement = "INSERT INTO `ADMIN` VALUES (?,?,?,?);";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, id);
                pStmnt.setString(2, name);
                pStmnt.setString(3, tel);
                pStmnt.setString(4, password);
                int rowCount = pStmnt.executeUpdate();
                if (rowCount >= 1) {
                    isSuccess = true;
                }
                pStmnt.close();
                cnnct.close();
            }
            if (type.equals("teacher")) {
                String preQueryStatement = "INSERT INTO `TEACHER` VALUES (?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, id);
                pStmnt.setString(2, name);
                pStmnt.setString(3, password);
                pStmnt.setString(4, tel);
                int rowCount = pStmnt.executeUpdate();
                if (rowCount >= 1) {
                    isSuccess = true;
                }
                pStmnt.close();
                cnnct.close();
            }  
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public teacherBean getteacherBeanByTid(String tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        teacherBean tb = new teacherBean();

        try {
            //get max sid
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  `teacher` where tid=?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, tid);

            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                // set the record detail to the customer bean
                tb.setTid(rs.getString(1));
                tb.setName(rs.getString(2));

            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tb;
    }

    public adminBean getAdminBeanByaId(String tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        adminBean ab = new adminBean();

        try {
            //get max sid
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  `admin` where aId=?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, tid);

            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                // set the record detail to the customer bean
                ab.setaId(rs.getString(1));
                ab.setName(rs.getString(2));
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ab;
    }
    // use it to show class schedule

    public ArrayList getScheduleBeanByTid(String tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT * FROM `class`, `lesson` , `teacher`,`subject` "
                    + "WHERE `teacher`.`tId`=`subject`.`tId` AND "
                    + "`lesson`.`sId`=`subject`.`sId` AND"
                    + " `class`.`cId`= `Lesson`.`cId` AND `teacher`.`tId`=" + tid + " ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                scheduleBean sb = new scheduleBean();
                // set the record detail to the customer bean
                sb.setCid(rs.getString("cid"));
                sb.setClassName(rs.getString("Classname"));
                sb.setLid(rs.getString("Lid"));
                sb.setPeriod(rs.getString("period"));
                sb.setWeekDay(rs.getString("weekDay"));
                sb.setSubjectName(rs.getString("SubjectName"));
                sb.setSid(rs.getString("sId"));

                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;
    }
    
    //student get subject
public ArrayList getScheduleBeanBysId(String studid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT DISTINCT(`studlesson`.`sId`),`subject`.`SubjectName` "
                    + "FROM `studlesson`,`subject` WHERE `studlesson`.`sId`=`subject`.`sId` "
                    + "AND `studlesson`.`studId`="+studid+"";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                scheduleBean sb = new scheduleBean();

                sb.setSubjectName(rs.getString("SubjectName"));
                sb.setSid(rs.getString("sId"));

                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;
    }




//show lesson you have and the lesson detail
    public ArrayList getScheduleBeanByTidToShowLesson(String tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT * FROM `class`, `lesson` , `teacher`,`subject` "
                    + "WHERE `teacher`.`tId`=`subject`.`tId` AND "
                    + "`lesson`.`sId`=`subject`.`sId` AND"
                    + " `class`.`cId`= `Lesson`.`cId` AND `teacher`.`tId`= " + tid + ";";

            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                scheduleBean sb = new scheduleBean();
                // set the record detail to the customer bean
                sb.setCid(rs.getString("cid"));
                sb.setClassName(rs.getString("Classname"));
                sb.setLid(rs.getString("Lid"));
                sb.setDate(rs.getString("Date"));
                sb.setSubjectName(rs.getString("SubjectName"));
                sb.setSid(rs.getString("sId"));

                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;

    }

    //show lessons on report 
    public ArrayList getScheduleBeanByTidAndCidAndSiDToShowLesson(String tid, String cid, String sid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT * FROM `class`, `lesson` , `teacher`,`subject` \n"
                    + "                    WHERE `teacher`.`tId`=`subject`.`tId` AND \n"
                    + "                    `lesson`.`sId`=`subject`.`sId` AND\n"
                    + "                     `class`.`cId`= `Lesson`.`cId` AND `teacher`.`tId`=  " + tid + " \n"
                    + "                    And `class`.`cId`=" + cid + " AND `subject`.`sId`=" + sid + ";";

            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                scheduleBean sb = new scheduleBean();
                // set the record detail to the customer bean
                sb.setCid(rs.getString("cid"));

                sb.setClassName(rs.getString("Classname"));
                sb.setLid(rs.getString("Lid"));

                sb.setSubjectName(rs.getString("SubjectName"));
                sb.setSid(rs.getString("sId"));

                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;

    }
    //report
    //show Student Lesson attendance 
    public ArrayList getStudentLessonBeanByTidAndCidAndSiD(String tid, String cid, String sid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement

//                    = "SELECT * FROM `studlesson` ,`subject`,`student` "
//                    + "WHERE `studlesson`.`sid`=`subject`.`sid` AND `studlesson`.`studId`=`student`.`studId`"
//                    + "AND `subject`.`tId`= "+tid+" And `student`.`cId`="+cid+" AND `subject`.`sId`="+sid+" "
//                    + "ORDER BY `studlesson`.`studId`,`studlesson`.`LId` ASC";
                    ="SELECT * FROM  `studlesson` ,`subject`,`student`   "
                    + "WHERE `studlesson`.`sid`=`subject`.`sid`  "
                    + "AND `studlesson`.`studId`=`student`.`studId`"
                    + "AND `subject`.`tId`=  "+tid+"  And `student`.`cId`="+cid+" AND `subject`.`sId`="+sid+"ORDER BY  `studlesson`.`studId`,`studlesson`.`LId` ASC;";


            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                StudentLessonBean sb = new StudentLessonBean();
                // set the record detail to the StudentLessonBean 
                sb.setStudID(rs.getString("studID"));
                sb.setStudName(rs.getString("name"));
                sb.setLid(rs.getString("LId"));
                sb.setSid(rs.getString("sId"));
                sb.setAttendance(rs.getString("attendance"));

                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;

    }
    //student get their attendance
        public ArrayList getStudentLessonBeanBysidAndStudid( String sid,String studid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement
                    ="SELECT * FROM `studlesson` ,`subject`,`student` WHERE `studlesson`.`sid`=`subject`.`sid` "
                    + "AND `studlesson`.`studId`=`student`.`studId`And `studlesson`.`studId`="+studid+" "
                    + "AND `subject`.`sId`="+sid+" ORDER BY `studlesson`.`studId`,`studlesson`.`LId` ASC";


            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                StudentLessonBean sb = new StudentLessonBean();
                // set the record detail to the StudentLessonBean 
                sb.setStudID(rs.getString("studID"));
                sb.setStudName(rs.getString("name"));
                sb.setSName(rs.getString("SubjectName"));
                sb.setLid(rs.getString("LId"));
                sb.setSid(rs.getString("sId"));
                sb.setAttendance(rs.getString("attendance"));

                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;

    }
    public ArrayList showReport(String tid, String cid, String sid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement

                    = "SELECT * FROM `studlesson` ,`subject`,`student` "
                    + "WHERE `studlesson`.`sid`=`subject`.`sid` AND `studlesson`.`studId`=`student`.`studId`"
                    + "AND `subject`.`tId`= "+tid+" And `student`.`cId`="+cid+" AND `subject`.`sId`="+sid+" "
                    + "ORDER BY `studlesson`.`studId`,`studlesson`.`LId` ASC";
/*                    ="SELECT * FROM  `studlesson` ,`subject`,`student`   "
                    + "WHERE `studlesson`.`sid`=`subject`.`sid`  "
                    + "AND `studlesson`.`studId`=`student`.`studId`"
                    + "AND `subject`.`tId`=  "+tid+"  And `student`.`cId`="+cid+" AND `subject`.`sId`="+sid+"ORDER BY  `studlesson`.`studId`,`studlesson`.`LId` ASC;";
*/

            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                StudentLessonBean sb = new StudentLessonBean();
                // set the record detail to the StudentLessonBean 
                sb.setStudID(rs.getString("studID"));
                sb.setStudName(rs.getString("name"));
                sb.setLid(rs.getString("LId"));
                sb.setSid(rs.getString("sId"));
                sb.setAttendance(rs.getString("attendance"));

                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;
    }
    
    
    

    public ArrayList getSubjectBeanByTid(String tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT * FROM `subject` "
                    + "WHERE  `subject`.`tId`=" + tid + " ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                subjectBean sb = new subjectBean();
                // set the record detail to the customer bean
                sb.setTid(rs.getString("tId"));
                sb.setSubjectName(rs.getString("SubjectName"));
                sb.setSid(rs.getString("sId"));

                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;
    }
    //mark attendance
    //show a student detail to mark attendance 
    public ArrayList getStudBean(String Lid, String Sid, String cid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT * FROM `studlesson`, `Student` "
                    + "WHERE  `student`.`studId`=`studlesson`.`studId` and `studlesson`.`Sid`=" + Sid
                    + " And `studlesson`.`Lid`=" + Lid + " And `student`.`cId`=" + cid + "";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                StudentLessonBean sb = new StudentLessonBean();
                // set the record detail to the customer bean
                sb.setStudID(rs.getString("studId"));
                sb.setStudName(rs.getString("name"));
                sb.setLid(Lid);
                sb.setSid(Sid);
                sb.setAttendance(rs.getString("attendance"));
                arrayRs.add(sb);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayRs;
    }
    //mark attendance
    // CURD => U
    public boolean makeAttendance(String Sid, String Lid, String studId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            //update studLesson table to take student's attendance
            String preQueryStatement
                    = "Update `studlesson` set `attendance`='1' "
                    + "where LId=" + Lid + " and sId=" + Sid + " and studId=" + studId + ";";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean cancelAttendance(String Sid, String Lid, String studId) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {

            cnnct = getConnection();
            //update studLesson table to take student's attendance
            String preQueryStatement
                    = "Update `studlesson` set `attendance`='0' "
                    + "where LId=" + Lid + " and sId=" + Sid + " and studId=" + studId + ";";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public ArrayList showAllClassbytid(String tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList arrayRs = new ArrayList();
        try {

            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT DISTINCT(`subject`.`sId`),`cId`,`Classname`,`SubjectName` FROM `class`, `lesson` , `teacher`,`subject`"
                    + "WHERE `teacher`.`tId`=`subject`.`tId` AND `lesson`.`sId`=`subject`.`sId` AND `class`.`cId`= `Lesson`.`cId`  AND `teacher`.`tId`= " + tid + ";";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                SubjectClassReportBean sb = new SubjectClassReportBean();
                // set the record detail to the customer bean
                sb.setClassID(rs.getString("cId"));
                sb.setSid(rs.getString("sId"));
                sb.setClassName(rs.getString("Classname"));
                sb.setSubjectName(rs.getString("SubjectName"));
                int rsAttendance = 0;
                rsAttendance = calculateAttendance(rs.getString("sId"), rs.getString("cId"), tid);
                sb.setAttendance(rsAttendance);
                arrayRs.add(sb);
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return arrayRs;
    }

    public int calculateAttendance(String Sid, String cid, String tid) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int classPercent = 0;
        int maxStudentNo = 0;
        int arriveStudentNo = 0;

        try {
            //get max stud
            //SELECT * FROM `teacher`,`subject`,`studlesson`,`student` WHERE `teacher`.`tId`= `subject`.`tId` AND `student`.`studId`=`studlesson`.`studId`and `teacher`.`tId`= 1 AND `studlesson`.`sId`=1 and `student`.`cId`=1;
            cnnct = getConnection();
            //update studLesson table to take student's attendance
            String preQueryStatement
                    = "SELECT Count(*) FROM `teacher`,`subject`,`studlesson`,`student` "
                    + "WHERE `teacher`.`tId`= `subject`.`tId` AND `student`.`studId`=`studlesson`.`studId`"
                    + "and `teacher`.`tId`= " + tid + " AND `studlesson`.`sId`=" + Sid + " and `student`.`cId`=" + cid + ";";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            //get maxStudentno
            maxStudentNo = Integer.parseInt(rs.getString("Count(*)"));

            preQueryStatement
                    = "SELECT Count(*) FROM `teacher`,`subject`,`studlesson`,`student` "
                    + "WHERE `teacher`.`tId`= `subject`.`tId` AND `student`.`studId`=`studlesson`.`studId`"
                    + "and `teacher`.`tId`= " + tid + " AND `studlesson`.`sId`=" + Sid + " and `student`.`cId`=" + cid + " AND `studlesson`.`attendance`='1' ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs2 = pStmnt.executeQuery();
            arriveStudentNo = Integer.parseInt(rs2.getString("Count(*)"));
            //get arrive studentno

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (maxStudentNo == 0) {
            return classPercent;

        } else {
            classPercent = 100 * arriveStudentNo / maxStudentNo;
        }
        return classPercent;
    }

    public StudentBean queryStudentByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        StudentBean sb = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT studId, name, password, tel, "
                    + "Classname FROM `student`, class WHERE student.cId = class.cId AND studId=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                sb = new StudentBean();
                // set the record detail to the customer bean
                sb.setStudId(rs.getString(1));
                sb.setName(rs.getString(2));
                sb.setPassword(rs.getString(3));
                sb.setTel(rs.getString(4));
                sb.setclassName(rs.getString(5));
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb;
    }
    
    public StudentBean queryNewStudentByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        StudentBean sb = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM `student` WHERE studId=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                sb = new StudentBean();
                // set the record detail to the customer bean
                sb.setStudId(rs.getString(1));
                sb.setName(rs.getString(2));
                sb.setPassword(rs.getString(3));
                sb.setTel(rs.getString(5));
                sb.setclassName("NULL");
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb;
    }

    public adminBean queryAdminByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        adminBean ab = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM `admin` WHERE aId=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                ab = new adminBean();
                // set the record detail to the customer bean
                ab.setaId(rs.getString(1));
                ab.setName(rs.getString(2));
                ab.setTel(rs.getString(3));
                ab.setPassword(rs.getString(4));
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ab;
    }

    public teacherBean queryTeacherByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        teacherBean tb = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM `teacher` WHERE tId=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                tb = new teacherBean();
                // set the record detail to the customer bean
                tb.setTid(rs.getString(1));
                tb.setName(rs.getString(2));
                tb.setPassword(rs.getString(3));
                tb.setTel(rs.getString(4));
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tb;
    }

    public ArrayList queryClass() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  class";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                ClassBean cb = new ClassBean();
                cb.setcId(rs.getString(1));
                cb.setClassName(rs.getString(2));
                list.add(cb);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    public ArrayList queryTeacher() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM `teacher`";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                teacherBean tb = new teacherBean();
                tb.setTid(rs.getString(1));
                tb.setName(rs.getString(2));
                tb.setPassword(rs.getString(3));
                tb.setTel(rs.getString(4));
                list.add(tb);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    public ArrayList queryAdmin() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM `admin`";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                adminBean ab = new adminBean();
                ab.setaId(rs.getString(1));
                ab.setName(rs.getString(2));
                ab.setTel(rs.getString(3));
                ab.setPassword(rs.getString(4));
                list.add(ab);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    public boolean editAccount(String id, String name, String tel, String password, String type, String className) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num=0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "";
            if (type.equals("student")) {
                preQueryStatement = "UPDATE student SET NAME=? ,password=? ,cId=?, tel=? WHERE studId=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, name);
                pStmnt.setString(2, password);
                pStmnt.setString(3, className);
                pStmnt.setString(4, tel);
                pStmnt.setString(5, id);
            } else if (type.equals("admin")) {
                preQueryStatement = "UPDATE admin SET NAME=?, password=?, tel=? WHERE aId=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, name);
                pStmnt.setString(2, password);
                pStmnt.setString(3, tel);
                pStmnt.setString(4, id);
            } else if (type.equals("teacher")) {
                preQueryStatement = "UPDATE teacher SET NAME=?, password=?, tel=? WHERE tId=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, name);
                pStmnt.setString(2, password);
                pStmnt.setString(3, tel);
                pStmnt.setString(4, id);
            }
            //Statement s = cnnct.createStatement();
            num= pStmnt.executeUpdate();
          
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
         return (num == 1) ? true : false;
    }
public ArrayList queryStudent() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT studId, name, password, tel, "
                    + "Classname FROM `student`, class WHERE student.cId = class.cId";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                StudentBean cb = new StudentBean();
                cb.setStudId(rs.getString(1));
                cb.setName(rs.getString(2));
                cb.setPassword(rs.getString(3));
                cb.setTel(rs.getString(4));
                cb.setclassName(rs.getString(5));
                list.add(cb);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    public ArrayList queryNewStudent() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM `student` WHERE cId IS NULL";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                StudentBean sb = new StudentBean();
                sb.setStudId(rs.getString(1));
                sb.setName(rs.getString(2));
                sb.setPassword(rs.getString(3));
                sb.setclassName("NULL");
                sb.setTel(rs.getString(5));
                list.add(sb);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }

    public boolean addClass(String id, String name) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();

            String preQueryStatement = "INSERT INTO `class` VALUES (?,?);";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            pStmnt.setString(2, name);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
}
