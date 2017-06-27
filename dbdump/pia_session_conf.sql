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
-- Table structure for table `session_conf`
--

DROP TABLE IF EXISTS `session_conf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_conf` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`session_id`),
  KEY `FK_nsrfrgk26jpfgx1p02fokjx37` (`room_id`),
  KEY `FK_n86a1g5avomj5pxo6eivhhoyp` (`conference`),
  CONSTRAINT `FK_n86a1g5avomj5pxo6eivhhoyp` FOREIGN KEY (`conference`) REFERENCES `conference` (`conference_id`),
  CONSTRAINT `FK_nsrfrgk26jpfgx1p02fokjx37` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_conf`
--

LOCK TABLES `session_conf` WRITE;
/*!40000 ALTER TABLE `session_conf` DISABLE KEYS */;
INSERT INTO `session_conf` VALUES (1,'Plenary session','Open',1,'2018-01-21','10:00:00','11:00:00',12),(2,'Welcome cocktail','Break',NULL,'2018-01-21','11:00:00','13:00:00',12),(3,'Telecomm.networks','Session',2,'2018-01-21','13:00:00','15:00:00',12),(4,'Radio communications','Session',3,'2018-01-21','13:00:00','15:00:00',12),(5,'Coffee','Break',NULL,'2018-01-21','15:30:00','16:00:00',12),(6,'Signal processing','Session',2,'2018-01-21','16:00:00','18:00:00',12),(7,'Hardware systems','Session',3,'2018-01-21','16:00:00','18:00:00',12),(8,'Software systems 1','Session',2,'2018-01-22','08:30:00','10:30:00',12),(9,'Coffee','Break',NULL,'2018-01-22','10:30:00','11:00:00',12),(10,'Software systems 2','Session',3,'2018-01-22','11:00:00','13:00:00',12),(11,'Lunch','Break',NULL,'2018-01-22','13:00:00','14:00:00',12),(12,'Workshop IoT','WS',2,'2018-01-22','14:00:00','17:00:00',12),(13,'Workshop PhD','WS',3,'2018-01-22','14:00:00','17:00:00',12),(14,'Workshop SensorNets','WS',4,'2018-01-22','14:00:00','17:00:00',12),(15,'Workshop CloudTech','WS',5,'2018-01-22','14:00:00','17:00:00',12),(16,'Closing ceremony','Close',1,'2018-01-22','17:00:00','18:00:00',12),(17,'Plenary session','Open',1,'2017-01-21','10:00:00','11:00:00',13),(18,'Welcome cocktail','Break',NULL,'2017-01-21','11:00:00','13:00:00',13),(19,'Telecomm.networks','Session',2,'2017-01-21','13:00:00','15:00:00',13),(20,'Radio communications','Session',3,'2017-01-21','13:00:00','15:00:00',13),(21,'Coffee ','Break',NULL,'2017-01-21','15:30:00','16:00:00',13),(22,'Signal processing','Session',2,'2017-01-21','16:00:00','18:00:00',13),(23,'Hardware systems','Session',3,'2017-01-21','16:00:00','18:00:00',13),(24,'Software systems 1','Session',2,'2017-01-22','08:30:00','10:30:00',13),(25,'Coffee','Break',NULL,'2017-01-22','10:30:00','11:00:00',13),(26,'Software systems 2','Session',3,'2017-01-22','11:00:00','13:00:00',13),(27,'Lunch','Break',NULL,'2017-01-22','13:00:00','14:00:00',13),(28,'Workshop IoT','WS',2,'2017-01-22','14:00:00','17:00:00',13),(29,'Workshop PhD','WS',3,'2017-01-22','14:00:00','17:00:00',13),(30,'Workshop SensorNets','WS',4,'2017-01-22','14:00:00','17:00:00',13),(31,'Workshop CloudTech','WS',5,'2017-01-22','14:00:00','17:00:00',13),(32,'Closing ceremony','Close',1,'2017-01-22','17:00:00','18:00:00',13),(33,'Plenary session','Open',1,'2018-01-21','10:00:00','11:00:00',15),(34,'Welcome cocktail','Break',NULL,'2018-01-21','11:00:00','13:00:00',15),(35,'Telecomm.networks','Session',2,'2018-01-21','13:00:00','15:00:00',15),(36,'Radio communications','Session',3,'2018-01-21','13:00:00','15:00:00',15),(37,'Coffee','Break',NULL,'2018-01-21','15:30:00','16:00:00',15),(38,'Signal processing','Session',2,'2018-01-21','16:00:00','18:00:00',15),(39,'Hardware systems','Session',3,'2018-01-21','16:00:00','18:00:00',15),(40,'Software systems 1','Session',2,'2018-01-22','08:30:00','10:30:00',15),(41,'Coffee','Break',NULL,'2018-01-22','10:30:00','11:00:00',15),(42,'Software systems 2','Session',3,'2018-01-22','11:00:00','13:00:00',15),(43,'Lunch','Break',NULL,'2018-01-22','13:00:00','14:00:00',15),(44,'Workshop IoT','WS',2,'2018-01-22','14:00:00','17:00:00',15),(45,'Workshop PhD','WS',3,'2018-01-22','14:00:00','17:00:00',15),(46,'Workshop SensorNets','WS',4,'2018-01-22','14:00:00','17:00:00',15),(47,'Workshop CloudTech','WS',5,'2018-01-22','14:00:00','17:00:00',15),(48,'Closing ceremony','Close',1,'2018-01-22','17:00:00','18:00:00',15),(49,'Plenary session','Open',1,'2018-01-21','10:00:00','11:00:00',15),(50,'Welcome cocktail','Break',1,'2018-01-21','11:00:00','13:00:00',15),(51,'Telecomm.networks','Session',2,'2018-01-21','13:00:00','15:00:00',15),(52,'Radio communications','Session',4,'2018-01-21','13:00:00','15:00:00',15),(53,'Coffee','Break',1,'2018-01-21','15:30:00','16:00:00',15),(54,'Signal processing','Session',2,'2018-01-21','16:00:00','18:00:00',15),(55,'Hardware systems','Session',4,'2018-01-21','16:00:00','18:00:00',15),(56,'Software systems 1','Session',2,'2018-01-22','08:30:00','10:30:00',15),(57,'Coffee','Break',1,'2018-01-22','10:30:00','11:00:00',15),(58,'Software systems 2','Session',4,'2018-01-22','11:00:00','13:00:00',15),(59,'Lunch','Break',1,'2018-01-22','13:00:00','14:00:00',15),(60,'Workshop IoT','WS',2,'2018-01-22','14:00:00','17:00:00',15),(61,'Workshop PhD','WS',4,'2018-01-22','14:00:00','17:00:00',15),(62,'Workshop SensorNets','WS',4,'2018-01-22','14:00:00','17:00:00',15),(63,'Workshop CloudTech','WS',5,'2018-01-22','14:00:00','17:00:00',15),(64,'Closing ceremony','Close',1,'2018-01-22','17:00:00','18:00:00',15),(65,'Plenary session','Open',1,'2017-01-21','10:00:00','11:00:00',16),(66,'Welcome cocktail','Break',NULL,'2017-01-21','11:00:00','13:00:00',16),(67,'Telecomm.networks','Session',2,'2017-01-21','13:00:00','15:00:00',16),(68,'Radio communications','Session',3,'2017-01-21','13:00:00','15:00:00',16),(69,'Coffee ','Break',NULL,'2017-01-21','15:30:00','16:00:00',16),(70,'Signal processing','Session',2,'2017-01-21','16:00:00','18:00:00',16),(71,'Hardware systems','Session',3,'2017-01-21','16:00:00','18:00:00',16),(72,'Software systems 1','Session',2,'2017-01-22','08:30:00','10:30:00',16),(73,'Coffee','Break',NULL,'2017-01-22','10:30:00','11:00:00',16),(74,'Software systems 2','Session',3,'2017-01-22','11:00:00','13:00:00',16),(75,'Lunch','Break',NULL,'2017-01-22','13:00:00','14:00:00',16),(76,'Workshop IoT','WS',2,'2017-01-22','14:00:00','17:00:00',16),(77,'Workshop PhD','WS',3,'2017-01-22','14:00:00','17:00:00',16),(78,'Workshop SensorNets','WS',4,'2017-01-22','14:00:00','17:00:00',16),(79,'Workshop CloudTech','WS',5,'2017-01-22','14:00:00','17:00:00',16),(80,'Closing ceremony','Close',1,'2017-01-22','17:00:00','18:00:00',16),(81,'Plenary session','Open',1,'2017-01-21','10:00:00','11:00:00',17),(82,'Welcome cocktail','Break',NULL,'2017-01-21','11:00:00','13:00:00',17),(83,'Telecomm.networks','Session',2,'2017-01-21','13:00:00','15:00:00',17),(84,'Radio communications','Session',3,'2017-01-21','13:00:00','15:00:00',17),(85,'Coffee ','Break',NULL,'2017-01-21','15:30:00','16:00:00',17),(86,'Signal processing','Session',2,'2017-01-21','16:00:00','18:00:00',17),(87,'Hardware systems','Session',3,'2017-01-21','16:00:00','18:00:00',17),(88,'Software systems 1','Session',2,'2017-01-22','08:30:00','10:30:00',17),(89,'Coffee','Break',NULL,'2017-01-22','10:30:00','11:00:00',17),(90,'Software systems 2','Session',3,'2017-01-22','11:00:00','13:00:00',17),(91,'Lunch','Break',NULL,'2017-01-22','13:00:00','14:00:00',17),(92,'Workshop IoT','WS',2,'2017-01-22','14:00:00','17:00:00',17),(93,'Workshop PhD','WS',3,'2017-01-22','14:00:00','17:00:00',17),(94,'Workshop SensorNets','WS',4,'2017-01-22','14:00:00','17:00:00',17),(95,'Workshop CloudTech','WS',5,'2017-01-22','14:00:00','17:00:00',17),(96,'Closing ceremony','Close',1,'2017-01-22','17:00:00','18:00:00',17),(97,'Plenary session','Open',1,'2017-01-21','10:00:00','11:00:00',14),(98,'Welcome cocktail','Break',1,'2017-01-21','11:00:00','13:00:00',14),(99,'Telecomm.networks','Session',2,'2017-01-21','13:00:00','15:00:00',14),(100,'Radio communications','Session',4,'2017-01-21','13:00:00','15:00:00',14),(101,'Coffee ','Break',1,'2017-01-21','15:30:00','16:00:00',14),(102,'Signal processing','Session',2,'2017-01-21','16:00:00','18:00:00',14),(103,'Hardware systems','Session',4,'2017-01-21','16:00:00','18:00:00',14),(104,'Software systems 1','Session',2,'2017-01-22','08:30:00','10:30:00',14),(105,'Coffee','Break',1,'2017-01-22','10:30:00','11:00:00',14),(106,'Software systems 2','Session',4,'2017-01-22','11:00:00','13:00:00',14),(107,'Lunch','Break',1,'2017-01-22','13:00:00','14:00:00',14),(108,'Workshop IoT','WS',2,'2017-01-22','14:00:00','17:00:00',14),(109,'Workshop PhD','WS',4,'2017-01-22','14:00:00','17:00:00',14),(110,'Workshop SensorNets','WS',4,'2017-01-22','14:00:00','17:00:00',14),(111,'Workshop CloudTech','WS',5,'2017-01-22','14:00:00','17:00:00',14),(112,'Closing ceremony','Close',1,'2017-01-22','17:00:00','18:00:00',14);
/*!40000 ALTER TABLE `session_conf` ENABLE KEYS */;
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
