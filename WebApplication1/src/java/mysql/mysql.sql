/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Laughing Lam
 * Created: 2019年12月6日
 */
--Example

-- insert data to teacher
INSERT INTO `teacher`(`tId`, `name`, `password`, `tel`, `age`) VALUES ("1","jesse","123","12312312","40");
INSERT INTO `teacher`(`tId`, `name`, `password`, `tel`, `age`) VALUES ("2","Nelson","159","12312312","40");

-- insert data to student
INSERT INTO `student`(`studId`, `name`, `cId`, `tel`, `age`) VALUES ("2","Ching","1","12312312","20");


--insert data to studlesson
INSERT INTO `studlesson`(`LId`, `studId`, `sId`, `attendance`) VALUES ("01","1","01",'0');

--insert data to class
INSERT INTO `class`(`cId`, `Classname`) VALUES ("1","1ASE");
INSERT INTO `class`(`cId`, `Classname`) VALUES ("2","1BSE");
INSERT INTO `class`(`cId`, `Classname`) VALUES ("3","2ABA");

--insert data to subject
INSERT INTO `subject`(`sId`, `SubjectName`, `tId`) VALUES ("1","OOT","1");
INSERT INTO `subject`(`sId`, `SubjectName`, `tId`) VALUES ("2","Android & moblie","1");

--insert data to lesson
INSERT INTO `lesson`(`LId`, `sId`, `cId`, `period`, `weekDay`, `Date`) VALUES ("1","1","1","1","Monday","2019-12-20");
INSERT INTO `lesson`(`LId`, `sId`, `cId`, `period`, `weekDay`, `Date`) VALUES ("1","2","1","2","Monday","2019-12-20");
INSERT INTO `lesson`(`LId`, `sId`, `cId`, `period`, `weekDay`, `Date`) VALUES ("2","1","2","4","Wednesday","2019-12-20");

--insert data to Studlesson
INSERT INTO `studlesson`(`LId`, `studId`, `sId`, `attendance`) VALUES ("1","1","1",Null);
INSERT INTO `studlesson`(`LId`, `studId`, `sId`, `attendance`) VALUES ("1","2","1",Null);
