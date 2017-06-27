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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `linkedin` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` bit(1) NOT NULL,
  `shirt_size` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `profile_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2017-06-25 22:24:10','asdf@!adf.asdf','','asdf',NULL,'asdf',NULL,'asdfasdf0A<','asdf','\0',NULL,1,'asdf',NULL),(2,'2017-06-25 22:30:58','sdf','\0','asdf',NULL,'adsfasdf',NULL,'asdfasdf0A<','0654','\0',NULL,0,'asdfasdf',NULL),(4,NULL,NULL,'\0','fafala',NULL,'simimti',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(5,NULL,NULL,'\0','George',NULL,'Paunovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(6,NULL,NULL,'\0','Zoran',NULL,'Jovanovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(7,NULL,NULL,'\0','Irini',NULL,'Reljin',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(8,NULL,NULL,'\0','Vint',NULL,'Cerf',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(9,NULL,NULL,'\0','Filip',NULL,'Hadzic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(10,NULL,NULL,'\0','Marija',NULL,'Punt',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(11,NULL,NULL,'\0','Zarko',NULL,'Stanisavljevic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(12,NULL,NULL,'\0','Zaki',NULL,'Radivojevic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(13,NULL,NULL,'\0','Uros',NULL,'Radenkovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(14,NULL,NULL,'\0','Marko',NULL,'Micovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(15,NULL,NULL,'\0','Zaki',NULL,'Radivojevic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(16,NULL,NULL,'\0','Sasa',NULL,'Stojanovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(17,NULL,NULL,'\0','Sanja',NULL,'Delcev',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(18,NULL,NULL,'\0','Drazen',NULL,'Draskovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(19,NULL,NULL,'\0','Drazen',NULL,'Draskovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(20,NULL,NULL,'\0','Sanja',NULL,'Delcev',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(21,NULL,NULL,'\0','Bosko',NULL,'Nikolic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(22,NULL,NULL,'\0','Marko',NULL,'Misic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(23,NULL,NULL,'\0','Sasa',NULL,'Stojanovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(24,NULL,NULL,'\0','Zivojin',NULL,'Sustran',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(25,NULL,NULL,'\0','Maja',NULL,'Vukasovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(26,NULL,NULL,'\0','Milana',NULL,'Prodanov',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(27,NULL,NULL,'\0','Stefan',NULL,'Tubic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(28,NULL,NULL,'\0','Nemanja',NULL,'Kojic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(29,NULL,NULL,'\0','Filip',NULL,'Hadzic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(30,NULL,NULL,'\0','Stefan',NULL,'Tubic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(31,NULL,NULL,'\0','Jelica',NULL,'Protic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(32,NULL,NULL,'\0','Dragan',NULL,'Bojic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(33,NULL,NULL,'\0','Nenad',NULL,'Korolija',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(34,NULL,NULL,'\0','Jovan',NULL,'Jovanovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(35,NULL,NULL,'\0','Petar',NULL,'Petrovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(36,NULL,NULL,'\0','Maja',NULL,'Vukasovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(37,NULL,NULL,'\0','Nemanja',NULL,'Kojic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(38,NULL,NULL,'\0','Dragan',NULL,'Bojic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(39,NULL,NULL,'\0','Veljko',NULL,'Milutinovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(40,NULL,NULL,'\0','Igor',NULL,'Tartalja',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(41,NULL,NULL,'\0','Jelica',NULL,'Protic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(42,NULL,NULL,'\0','Thomas',NULL,'Erl',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(43,NULL,NULL,'\0','Branko',NULL,'Milutinovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(44,NULL,NULL,'\0','George',NULL,'Paunovic',NULL,NULL,NULL,'\0',NULL,2,NULL,NULL),(45,'2017-06-26 00:00:00','asdfasdf@asdfa.asdf','\0','mladen',NULL,'canovic',NULL,'asdfasdf0A<','064064606','\0',NULL,2,'asdfasdfasdf',NULL),(46,'2017-06-27 00:00:00','sdfsdf@sdf.sdf','\0','sdf','sdf','sdf','sdf','asdfasdf0A<','56461','','XL',2,'sdf','[001036].jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
