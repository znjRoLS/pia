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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `time` date DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `opened` int(11) DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK_oe8418glrew8q1b7pm8ouacah` (`user`),
  CONSTRAINT `FK_oe8418glrew8q1b7pm8ouacah` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,45,'2017-06-26','TELFOR 2018','Conferention is canceled',1),(2,45,'2017-06-26','TELFOR 2018','Conferention is canceled',1),(3,45,'2017-06-27','MACANE!','Message from sdf',1),(4,45,'2017-06-26','TELFOR 2018','Conferention is canceled',1),(5,45,'2017-06-27','MACANE!','Message from sdf',1),(6,45,'2017-06-26','TELFOR 2018','Conferention is canceled',1),(7,45,'2017-06-27','macane','Message from sdf',1),(8,45,'2017-06-27','herheeeee','Message from sdf',1),(9,45,'2017-06-27','hehe!','Message from sdf',1),(10,45,'2017-06-27','sdfdsfsfdsfsfdsfdsf','Message from sdf',1),(11,45,'2017-06-27','mozeeeeeee','Message from sdf',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
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
