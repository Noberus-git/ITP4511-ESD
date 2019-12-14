-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2019 at 06:50 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `4511assignment`
--
CREATE DATABASE IF NOT EXISTS `4511assignment` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `4511assignment`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `aId` varchar(10) NOT NULL,
  `name` varchar(25) NOT NULL,
  `tel` varchar(10) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`aId`, `name`, `tel`, `password`) VALUES
('JohnWong', 'John Wong', '12345678', '1701');

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `cId` varchar(10) NOT NULL,
  `Classname` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`cId`, `Classname`) VALUES
('1', '1ASE');

-- --------------------------------------------------------

--
-- Table structure for table `lesson`
--

CREATE TABLE `lesson` (
  `LId` varchar(10) NOT NULL,
  `sId` varchar(10) NOT NULL,
  `cId` varchar(10) NOT NULL,
  `period` varchar(10) NOT NULL,
  `weekDay` varchar(10) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lesson`
--

INSERT INTO `lesson` (`LId`, `sId`, `cId`, `period`, `weekDay`, `Date`) VALUES
('1', '1', '1', '1', 'Tuesday', '2019-12-20'),
('2', '1', '1', '1', 'Tuesday', '2019-12-27'),
('3', '1', '1', '1', 'Tuesday', '2020-01-04'),
('4', '1', '1', '1', 'Tuesday', '2020-01-11');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studId` varchar(10) NOT NULL,
  `name` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `cId` varchar(10) DEFAULT NULL,
  `tel` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studId`, `name`, `password`, `cId`, `tel`) VALUES
('1', 'Ching', '1', '1', '12312312'),
('2', 'C9Wong', '2', '1', '12312312');

-- --------------------------------------------------------

--
-- Table structure for table `studlesson`
--

CREATE TABLE `studlesson` (
  `LId` varchar(10) NOT NULL,
  `studId` varchar(10) NOT NULL,
  `sId` varchar(10) NOT NULL,
  `attendance` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studlesson`
--

INSERT INTO `studlesson` (`LId`, `studId`, `sId`, `attendance`) VALUES
('1', '1', '1', '0'),
('1', '2', '1', '0'),
('2', '1', '1', '0'),
('2', '2', '1', '0'),
('3', '1', '1', '0'),
('3', '2', '1', '0'),
('4', '1', '1', '0'),
('4', '2', '1', '0');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `sId` varchar(10) NOT NULL,
  `SubjectName` varchar(10) NOT NULL,
  `tId` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`sId`, `SubjectName`, `tId`) VALUES
('1', 'OOT', '1'),
('2', 'Android & ', '1');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `tId` varchar(10) NOT NULL,
  `name` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `tel` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`tId`, `name`, `password`, `tel`) VALUES
('1', 'jesse', '123', '12312312'),
('2', 'Nelson', '159', '12312312');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`aId`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`cId`);

--
-- Indexes for table `lesson`
--
ALTER TABLE `lesson`
  ADD PRIMARY KEY (`LId`,`sId`,`cId`) USING BTREE,
  ADD KEY `sId` (`sId`),
  ADD KEY `cId` (`cId`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studId`),
  ADD KEY `cId` (`cId`);

--
-- Indexes for table `studlesson`
--
ALTER TABLE `studlesson`
  ADD PRIMARY KEY (`LId`,`sId`,`studId`),
  ADD KEY `sId` (`sId`),
  ADD KEY `studId` (`studId`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`sId`),
  ADD KEY `tId` (`tId`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`tId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `lesson`
--
ALTER TABLE `lesson`
  ADD CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`sId`) REFERENCES `subject` (`sId`),
  ADD CONSTRAINT `lesson_ibfk_2` FOREIGN KEY (`cId`) REFERENCES `class` (`cId`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`cId`) REFERENCES `class` (`cId`);

--
-- Constraints for table `studlesson`
--
ALTER TABLE `studlesson`
  ADD CONSTRAINT `studlesson_ibfk_1` FOREIGN KEY (`LId`) REFERENCES `lesson` (`LId`),
  ADD CONSTRAINT `studlesson_ibfk_2` FOREIGN KEY (`sId`) REFERENCES `subject` (`sId`),
  ADD CONSTRAINT `studlesson_ibfk_3` FOREIGN KEY (`studId`) REFERENCES `student` (`studId`);

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`tId`) REFERENCES `teacher` (`tId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
