-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: database-1.cqhqygeze8aq.us-east-2.rds.amazonaws.com    Database: ecounidb
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `historial` (
  `id_historial` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(9) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id_historial`),
  KEY `usuFK_idx` (`codigo`),
  CONSTRAINT `usuFK` FOREIGN KEY (`codigo`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` VALUES (1,'20141469j',14,'2019-10-31 15:25:00'),(2,'20141469j',8,'2019-10-31 15:25:00'),(3,'20141469j',11,'2019-10-31 15:35:00'),(4,'20141469j',25,'2019-10-25 12:00:00'),(5,'20141469j',5,'2019-10-25 13:00:00'),(6,'20141469j',20,'2019-10-29 14:00:00'),(7,'20141469j',10,'2019-09-25 15:00:00'),(10,'20141469j',15,'2020-10-12 00:00:00');
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `codigo` varchar(9) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `facultad` varchar(10) DEFAULT NULL,
  `sobre_mi` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `cumple` date DEFAULT NULL,
  `path_foto` varchar(200) DEFAULT NULL,
  `pass` varchar(64) DEFAULT NULL,
  `cantidad` int(11) DEFAULT '0',
  `puntos` int(11) DEFAULT '0',
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('20130504C','Víctor','FC','','victoralegre@uni.pe','999333222','1994-10-29','20130504C_1573164856247.jpeg','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',25,25),('20141469J','Ale','FC','','aleleslie96@gmail.com','958984798','1996-03-09','20141469J_1573142099364.jpeg','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',1800,1800),('20152734A','nelson','FC','','nsanabiom@uni.pe','992621023','1996-02-28','20152734A_1573142354644.jpeg','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',1500,1500),('20180365E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',354,354);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ecounidb'
--
/*!50003 DROP PROCEDURE IF EXISTS `edit_foto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `edit_foto`(IN codigo varchar(9),IN path_foto varchar(200))
BEGIN
	if length(path_foto) = 0 then
		update ecounidb.usuarios u set u.path_foto = null where u.codigo = codigo;
    else
		update ecounidb.usuarios u set u.path_foto = path_foto where u.codigo = codigo;
	end if;
    select u.codigo,u.path_foto from ecounidb.usuarios u where u.codigo= codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `edit_perfil` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `edit_perfil`(IN codigo varchar(9),IN nombre varchar(100),
IN facultad varchar(10),IN email varchar(100),IN sobre_mi varchar(200), IN celular varchar(45), IN cumple date)
BEGIN
	UPDATE
		ecounidb.usuarios u
	SET
		u.nombre = nombre,
        u.facultad = facultad,
        u.sobre_mi = sobre_mi,
        u.email = email,
        u.celular = celular,
        u.cumple = cumple
	where
		u.codigo = codigo;
	
    select * from ecounidb.usuarios u where u.codigo=codigo; 
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `login`(IN cod varchar(9), IN pass varchar(64))
BEGIN
	select
		*
	from
		ecounidb.usuarios u
	where
		u.codigo = cod
		and upper(u.pass) = upper(pass);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reciclar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `reciclar`(IN cod varchar(9), IN cant int,IN fech datetime)
BEGIN
	insert into ecounidb.historial(codigo,cantidad,fecha)
    values (cod,cant,fech);

	update
		ecounidb.usuarios u 
    set
		u.cantidad = (u.cantidad + cant),
        u.puntos = (u.puntos + cant)
	where
		u.codigo = cod;
	select u.codigo, u.cantidad, u.puntos from ecounidb.usuarios u where u.codigo = cod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `rec_historial` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `rec_historial`(IN codigo varchar(9), IN anho int, IN mes int)
BEGIN
	select 
		sum(u.cantidad) as cant_dia,
        day(u.fecha) as dia
	from
		ecounidb.historial u
	where
		u.codigo = codigo
        and year(u.fecha) = anho
		and month(u.fecha) = mes
	group by dia;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top10` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `top10`()
BEGIN
	SET @rank_num = 0;
    SELECT
		(@rank_num:=@rank_num + 1) AS rank_pos,
		u.nombre,
		u.facultad,
		u.cantidad,
        u.path_foto
	FROM
		ecounidb.usuarios u
	ORDER BY
		u.cantidad DESC
	LIMIT
		10;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-08 17:52:12
