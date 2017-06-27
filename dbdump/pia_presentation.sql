-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: pia
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
-- Table structure for table `presentation`
--

DROP TABLE IF EXISTS `presentation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presentation` (
  `presentation_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `session_id` int(11) NOT NULL,
  `start_time` time DEFAULT NULL,
  PRIMARY KEY (`presentation_id`),
  KEY `FK_a3twfhm3y9whkxjpbvk3xicyl` (`session_id`),
  CONSTRAINT `FK_a3twfhm3y9whkxjpbvk3xicyl` FOREIGN KEY (`session_id`) REFERENCES `session_conf` (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentation`
--

LOCK TABLES `presentation` WRITE;
/*!40000 ALTER TABLE `presentation` DISABLE KEYS */;
INSERT INTO `presentation` VALUES (1,'Welcome words of orgineser',1,'10:00:00'),(2,'Dean\'s word',1,'10:10:00'),(3,'Assistant Minister',1,'10:20:00'),(4,'***\"Intro: 5G network',1,'10:30:00'),(5,'Simulator ORT1',7,'16:00:00'),(6,'Simulator ORT2',7,'16:20:00'),(7,'Simulator cash memory',7,'16:40:00'),(8,'Simulator virtual memory',7,'17:00:00'),(9,'Simulator pipeline',7,'17:20:00'),(10,'***\"A new approach in the development of web			applications - React JS',8,'08:30:00'),(11,'Angular 4',8,'09:00:00'),(12,'JSF, Spring and MVC',8,'09:15:00'),(13,'Software for plagiarism',8,'09:30:00'),(14,'Parallel systems',8,'09:45:00'),(15,'Unity in education',8,'10:00:00'),(16,'BPMN and BPEL notation',8,'10:15:00'),(17,'***\"Model driven development ',10,'11:00:00'),(18,'Big data',10,'11:30:00'),(19,'Social networks',10,'11:45:00'),(20,'Software evolution',10,'12:00:00'),(21,'Maxeler AppGalery Algorithms',10,'12:15:00'),(22,'Compilers',10,'12:30:00'),(23,'HOW TO GENERATE A GOOD PHD THESIS?',13,'14:00:00'),(24,'Cloud App Architecture',15,'14:00:00'),(25,'***\"VR in medicine',16,'17:00:00'),(26,'Conference Awards',16,'17:30:00'),(27,'Welcome words of orgineser',33,'10:00:00'),(28,'Dean\'s word',33,'10:10:00'),(29,'Assistant Minister',33,'10:20:00'),(30,'***\"Intro: 5G network',33,'10:30:00'),(31,'Simulator ORT1',39,'16:00:00'),(32,'Simulator ORT2',39,'16:20:00'),(33,'Simulator cash memory',39,'16:40:00'),(34,'Simulator virtual memory',39,'17:00:00'),(35,'Simulator pipeline',39,'17:20:00'),(36,'***\"A new approach in the development of web			applications - React JS',40,'08:30:00'),(37,'Angular 4',40,'09:00:00'),(38,'JSF, Spring and MVC',40,'09:15:00'),(39,'Software for plagiarism',40,'09:30:00'),(40,'Parallel systems',40,'09:45:00'),(41,'Unity in education',40,'10:00:00'),(42,'BPMN and BPEL notation',40,'10:15:00'),(43,'***\"Model driven development ',42,'11:00:00'),(44,'Big data',42,'11:30:00'),(45,'Social networks',42,'11:45:00'),(46,'Software evolution',42,'12:00:00'),(47,'Maxeler AppGalery Algorithms',42,'12:15:00'),(48,'Compilers',42,'12:30:00'),(49,'HOW TO GENERATE A GOOD PHD THESIS?',45,'14:00:00'),(50,'Cloud App Architecture',47,'14:00:00'),(51,'***\"VR in medicine',48,'17:00:00'),(52,'Conference Awards',48,'17:30:00'),(53,'Welcome words of orgineser',33,'10:00:00'),(54,'Dean\'s word',33,'10:10:00'),(55,'Assistant Minister',33,'10:20:00'),(56,'***\"Intro: 5G network',33,'10:30:00'),(57,'Simulator ORT1',39,'16:00:00'),(58,'Simulator ORT2',39,'16:20:00'),(59,'Simulator cash memory',39,'16:40:00'),(60,'Simulator virtual memory',39,'17:00:00'),(61,'Simulator pipeline',39,'17:20:00'),(62,'***\"A new approach in the development of web			applications - React JS',40,'08:30:00'),(63,'Angular 4',40,'09:00:00'),(64,'JSF, Spring and MVC',40,'09:15:00'),(65,'Software for plagiarism',40,'09:30:00'),(66,'Parallel systems',40,'09:45:00'),(67,'Unity in education',40,'10:00:00'),(68,'BPMN and BPEL notation',40,'10:15:00'),(69,'***\"Model driven development ',42,'11:00:00'),(70,'Big data',42,'11:30:00'),(71,'Social networks',42,'11:45:00'),(72,'Software evolution',42,'12:00:00'),(73,'Maxeler AppGalery Algorithms',42,'12:15:00'),(74,'Compilers',42,'12:30:00'),(75,'HOW TO GENERATE A GOOD PHD THESIS?',45,'14:00:00'),(76,'Cloud App Architecture',47,'14:00:00'),(77,'***\"VR in medicine',48,'17:00:00'),(78,'Conference Awards',48,'17:30:00'),(163,'Welcome words of orgineser',81,'10:00:00'),(164,'Dean\'s word',81,'10:10:00'),(165,'Assistant Minister',81,'10:20:00'),(166,'***Intro: 5G network',81,'10:30:00'),(167,'Simulator ORT1',87,'16:00:00'),(168,'Simulator ORT2',87,'16:20:00'),(169,'Simulator cash memory',87,'16:40:00'),(170,'Simulator virtual memory',87,'17:00:00'),(171,'Simulator pipeline ',87,'17:20:00'),(172,'***A new approach in the development of web applications - React JS',88,'08:30:00'),(173,'Angular 4',88,'09:00:00'),(174,'JSF, Spring and MVC',88,'09:15:00'),(175,'Software for plagiarism',88,'09:30:00'),(176,'Parallel systems',88,'09:45:00'),(177,'Unity in education',88,'10:00:00'),(178,'BPMN and BPEL notation',88,'10:15:00'),(179,'***Model driven development ',90,'11:00:00'),(180,'Big data',90,'11:30:00'),(181,'Social networks',90,'11:45:00'),(182,'Software evolution',90,'12:00:00'),(183,'Maxeler AppGalery Algorithms',90,'12:15:00'),(184,'Compilers',90,'12:30:00'),(185,'HOW TO GENERATE A GOOD PHD THESIS?',93,'14:00:00'),(186,'Cloud App Architecture',95,'14:00:00'),(187,'***VR in medicine',96,'17:00:00'),(188,'Conference Awards',96,'17:30:00'),(189,'Welcome words of orgineser',97,'10:00:00'),(190,'Dean\'s word',97,'10:10:00'),(191,'Assistant Minister',97,'10:20:00'),(192,'***Intro: 5G network',97,'10:30:00'),(193,'Simulator ORT1',103,'16:00:00'),(194,'Simulator ORT2',103,'16:20:00'),(195,'Simulator cash memory',103,'16:40:00'),(196,'Simulator virtual memory',103,'17:00:00'),(197,'Simulator pipeline ',103,'17:20:00'),(198,'***A new approach in the development of web applications - React JS',104,'08:30:00'),(199,'Angular 4',104,'09:00:00'),(200,'JSF, Spring and MVC',104,'09:15:00'),(201,'Software for plagiarism',104,'09:30:00'),(202,'Parallel systems',104,'09:45:00'),(203,'Unity in education',104,'10:00:00'),(204,'BPMN and BPEL notation',104,'10:15:00'),(205,'***Model driven development ',106,'11:00:00'),(206,'Big data',106,'11:30:00'),(207,'Social networks',106,'11:45:00'),(208,'Software evolution',106,'12:00:00'),(209,'Maxeler AppGalery Algorithms',106,'12:15:00'),(210,'Compilers',106,'12:30:00'),(211,'HOW TO GENERATE A GOOD PHD THESIS?',109,'14:00:00'),(212,'Cloud App Architecture',111,'14:00:00'),(213,'***VR in medicine',112,'17:00:00'),(214,'Conference Awards',112,'17:30:00');
/*!40000 ALTER TABLE `presentation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-27 17:42:51
