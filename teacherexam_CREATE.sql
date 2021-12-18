-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- 主機: localhost
-- 建立日期: Jan 06, 2016, 12:56 PM
-- 伺服器版本: 5.1.44
-- PHP 版本: 5.3.1




SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- 資料庫: `teacherexam`
--
-- 傾印 teacherexam 的資料庫結構
CREATE DATABASE IF NOT EXISTS `teacherexam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `teacherexam`;

-- --------------------------------------------------------

--
-- 資料表格式： `appconfigs`
--

CREATE TABLE IF NOT EXISTS `appconfigs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `schoolname` varchar(255) NOT NULL,
  `principal` varchar(255) NOT NULL,
  `zhuji` varchar(255) NOT NULL,
  `chuna` varchar(255) NOT NULL,
  `renshi` varchar(255) NOT NULL,
  `renshiphone` varchar(255) NOT NULL,
  `bankprefix` varchar(255) NOT NULL,
  `bankname` varchar(255) NOT NULL,
  `bankhuming` varchar(255) NOT NULL,
  `manager_ip` text NOT NULL,
  `allowed_ip` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `applications`
--

CREATE TABLE IF NOT EXISTS `applications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examid` int(11) NOT NULL,
  `seatid` varchar(50) NOT NULL DEFAULT '',
  `pid` varchar(50) NOT NULL,
  `bankaccount` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `cellphone` varchar(100) NOT NULL,
  `teloffice` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL DEFAULT 'NOUSE',
  `subjectid` int(11) NOT NULL,
  `school` varchar(100) NOT NULL,
  `position` varchar(50) NOT NULL,
  `license` varchar(50) NOT NULL,
  `licensedate` varchar(50) NOT NULL,
  `licenseid` varchar(100) NOT NULL,
  `bachelorschool` varchar(50) NOT NULL,
  `bachelormajor` varchar(100) NOT NULL,
  `bachelorbegin` varchar(50) NOT NULL,
  `bachelorend` varchar(50) NOT NULL,
  `masterschool` varchar(50) NOT NULL,
  `mastermajor` varchar(100) NOT NULL,
  `masterbegin` varchar(50) NOT NULL,
  `masterend` varchar(50) NOT NULL,
  `phdschool` varchar(50) NOT NULL,
  `phdmajor` varchar(100) NOT NULL,
  `phdbegin` varchar(50) NOT NULL,
  `phdend` varchar(50) NOT NULL,
  `school1` varchar(50) NOT NULL,
  `position1` varchar(50) NOT NULL,
  `school1begin` varchar(50) NOT NULL,
  `school1end` varchar(50) NOT NULL,
  `school2` varchar(50) NOT NULL,
  `position2` varchar(50) NOT NULL,
  `school2begin` varchar(50) NOT NULL,
  `school2end` varchar(50) NOT NULL,
  `school3` varchar(50) NOT NULL,
  `position3` varchar(50) NOT NULL,
  `school3begin` varchar(50) NOT NULL,
  `school3end` varchar(50) NOT NULL,
  `special1` varchar(255) NOT NULL,
  `special1date` varchar(50) NOT NULL,
  `special2` varchar(255) NOT NULL,
  `special2date` varchar(50) NOT NULL,
  `license1` varchar(255) NOT NULL,
  `license2` varchar(255) NOT NULL,
  `license3` varchar(255) NOT NULL,
  `honors` text NOT NULL,
  `other` text NOT NULL,
  `note` text NOT NULL,
  `pictureid` int(11) NOT NULL DEFAULT '0',
  `ispaid` tinyint(1) DEFAULT '0',
  `score_teach` double NOT NULL DEFAULT '0',
  `score_subject` double NOT NULL DEFAULT '0',
  `tscore_teach` double NOT NULL DEFAULT '0',
  `tscore_subject` double NOT NULL DEFAULT '0',
  `percent_teach` double NOT NULL DEFAULT '0',
  `percent_subject` double NOT NULL DEFAULT '0',
  `totalscore` double NOT NULL DEFAULT '0',
  `result` varchar(100) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `application_duplicate` (`examid`,`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `bankdatas`
--

CREATE TABLE IF NOT EXISTS `bankdatas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bankname` varchar(100) NOT NULL,
  `bankaccount` varchar(50) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `rowdata` text NOT NULL,
  `receivetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `exams`
--

CREATE TABLE IF NOT EXISTS `exams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `applybegin` date NOT NULL,
  `applyend` date NOT NULL,
  `deadline` date NOT NULL,
  `firsttest` datetime NOT NULL DEFAULT '2000-01-01 00:00:00',
  `secondtest` datetime NOT NULL DEFAULT '2000-01-01 00:00:00',
  `scoreboardbegin` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `money` int(11) NOT NULL DEFAULT '0',
  `examroom` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `isexamformprintable` tinyint(1) NOT NULL,
  `isactive` tinyint(1) NOT NULL,
  `isapplicationalwayseditable` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `logs`
--

CREATE TABLE IF NOT EXISTS `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `method` varchar(10) NOT NULL,
  `uri` varchar(100) NOT NULL,
  `session_pid` varchar(20) NOT NULL,
  `ip` varchar(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `subtitle` varchar(255) NOT NULL,
  `stacktrace` text NOT NULL,
  `debug` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `pictures`
--

CREATE TABLE IF NOT EXISTS `pictures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) NOT NULL,
  `filetype` varchar(255) NOT NULL,
  `blob` longblob NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT '2012-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `scoreboard`
--

CREATE TABLE IF NOT EXISTS `scoreboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(30) NOT NULL,
  `examid` varchar(30) NOT NULL,
  `teach_score` double NOT NULL,
  `benke_score` double NOT NULL,
  `teach_tscore` double NOT NULL,
  `benke_tscore` double NOT NULL,
  `teach_tscore_percent` double NOT NULL,
  `benke_tscore_percent` double NOT NULL,
  `total_score` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examid` int(11) NOT NULL,
  `zhengshidaili` varchar(100) NOT NULL,
  `nianduan` varchar(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  `seatpattern` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `upfiles`
--

CREATE TABLE IF NOT EXISTS `upfiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examid` int(11) NOT NULL,
  `descript` varchar(255) NOT NULL,
  `filename` varchar(200) NOT NULL,
  `filetype` varchar(200) NOT NULL,
  `fileblob` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表格式： `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(50) NOT NULL,
  `examid` int(11) NOT NULL DEFAULT '0',
  `passwd` varchar(50) NOT NULL,
  `group` varchar(50) NOT NULL,
  `role` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_duplicate` (`pid`,`examid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `scorecsv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examid` int(11) NOT NULL,
  `seatid` varchar(50) NOT NULL DEFAULT '',
  `step1` text NOT NULL,
  `step2` text NOT NULL,
  `step3` text NOT NULL,
  `step4` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `examid_seatid` (`examid`,`seatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
