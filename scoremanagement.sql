-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 20, 2019 at 07:42 AM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scoremanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
CREATE TABLE IF NOT EXISTS `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`id`, `name`) VALUES
(1, '17HCB'),
(2, '18HCB');

-- --------------------------------------------------------

--
-- Table structure for table `remarking`
--

DROP TABLE IF EXISTS `remarking`;
CREATE TABLE IF NOT EXISTS `remarking` (
  `score_id` int(11) NOT NULL,
  `score_type` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `score_old` float DEFAULT NULL,
  `score_desired` float DEFAULT NULL,
  `reason` varchar(512) COLLATE utf8_unicode_ci DEFAULT '',
  `status` varchar(32) COLLATE utf8_unicode_ci DEFAULT 'Chưa xem',
  PRIMARY KEY (`score_id`,`score_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
CREATE TABLE IF NOT EXISTS `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `subject_id` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `grade_id` int(11) NOT NULL,
  `scode_haft` float DEFAULT NULL,
  `score_full` float DEFAULT NULL,
  `score_another` float DEFAULT NULL,
  `score_summary` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`student_id`,`subject_id`,`grade_id`),
  KEY `subject_students_ibfk_2` (`grade_id`,`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student_code` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` varchar(4) COLLATE utf8_unicode_ci DEFAULT 'Nữ',
  `person_code` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_code`),
  KEY `grade` (`grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_code`, `fullname`, `sex`, `person_code`, `grade`) VALUES
('giaovu', 'Giáo vụ', 'Nữ', 'giaovu', NULL);

--
-- Triggers `student`
--
DROP TRIGGER IF EXISTS `addStudent`;
DELIMITER $$
CREATE TRIGGER `addStudent` AFTER INSERT ON `student` FOR EACH ROW INSERT INTO `user`(`name`, `pass`, `nameshow`, `role`) VALUES (new.student_code,MD5(SHA1(new.person_code)),new.student_code,"SINHVIEN")
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `code` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`code`, `name`) VALUES
('CTT001', 'Lập trình ứng dụng Java'),
('CTT002', 'Mạng máy tính'),
('CTT011', 'Thiết kế giao diện'),
('CTT012', 'Kiểm chứng phần mềm');

-- --------------------------------------------------------

--
-- Table structure for table `time_table`
--

DROP TABLE IF EXISTS `time_table`;
CREATE TABLE IF NOT EXISTS `time_table` (
  `subject_code` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `grade` int(11) NOT NULL,
  `room` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`subject_code`,`grade`),
  KEY `grade` (`grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `nameshow` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`name`, `pass`, `nameshow`, `role`) VALUES
('giaovu', 'b2945411f75ee6c8745d2020fe420a75', 'giaovu', 'GIAOVU');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
