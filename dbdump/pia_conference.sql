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
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference` (
  `conference_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `location_id` int(11) NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  PRIMARY KEY (`conference_id`),
  KEY `FK_lxi0wc9ymqd75skv8k4dwgjjj` (`location_id`),
  CONSTRAINT `FK_lxi0wc9ymqd75skv8k4dwgjjj` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
INSERT INTO `conference` VALUES (6,'dsfdsf','sdf','2017-06-13','2017-06-23',1,NULL,NULL),(7,'sdf','sdf','2017-06-06','2017-06-29',1,NULL,NULL),(8,'dsfdsf','sdf','2017-06-13','2017-06-23',1,'03:00:00','13:00:00'),(9,'dsfdsf','sdf','2017-06-13','2017-06-23',1,NULL,NULL),(10,'dsfdsf','sdf','2017-06-13','2017-06-23',1,NULL,NULL),(11,'dsfdsf','sdf','2017-06-13','2017-06-23',1,NULL,NULL),(12,'TELFOR 2018','beograd','2017-06-01','2017-06-22',1,NULL,NULL),(13,'TELFOR 2018','df','2017-06-06','2017-06-14',1,NULL,NULL),(14,'herhe','herhe','2017-06-29','2017-06-30',1,NULL,NULL),(15,'TELFOR 2018','Beograd','2018-11-20','2018-11-22',1,NULL,NULL),(16,'haha konferencija','zajecar','2017-06-30','2018-12-07',1,NULL,NULL),(17,'mladen','canovic','2017-06-30','2019-01-10',1,NULL,NULL);
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
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
