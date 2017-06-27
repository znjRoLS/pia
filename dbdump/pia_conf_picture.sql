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
-- Table structure for table `conf_picture`
--

DROP TABLE IF EXISTS `conf_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conf_picture` (
  `conf_picture_id` int(11) NOT NULL AUTO_INCREMENT,
  `conference` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`conf_picture_id`),
  KEY `FK_d4xjf1kem4atqyjgwnkmunpcw` (`conference`),
  CONSTRAINT `FK_d4xjf1kem4atqyjgwnkmunpcw` FOREIGN KEY (`conference`) REFERENCES `conference` (`conference_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conf_picture`
--

LOCK TABLES `conf_picture` WRITE;
/*!40000 ALTER TABLE `conf_picture` DISABLE KEYS */;
INSERT INTO `conf_picture` VALUES (1,14,'uploads\\conferencePictures\\14\\15317922_1294747707244385_8821496560466816815_n.jpg'),(2,14,'uploads\\conferencePictures\\14\\16736097_10211803772597833_1709078416_n.jpg'),(3,14,'uploads\\conferencePictures\\14\\[001036].jpg'),(4,14,'uploads\\conferencePictures\\14\\aca.lukas_.jpg'),(5,14,'uploads\\conferencePictures\\14\\cikn.jpg'),(6,14,'uploads\\conferencePictures\\14\\aca.lukas_.jpg'),(7,14,'uploads\\conferencePictures\\14\\IMG_0759.jpg');
/*!40000 ALTER TABLE `conf_picture` ENABLE KEYS */;
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
