-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aimp
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (3,'layers','2018-03-23 20:31:03','2018-03-24 22:13:56'),(55,'Logic','2018-04-16 23:13:53','2018-05-20 23:28:16'),(59,'Security','2018-03-17 00:15:07','2018-05-20 23:28:16'),(60,'Cooker','2018-04-26 23:24:52','2018-05-20 23:28:16'),(61,'First','2018-05-16 23:25:07','2018-05-21 12:39:29'),(62,'Cargoo','2018-05-16 23:28:40','2018-05-17 20:43:32'),(63,'Driver','2018-05-16 23:28:45','2018-05-17 20:43:54'),(65,'Billing','2018-06-30 21:46:47','2018-06-30 21:47:10'),(66,'Actors','2018-07-01 02:15:18','2018-07-01 02:15:18'),(67,'Barrista','2018-07-03 22:43:58','2018-07-03 22:44:22');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` int(11) DEFAULT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(85) NOT NULL,
  `salary` int(11) DEFAULT '100',
  `birthdate` date NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `department_employee_idx` (`department`),
  CONSTRAINT `department_employee` FOREIGN KEY (`department`) REFERENCES `department` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,65,'Tony','Sopr','sopr@gmail.com',500,'1958-03-16','2018-05-06 19:49:58','2018-07-03 03:10:00'),(30,61,'Mike','Tyson','tyson@gmail.com',100,'1960-03-02','2018-03-24 22:17:24','2018-05-18 00:48:00'),(43,62,'James','Bond','0007D@gmail.com',100,'1970-03-16','2018-03-23 20:31:50','2018-05-21 12:36:12'),(46,55,'Ffgh','Klg','hd@gmail.com',500,'1955-01-02','2018-03-24 22:17:24','2018-07-03 03:10:00'),(50,63,'rtyu','wsy','hdee@gmail.com',100,'2000-01-02','2018-03-24 22:17:24','2018-05-21 12:39:11'),(51,55,'Mike','James','red-21@ukr.net',500,'2000-01-02','2018-03-24 22:17:24','2018-07-03 03:10:17'),(53,55,'Mike','Soprano','s222',430,'2000-00-02','2018-03-24 22:17:24','2018-07-03 03:10:17'),(54,3,'MStasike','Soprano','34@gmail.com',180,'2000-02-28','2018-03-25 00:40:25','2018-07-03 03:10:17'),(56,3,'dfghj','Soprano','dde3@gmail.com',800,'2000-01-02','2018-05-06 19:59:00','2018-07-03 03:10:00'),(59,55,'Mike','6451','3345@gmail.com',900,'1990-01-02','2018-05-06 21:03:35','2018-07-03 03:13:04'),(60,55,'Mike','Soprano','368@gmail.com',1120,'2000-01-02','2018-05-06 21:26:34','2018-07-03 03:13:04'),(61,3,'Mike','Gaidai','1gh@gmail.com',980,'2000-01-02','2018-05-06 22:29:54','2018-07-03 03:13:04'),(64,59,'Mike','Gaidai','2384ss@gmail.com',320,'2000-01-02','2018-05-06 22:31:00','2018-07-03 03:13:04'),(65,60,'Mike','Soprano','dsa',450,'2000-01-02','2018-05-06 22:33:00','2018-07-03 03:13:04'),(66,60,'Mike','Soprano','1g4545h@gmail.com',550,'2000-01-02','2018-05-06 22:34:47','2018-07-03 03:13:04'),(67,60,'Stanislav','Soprano','Q3888AD@gmail.com',750,'1988-03-21','2018-05-06 22:36:16','2018-07-03 03:10:00'),(68,3,'Mike','asd','das',100,'2000-01-02','2018-05-06 22:55:37','2018-07-03 03:13:04'),(69,3,'Vito','Soprano','43444',250,'2000-01-02','2018-05-06 22:56:07','2018-07-03 03:11:59'),(70,59,'Mike','Wsy','hd8888@gmail.com',890,'1988-01-02','2018-05-06 22:58:39','2018-07-03 03:13:04'),(72,59,'Polly','Soprano','54554y@gmail.com',1250,'1990-03-21','2018-05-15 00:05:31','2018-07-03 03:13:04'),(73,59,'Mike','Soprano','45D@gmail.com',2500,'1995-03-21','2018-05-15 00:15:18','2018-07-03 03:13:04'),(74,63,'Ralph','Soprano','ghhtg5@gmail.com',2300,'1992-03-21','2018-05-15 00:16:33','2018-07-03 03:11:59'),(75,59,'Polly','Soprano','342@gmail.com',2200,'1992-03-21','2018-05-15 00:19:09','2018-07-03 03:13:04'),(76,63,'Mike','Soprano','234@gmail.com',280,'1980-03-16','2018-05-15 00:22:37','2018-07-03 03:13:04'),(77,63,'Ralph','Soprano','Q-3412@gmail.com',650,'1990-03-21','2018-05-15 00:29:13','2018-07-03 03:11:59'),(78,62,'Polly','Soprano','rek155@ukr.net',480,'1980-03-16','2018-05-15 01:15:28','2018-07-03 03:13:04'),(79,62,'Ralph','Soprano','DY@gmail.com',920,'1992-03-21','2018-05-15 01:16:50','2018-07-03 03:11:59'),(80,62,'Polly','Soprano','DYs@gmail.com',820,'1990-03-21','2018-05-15 01:17:31','2018-07-03 03:13:04'),(81,62,'Asd','Asd','HHHH@gmail.com',720,'1980-03-16','2018-05-15 01:17:54','2018-07-03 03:10:00'),(82,61,'Tommy','Versetti','f45@gmail.com',650,'1968-02-21','2018-05-15 01:18:51','2018-07-03 03:10:00'),(83,61,'Stas','dfg','555ADoY@gMAIL.com',740,'1990-03-21','2018-05-16 00:45:39','2018-07-03 03:13:04'),(85,61,'Mike','dfg','q-d554321@gmail.com',1800,'1990-03-21','2018-05-16 00:46:01','2018-07-03 03:13:04'),(86,61,'Alexander','fgdfg','will@gmail.com',1600,'1945-03-21','2018-05-16 00:57:56','2018-07-03 03:13:04'),(87,61,'Alexander','Gaidai','dfg@gmail.com',1000,'1980-03-16','2018-05-16 23:37:31','2018-07-03 03:13:04'),(93,63,'Asd','dfg','stanislav@gaidai.com',1000,'1988-03-21','2018-05-20 00:06:16','2018-07-03 03:13:04'),(94,63,'Alexander','dfg','darg@com.ua',1200,'1990-03-21','2018-05-20 00:07:21','2018-07-03 03:13:04'),(95,63,'Alexander','dfgddg','stas@gaidai.com',1150,'1990-03-21','2018-05-20 01:02:55','2018-07-03 03:13:04'),(96,63,'Alexander','dfggdf','arg@com.ua',1050,'1955-01-02','2018-05-20 01:26:19','2018-07-03 03:13:04'),(97,55,'Stas','dfg','argyyy@com.ua',950,'1992-03-21','2018-05-21 00:24:02','2018-07-03 03:13:04'),(98,59,'Stas','dfgd','ar52g@com.ua',800,'1980-03-16','2018-05-22 00:35:26','2018-07-03 03:13:04'),(99,NULL,'Stas','dfggg','ar87g@com.ua',1230,'1990-03-21','2018-05-22 00:37:23','2018-07-03 03:13:04'),(100,65,'Antonio','Cirocco','antonios@com.up',990,'1990-03-21','2018-06-30 21:48:08','2018-07-03 03:10:00'),(101,65,'Hanter','Tompson','gonzo@gmail.com',4800,'1940-03-16','2018-07-03 21:18:33','2018-07-03 21:18:46'),(102,66,'Hanter','Tompson','hanterd@gmail.com',305,'1988-02-21','2018-07-04 00:15:33','2018-07-04 00:15:44');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-04  3:19:07
