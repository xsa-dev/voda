-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: telegram
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Current Database: `telegram`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `telegram` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `telegram`;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `idrole` int(11) NOT NULL,
  `rolename` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (0,'client'),(1,'administrator');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `telegramid` int(11) NOT NULL,
  `telegramname` varchar(45) DEFAULT NULL,
  `telegramnumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`telegramid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (188416619,'Aleksey',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'telegram'
--

--
-- Dumping routines for database 'telegram'
--
/*!50003 DROP PROCEDURE IF EXISTS `registration` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`devuser`@`%` PROCEDURE `registration`(IN tid numeric(15), IN tnumb numeric(10), in tname varchar(255) )
BEGIN
	declare tname varchar(45);
	declare tnumb numeric(10);
	declare tid numeric(10);

	   
    INSERT INTO `telegram`.`users` (`telegramid`, `telegramname`, `telegramnumber`) 
    VALUES (tid, tnumb, tname);
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Current Database: `clpr`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `clpr` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `clpr`;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `idcontacts` int(11) NOT NULL,
  `contactMethod` varchar(45) DEFAULT NULL,
  `idrow` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrow`),
  UNIQUE KEY `uri_UNIQUE` (`uri`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (1,'FB',1,NULL),(1,'VK',2,NULL);
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `contactsandmethods`
--

DROP TABLE IF EXISTS `contactsandmethods`;
/*!50001 DROP VIEW IF EXISTS `contactsandmethods`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `contactsandmethods` AS SELECT 
 1 AS `idpeople`,
 1 AS `peopleFistName`,
 1 AS `peopleLastName`,
 1 AS `peopleSecondName`,
 1 AS `idrow`,
 1 AS `idcontacts`,
 1 AS `contactMethod`,
 1 AS `contactUri`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `contactsmethods`
--

DROP TABLE IF EXISTS `contactsmethods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactsmethods` (
  `idcontactsMethods` varchar(10) NOT NULL,
  `contactsMethodsName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcontactsMethods`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactsmethods`
--

LOCK TABLES `contactsmethods` WRITE;
/*!40000 ALTER TABLE `contactsmethods` DISABLE KEYS */;
INSERT INTO `contactsmethods` VALUES ('-','-'),('FB','Facebook'),('TEL','Телефон'),('TM','Telegram'),('VK','Вконтакте'),('WA','WhatsApp');
/*!40000 ALTER TABLE `contactsmethods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `idpeople` int(11) NOT NULL,
  `peopleFistName` varchar(45) DEFAULT NULL,
  `peopleLastName` varchar(45) DEFAULT NULL,
  `peopleSecondName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpeople`),
  UNIQUE KEY `idpeople_UNIQUE` (`idpeople`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'Алексей','Савин','Валерьевич');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `idproducts` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `protein` varchar(45) NOT NULL,
  `fat` varchar(45) NOT NULL,
  `carbohydrate` varchar(45) NOT NULL,
  PRIMARY KEY (`idproducts`),
  UNIQUE KEY `productsname_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (4,'test0','1','1','1'),(5,'testo','1','1','1'),(6,'addFromBot','1','1','1');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'clpr'
--

--
-- Dumping routines for database 'clpr'
--
/*!50003 DROP PROCEDURE IF EXISTS `insertPeopleContact` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`devuser`@`%` PROCEDURE `insertPeopleContact`(IN pfname varchar(255))
BEGIN
declare pfname, plname, psecond varchar(45);
declare pcontactsmethod varchar(2);
declare pcontactsuri varchar(255);



END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Current Database: `telegram`
--

USE `telegram`;

--
-- Current Database: `clpr`
--

USE `clpr`;

--
-- Final view structure for view `contactsandmethods`
--

/*!50001 DROP VIEW IF EXISTS `contactsandmethods`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`devuser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `contactsandmethods` AS select `p`.`idpeople` AS `idpeople`,`p`.`peopleFistName` AS `peopleFistName`,`p`.`peopleLastName` AS `peopleLastName`,`p`.`peopleSecondName` AS `peopleSecondName`,`c`.`idrow` AS `idrow`,`c`.`idcontacts` AS `idcontacts`,`c`.`contactMethod` AS `contactMethod`,`c`.`uri` AS `contactUri` from (`people` `p` join `contacts` `c` on((`c`.`idcontacts` = `p`.`idpeople`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-25 23:13:18
