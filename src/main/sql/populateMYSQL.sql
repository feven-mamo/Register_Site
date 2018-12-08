-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: register2
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `block_course`
--

DROP TABLE IF EXISTS `block_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `block_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_number` varchar(45) DEFAULT NULL,
  `block` varchar(45) DEFAULT NULL,
  `professor` int(5) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `enrolled` int(11) DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_number_idx` (`course_number`),
  KEY `FK_professorid_idx` (`professor`),
  CONSTRAINT `FK_course_num` FOREIGN KEY (`course_number`) REFERENCES `course` (`course_number`),
  CONSTRAINT `FK_professorid` FOREIGN KEY (`professor`) REFERENCES `faculty` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block_course`
--

LOCK TABLES `block_course` WRITE;
/*!40000 ALTER TABLE `block_course` DISABLE KEYS */;
INSERT INTO `block_course` VALUES (4,'CS101','April',2,2,2,2);
/*!40000 ALTER TABLE `block_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `course_number` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `isprerequisite` tinyint(4) DEFAULT NULL,
  `course_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`course_number`),
  KEY `Fk_precourse_idx` (`course_id`),
  CONSTRAINT `Fk_precourse` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_number`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('CS101','WAP',0,NULL),('CS103','APP',0,NULL),('CS401','AAA',1,'CS101'),('CS504','Algorithm',1,'CS512'),('CS508','Network Security',1,NULL),('CS509','Modern Programming',0,NULL),('CS510','Advanced Software',1,NULL),('CS512','CS512',0,NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `faculty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(5) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_id_idx` (`userid`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (2,1001,'tg');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `student_id` int(5) NOT NULL AUTO_INCREMENT,
  `reg_number` int(11) DEFAULT NULL,
  `entry` varchar(45) DEFAULT NULL,
  `track` varchar(45) DEFAULT NULL,
  `advisor` int(5) DEFAULT NULL,
  `user_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `FK_advisor_idx` (`advisor`),
  CONSTRAINT `Fk_advisor` FOREIGN KEY (`advisor`) REFERENCES `faculty` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (4,2,'April','FPP',2,1021),(5,12,'APRIL','FPP',2,1024);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(5) DEFAULT NULL,
  `block_id` int(11) DEFAULT NULL,
  `grade` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'Pending',
  PRIMARY KEY (`id`),
  KEY `FK_block_id_idx` (`block_id`),
  CONSTRAINT `FK_block_id` FOREIGN KEY (`block_id`) REFERENCES `block_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (2,4,4,NULL,'Pending'),(3,4,4,NULL,'Pending'),(4,5,4,NULL,'Pending');
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(5) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(25) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1001,'Tigist','Tadesse','tg@yahoo.com','1988-12-31 00:00:00','tigist','123456','admin'),(1015,'Bana Wakanda','Aweke','tgcool12@gmail.com','2018-11-08 00:00:00','tg55','123456','faculty'),(1016,'Tigist','Tadesse','tg@yahoo.com','1988-12-31 00:00:00','tigist','123456','admin'),(1017,'TigistTade','Mengesha','tgcool12@gmail.com','2018-11-06 00:00:00','tade4','123456','admin'),(1018,'TigistTade','Mengesha','tgcool12@gmail.com','2018-11-06 00:00:00','tade4','12345','admin'),(1019,'Bana Wakanda','Aweke','tgcool12@gmail.com','2018-11-08 00:00:00','tgtade','12345','staff'),(1020,'Silas','Awekew','tigew2000@gmail.com','2018-11-02 00:00:00','tgpp','12345','staff'),(1021,'tgcool','Mengesha','tgtade@yahoo.com','2018-11-06 00:00:00','tg44','12345','STUDENT'),(1022,'Feven','Mamo','tgtade@yahoo.com','2018-11-05 00:00:00','tgtade','$2a$10$Tnd0aZv/VnlUxgmN1kEmeehV4P6TSnIxCwcdijTklj9Pv/JC2s6ni','student'),(1023,'Feven','Mamo','tgtade@yahoo.com','2018-11-05 00:00:00','tgtad6','$2a$10$D9.OEV/flMUB69OWDfW9XeHhlKgScczGvsNloNPBJNbB7pkVnH65i','ADMIN'),(1024,'Nadia','Kemal','tigist.mengesha7@gmail.com','2018-11-06 00:00:00','tg77','$2a$10$AZ7oLJhA.cyf1qeek5VVQ.6CDQFPkGKRLTppkUAJ2vNA6UgvlrOhe','STUDENT'),(1025,'Feven.h','Mamo.E','fevu@mum.edu','2010-01-01 00:00:00','fevu2','12345','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waiver`
--

DROP TABLE IF EXISTS `waiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `waiver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(5) DEFAULT NULL,
  `course_number` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_courseNum_idx` (`course_number`),
  KEY `Fk_sid_idx` (`student_id`),
  CONSTRAINT `FK_courseNum` FOREIGN KEY (`course_number`) REFERENCES `course` (`course_number`),
  CONSTRAINT `Fk_sid` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waiver`
--

LOCK TABLES `waiver` WRITE;
/*!40000 ALTER TABLE `waiver` DISABLE KEYS */;
INSERT INTO `waiver` VALUES (1,5,NULL,'Pending'),(2,5,NULL,'Pending'),(3,5,'CS101','Pending');
/*!40000 ALTER TABLE `waiver` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-22 15:34:29
