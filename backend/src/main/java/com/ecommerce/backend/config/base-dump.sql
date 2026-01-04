-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: e-commerce
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cp_index`
--

DROP TABLE IF EXISTS `cp_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cp_index` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime(6) NOT NULL,
  `description` text DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cp_index`
--

LOCK TABLES `cp_index` WRITE;
/*!40000 ALTER TABLE `cp_index` DISABLE KEYS */;
INSERT INTO `cp_index` VALUES (1,'2026-01-04 10:51:31.000000','Ajustes generales del sistema',_binary '','2026-01-04 10:51:31.000000','Configuración','/admin/config'),(2,'2026-01-04 10:51:31.000000','Gestión de pedidos de clientes',_binary '','2026-01-04 10:51:31.000000','Pedidos','/admin/orders'),(3,'2026-01-04 10:51:31.000000','Gestión de inventario y catálogo',_binary '','2026-01-04 10:51:31.000000','Productos','/admin/products'),(4,'2026-01-04 10:51:31.000000','Administración de usuarios web',_binary '','2026-01-04 10:51:31.000000','Usuarios','/admin/users');
/*!40000 ALTER TABLE `cp_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cp_role`
--

DROP TABLE IF EXISTS `cp_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cp_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cp_role`
--

LOCK TABLES `cp_role` WRITE;
/*!40000 ALTER TABLE `cp_role` DISABLE KEYS */;
INSERT INTO `cp_role` VALUES (1,'SUPER_ADMIN'),(2,'COMERCIAL');
/*!40000 ALTER TABLE `cp_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cp_user`
--

DROP TABLE IF EXISTS `cp_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `surname` varchar(150) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `cp_role_id` int(11) DEFAULT NULL,
  `created` datetime(6) NOT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `deleted` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2j8pf7yg9eq78i0arve2juq2b` (`cp_role_id`),
  CONSTRAINT `FK2j8pf7yg9eq78i0arve2juq2b` FOREIGN KEY (`cp_role_id`) REFERENCES `cp_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cp_user`
--

LOCK TABLES `cp_user` WRITE;
/*!40000 ALTER TABLE `cp_user` DISABLE KEYS */;
INSERT INTO `cp_user` VALUES (1,'Admin','Mascotas','erwin.lino@paro.es','$2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2',_binary '',1,'2026-01-04 11:08:38.000000',NULL,NULL);
/*!40000 ALTER TABLE `cp_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_event`
--

DROP TABLE IF EXISTS `logging_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logging_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestmp` bigint(20) NOT NULL,
  `formatted_message` text NOT NULL,
  `logger_name` varchar(254) NOT NULL,
  `level_string` varchar(254) NOT NULL,
  `thread_name` varchar(254) DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `arg0` varchar(254) DEFAULT NULL,
  `arg1` varchar(254) DEFAULT NULL,
  `arg2` varchar(254) DEFAULT NULL,
  `arg3` varchar(254) DEFAULT NULL,
  `caller_filename` varchar(254) NOT NULL,
  `caller_class` varchar(254) NOT NULL,
  `caller_method` varchar(254) NOT NULL,
  `caller_line` char(4) NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_event`
--

LOCK TABLES `logging_event` WRITE;
/*!40000 ALTER TABLE `logging_event` DISABLE KEYS */;
INSERT INTO `logging_event` VALUES (1,1767521641468,'Starting BackendApplication using Java 21.0.2 with PID 3000 (C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend\\target\\classes started by Erwin in C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarting','53'),(2,1767521641526,'No active profile set, falling back to 1 default profile: \"default\"','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'SpringApplication.java','org.springframework.boot.SpringApplication','logStartupProfileInfo','652'),(3,1767521648512,'Starting BackendApplication using Java 21.0.2 with PID 1880 (C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend\\target\\classes started by Erwin in C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarting','53'),(4,1767521648560,'No active profile set, falling back to 1 default profile: \"default\"','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'SpringApplication.java','org.springframework.boot.SpringApplication','logStartupProfileInfo','652'),(5,1767521655104,'Started BackendApplication in 7.361 seconds (process running for 8.035)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarted','59'),(6,1767521667434,'\n\n[34m--- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'--- INICIO PROCESO LOGIN ---','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(7,1767521667440,'\n\n[34m--- Email recibido:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Email recibido: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(8,1767521667444,'\n\n[34m--- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Password recibida (longitud): ','5',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(9,1767521667578,'\n\n[34m--- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Llamando a authenticationManager.authenticate...','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(10,1767521667674,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(11,1767521667756,'\n\n[33m--- ERROR: Credenciales inválidas para:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','WARN','http-nio-8080-exec-2',0,'ERROR: Credenciales inválidas para: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','warning','26'),(12,1767521723314,'\n\n[34m--- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'--- INICIO PROCESO LOGIN ---','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(13,1767521723320,'\n\n[34m--- Email recibido:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'Email recibido: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(14,1767521723324,'\n\n[34m--- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'Password recibida (longitud): ','5',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(15,1767521723333,'\n\n[34m--- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'Llamando a authenticationManager.authenticate...','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(16,1767521723338,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(17,1767521723421,'\n\n[33m--- ERROR: Credenciales inválidas para:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','WARN','http-nio-8080-exec-5',0,'ERROR: Credenciales inválidas para: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','warning','26'),(18,1767521767348,'Starting BackendApplication using Java 21.0.2 with PID 19332 (C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend\\target\\classes started by Erwin in C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarting','53'),(19,1767521767394,'No active profile set, falling back to 1 default profile: \"default\"','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'SpringApplication.java','org.springframework.boot.SpringApplication','logStartupProfileInfo','652'),(20,1767521774380,'Started BackendApplication in 7.703 seconds (process running for 8.381)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarted','59'),(21,1767521795371,'\n\n[34m--- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'--- INICIO PROCESO LOGIN ---','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(22,1767521795377,'\n\n[34m--- Email recibido:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Email recibido: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(23,1767521795382,'\n\n[34m--- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Password recibida (longitud): ','5',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(24,1767521795545,'\n\n[34m--- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Llamando a authenticationManager.authenticate...','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(25,1767521795655,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(26,1767521795739,'\n\n[33m--- ERROR: Credenciales inválidas para:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','WARN','http-nio-8080-exec-2',0,'ERROR: Credenciales inválidas para: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','warning','26'),(27,1767521853491,'\n\n[34m--- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'--- INICIO PROCESO LOGIN ---','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(28,1767521853497,'\n\n[34m--- Email recibido:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Email recibido: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(29,1767521853501,'\n\n[34m--- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Password recibida (longitud): ','5',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(30,1767521853510,'\n\n[34m--- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Llamando a authenticationManager.authenticate...','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(31,1767521853516,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(32,1767521853598,'\n\n[33m--- ERROR: Credenciales inválidas para:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','WARN','http-nio-8080-exec-4',0,'ERROR: Credenciales inválidas para: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','warning','26'),(33,1767521975474,'\n\n[34m--- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'--- INICIO PROCESO LOGIN ---','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(34,1767521975480,'\n\n[34m--- Email recibido:  : erwin.lino@paro.com ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Email recibido: ','erwin.lino@paro.com',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(35,1767521975484,'\n\n[34m--- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Password recibida (longitud): ','5',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(36,1767521975582,'\n\n[34m--- ¿COINCIDENCIA MANUAL BCrypt?:  : NO (FALSE) ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'¿COINCIDENCIA MANUAL BCrypt?: ','NO (FALSE)',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(37,1767521975587,'\n\n[34m--- Hash actual en DB:  : $2a$10$8.UnVuG9HHgffUDAlk8q7Ou5f2L9nRqBAt1YvO6S.m9K7vNqS75L2 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Hash actual en DB: ','$2a$10$8.UnVuG9HHgffUDAlk8q7Ou5f2L9nRqBAt1YvO6S.m9K7vNqS75L2',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(38,1767521975663,'\n\n[34m--- Si generamos un hash ahora para \'12345\' sería:  : $2a$10$VIPBBWT.O1sonv9y7dVGdesPMsHkom2bO/TcRKPt.DWV.MZZogFfy ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Si generamos un hash ahora para \'12345\' sería: ','$2a$10$VIPBBWT.O1sonv9y7dVGdesPMsHkom2bO/TcRKPt.DWV.MZZogFfy',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(39,1767521975668,'\n\n[34m--- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Llamando a authenticationManager.authenticate...','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(40,1767521975674,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.com ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Intentando cargar usuario por email: ','erwin.lino@paro.com',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(41,1767521975682,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.com ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'CP User encontrado: ','erwin.lino@paro.com',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(42,1767521975759,'\n\n[33m--- ERROR: Credenciales inválidas para:  : erwin.lino@paro.com ---[0m\n\n','com.ecommerce.backend.util.UseLogger','WARN','http-nio-8080-exec-7',0,'ERROR: Credenciales inválidas para: ','erwin.lino@paro.com',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','warning','26'),(43,1767521986623,'\n\n[34m--- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'--- INICIO PROCESO LOGIN ---','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(44,1767521986628,'\n\n[34m--- Email recibido:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Email recibido: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(45,1767521986633,'\n\n[34m--- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Password recibida (longitud): ','5',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(46,1767521986712,'\n\n[34m--- ¿COINCIDENCIA MANUAL BCrypt?:  : NO (FALSE) ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'¿COINCIDENCIA MANUAL BCrypt?: ','NO (FALSE)',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(47,1767521986717,'\n\n[34m--- Hash actual en DB:  : $2a$10$8.UnVuG9HHgffUDAlk8q7Ou5f2L9nRqBAt1YvO6S.m9K7vNqS75L2 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Hash actual en DB: ','$2a$10$8.UnVuG9HHgffUDAlk8q7Ou5f2L9nRqBAt1YvO6S.m9K7vNqS75L2',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(48,1767521986792,'\n\n[34m--- Si generamos un hash ahora para \'12345\' sería:  : $2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Si generamos un hash ahora para \'12345\' sería: ','$2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(49,1767521986798,'\n\n[34m--- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Llamando a authenticationManager.authenticate...','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(50,1767521986804,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(51,1767521986812,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(52,1767521986887,'\n\n[33m--- ERROR: Credenciales inválidas para:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','WARN','http-nio-8080-exec-9',0,'ERROR: Credenciales inválidas para: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','warning','26'),(53,1767522018658,'\n\n[34m--- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'--- INICIO PROCESO LOGIN ---','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(54,1767522018663,'\n\n[34m--- Email recibido:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Email recibido: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(55,1767522018668,'\n\n[34m--- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Password recibida (longitud): ','5',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(56,1767522018749,'\n\n[34m--- ¿COINCIDENCIA MANUAL BCrypt?:  : SÍ (TRUE) ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'¿COINCIDENCIA MANUAL BCrypt?: ','SÍ (TRUE)',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(57,1767522018753,'\n\n[34m--- Hash actual en DB:  : $2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Hash actual en DB: ','$2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(58,1767522018827,'\n\n[34m--- Si generamos un hash ahora para \'12345\' sería:  : $2a$10$o6R2n/VKWPXW0uGjfbKEzum39dOHyfZWx6yptng839InYUXPEwv2G ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Si generamos un hash ahora para \'12345\' sería: ','$2a$10$o6R2n/VKWPXW0uGjfbKEzum39dOHyfZWx6yptng839InYUXPEwv2G',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(59,1767522018833,'\n\n[34m--- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Llamando a authenticationManager.authenticate...','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(60,1767522018837,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(61,1767522018844,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(62,1767522018918,'\n\n[34m--- ¡Autenticación exitosa en Spring Security! :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'¡Autenticación exitosa en Spring Security!','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(63,1767522018966,'\n\n[34m--- Login Finalizado como ADMIN para:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Login Finalizado como ADMIN para: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(64,1767522019046,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(65,1767522019046,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(66,1767522019088,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(67,1767522019088,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(68,1767522077938,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-8',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(69,1767522077938,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(70,1767522077949,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(71,1767522077949,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-8',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(72,1767522081670,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(73,1767522081672,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(74,1767522081681,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(75,1767522081681,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(76,1767522082483,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(77,1767522082493,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(78,1767522084559,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(79,1767522084562,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-8',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(80,1767522084571,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(81,1767522084573,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-8',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(82,1767522085092,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(83,1767522085093,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(84,1767522085104,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(85,1767522085104,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(86,1767522086499,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(87,1767522086508,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(88,1767522092190,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(89,1767522092198,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(90,1767522093969,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(91,1767522093982,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(92,1767522093993,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(93,1767522093991,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(94,1767522094007,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(95,1767522094020,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(96,1767522097877,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-6',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(97,1767522097887,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-6',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(98,1767522097903,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(99,1767522097913,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(100,1767522100019,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(101,1767522100028,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(102,1767522100051,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(103,1767522100059,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(104,1767522100399,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(105,1767522100409,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(106,1767522100432,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(107,1767522100443,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(108,1767522284088,'Starting BackendApplication using Java 21.0.2 with PID 5608 (C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend\\target\\classes started by Erwin in C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarting','53'),(109,1767522284146,'No active profile set, falling back to 1 default profile: \"default\"','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'SpringApplication.java','org.springframework.boot.SpringApplication','logStartupProfileInfo','652'),(110,1767522291486,'Started BackendApplication in 8.117 seconds (process running for 8.945)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarted','59'),(111,1767522297440,'Starting BackendApplication using Java 21.0.2 with PID 6360 (C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend\\target\\classes started by Erwin in C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarting','53'),(112,1767522297487,'No active profile set, falling back to 1 default profile: \"default\"','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'SpringApplication.java','org.springframework.boot.SpringApplication','logStartupProfileInfo','652'),(113,1767522305230,'Started BackendApplication in 8.443 seconds (process running for 9.106)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarted','59'),(114,1767522311726,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','--- INICIO PROCESO LOGIN ---','',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(115,1767522311732,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- Email recibido:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','Email recibido: ','erwin.lino@paro.es',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(116,1767522311739,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','Password recibida (longitud): ','5',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(117,1767522311990,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- ¿COINCIDENCIA MANUAL BCrypt?:  : SÍ (TRUE) ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','¿COINCIDENCIA MANUAL BCrypt?: ','SÍ (TRUE)',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(118,1767522311997,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- Hash actual en DB:  : $2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','Hash actual en DB: ','$2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(119,1767522312076,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- Si generamos un hash ahora para \'12345\' sería:  : $2a$10$r9sTkMdTO589MwMmdtE9L.W2gnXduYlJ6sOYfAYxMr2huux0GKqZm ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','Si generamos un hash ahora para \'12345\' sería: ','$2a$10$r9sTkMdTO589MwMmdtE9L.W2gnXduYlJ6sOYfAYxMr2huux0GKqZm',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(120,1767522312082,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','Llamando a authenticationManager.authenticate...','',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(121,1767522312165,'\n\n[34m[com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername] --- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername','Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(122,1767522312174,'\n\n[34m[com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername] --- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername','CP User encontrado: ','erwin.lino@paro.es',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(123,1767522312253,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- ¡Autenticación exitosa en Spring Security! :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','¡Autenticación exitosa en Spring Security!','',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(124,1767522312312,'\n\n[34m[com.ecommerce.backend.controllers.AuthController.login] --- Login Finalizado como ADMIN para:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'com.ecommerce.backend.controllers.AuthController.login','Login Finalizado como ADMIN para: ','erwin.lino@paro.es',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(125,1767522312405,'\n\n[34m[com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername] --- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-6',0,'com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername','Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(126,1767522312405,'\n\n[34m[com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername] --- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername','Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(127,1767522312461,'\n\n[34m[com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername] --- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-6',0,'com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername','CP User encontrado: ','erwin.lino@paro.es',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(128,1767522312461,'\n\n[34m[com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername] --- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'com.ecommerce.backend.security.WebUserDetailsService.loadUserByUsername','CP User encontrado: ','erwin.lino@paro.es',NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','29'),(129,1767522422168,'Starting BackendApplication using Java 21.0.2 with PID 13348 (C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend\\target\\classes started by Erwin in C:\\Users\\erwin\\Downloads\\3.UnderVolt\\1.JAVA\\first_project\\backend)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarting','53'),(130,1767522422220,'No active profile set, falling back to 1 default profile: \"default\"','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'SpringApplication.java','org.springframework.boot.SpringApplication','logStartupProfileInfo','652'),(131,1767522429019,'Started BackendApplication in 7.528 seconds (process running for 8.236)','com.ecommerce.backend.BackendApplication','INFO','main',0,NULL,NULL,NULL,NULL,'StartupInfoLogger.java','org.springframework.boot.StartupInfoLogger','logStarted','59'),(132,1767522445932,'\n\n[34m--- --- INICIO PROCESO LOGIN --- :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'--- INICIO PROCESO LOGIN ---','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(133,1767522445938,'\n\n[34m--- Email recibido:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Email recibido: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(134,1767522445943,'\n\n[34m--- Password recibida (longitud):  : 5 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Password recibida (longitud): ','5',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(135,1767522446184,'\n\n[34m--- ¿COINCIDENCIA MANUAL BCrypt?:  : SÍ (TRUE) ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'¿COINCIDENCIA MANUAL BCrypt?: ','SÍ (TRUE)',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(136,1767522446189,'\n\n[34m--- Hash actual en DB:  : $2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2 ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Hash actual en DB: ','$2a$10$QTRoszoc/XB28OX2nMcCG.CEi1/BSz3UO9j.JiMVhstcmz00OgSJ2',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(137,1767522446270,'\n\n[34m--- Si generamos un hash ahora para \'12345\' sería:  : $2a$10$oRCB6N08oQE80nUTfWwyTutz4KF34cvZ32B4C6.OrHhhGRgJrtihu ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Si generamos un hash ahora para \'12345\' sería: ','$2a$10$oRCB6N08oQE80nUTfWwyTutz4KF34cvZ32B4C6.OrHhhGRgJrtihu',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(138,1767522446275,'\n\n[34m--- Llamando a authenticationManager.authenticate... :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Llamando a authenticationManager.authenticate...','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(139,1767522446354,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(140,1767522446362,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(141,1767522446443,'\n\n[34m--- ¡Autenticación exitosa en Spring Security! :  ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'¡Autenticación exitosa en Spring Security!','',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(142,1767522446500,'\n\n[34m--- Login Finalizado como ADMIN para:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Login Finalizado como ADMIN para: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(143,1767522446577,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(144,1767522446577,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-6',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(145,1767522446627,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-6',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(146,1767522446627,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(147,1767522474150,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(148,1767522474165,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(149,1767522474251,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(150,1767522474263,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(151,1767522474701,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(152,1767522474714,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(153,1767522474752,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(154,1767522474764,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-4',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(155,1767522475149,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-8',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(156,1767522475163,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-8',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(157,1767522475188,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(158,1767522475201,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(159,1767522477374,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(160,1767522477385,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(161,1767522477416,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(162,1767522477427,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(163,1767522477959,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(164,1767522477970,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-1',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(165,1767522477999,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(166,1767522478010,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(167,1767522480815,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(168,1767522480827,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-5',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(169,1767522480857,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-6',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(170,1767522480869,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-6',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(171,1767522502062,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(172,1767522502072,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(173,1767522502107,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(174,1767522502118,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(175,1767522509503,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(176,1767522509513,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-3',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(177,1767522562626,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(178,1767522562635,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-2',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(179,1767522567442,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(180,1767522567445,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(181,1767522567445,'\n\n[34m--- Intentando cargar usuario por email:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'Intentando cargar usuario por email: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(182,1767522567456,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-7',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(183,1767522567457,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-9',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16'),(184,1767522567456,'\n\n[34m--- CP User encontrado:  : erwin.lino@paro.es ---[0m\n\n','com.ecommerce.backend.util.UseLogger','INFO','http-nio-8080-exec-10',0,'CP User encontrado: ','erwin.lino@paro.es',NULL,NULL,'UseLogger.java','com.ecommerce.backend.util.UseLogger','info','16');
/*!40000 ALTER TABLE `logging_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_event_exception`
--

DROP TABLE IF EXISTS `logging_event_exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logging_event_exception` (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) NOT NULL,
  PRIMARY KEY (`event_id`,`i`),
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_event_exception`
--

LOCK TABLES `logging_event_exception` WRITE;
/*!40000 ALTER TABLE `logging_event_exception` DISABLE KEYS */;
/*!40000 ALTER TABLE `logging_event_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_event_property`
--

DROP TABLE IF EXISTS `logging_event_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logging_event_property` (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254) NOT NULL,
  `mapped_value` text DEFAULT NULL,
  PRIMARY KEY (`event_id`,`mapped_key`),
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_event_property`
--

LOCK TABLES `logging_event_property` WRITE;
/*!40000 ALTER TABLE `logging_event_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `logging_event_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_cart`
--

DROP TABLE IF EXISTS `shop_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime(6) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `total_items` int(11) DEFAULT NULL,
  `shop_cart_status_id` int(11) DEFAULT NULL,
  `web_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqhat60auu52gbd0rrmgfddyr5` (`shop_cart_status_id`),
  KEY `FK284pll9vg4xkxi7bn345ghg7y` (`web_user_id`),
  CONSTRAINT `FK284pll9vg4xkxi7bn345ghg7y` FOREIGN KEY (`web_user_id`) REFERENCES `web_user` (`id`),
  CONSTRAINT `FKqhat60auu52gbd0rrmgfddyr5` FOREIGN KEY (`shop_cart_status_id`) REFERENCES `shop_cart_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart`
--

LOCK TABLES `shop_cart` WRITE;
/*!40000 ALTER TABLE `shop_cart` DISABLE KEYS */;
INSERT INTO `shop_cart` VALUES (8,'2026-01-04 11:01:58.000000',0,0,1,1);
/*!40000 ALTER TABLE `shop_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_cart_item`
--

DROP TABLE IF EXISTS `shop_cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_cart_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `shop_cart_id` int(11) DEFAULT NULL,
  `shop_product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1udvu73rvu48ykbej9x616h4t` (`shop_cart_id`),
  KEY `FKah2jdi428xn4qqq7ouol46vkt` (`shop_product_id`),
  CONSTRAINT `FK1udvu73rvu48ykbej9x616h4t` FOREIGN KEY (`shop_cart_id`) REFERENCES `shop_cart` (`id`),
  CONSTRAINT `FKah2jdi428xn4qqq7ouol46vkt` FOREIGN KEY (`shop_product_id`) REFERENCES `shop_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart_item`
--

LOCK TABLES `shop_cart_item` WRITE;
/*!40000 ALTER TABLE `shop_cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_cart_status`
--

DROP TABLE IF EXISTS `shop_cart_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_cart_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `deleted` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart_status`
--

LOCK TABLES `shop_cart_status` WRITE;
/*!40000 ALTER TABLE `shop_cart_status` DISABLE KEYS */;
INSERT INTO `shop_cart_status` VALUES (1,'ACTIVE',NULL),(2,'ABANDONED',NULL);
/*!40000 ALTER TABLE `shop_cart_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_index`
--

DROP TABLE IF EXISTS `shop_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_index` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime(6) NOT NULL,
  `description` text DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_index`
--

LOCK TABLES `shop_index` WRITE;
/*!40000 ALTER TABLE `shop_index` DISABLE KEYS */;
INSERT INTO `shop_index` VALUES (1,'2026-01-04 10:51:27.000000','Página principal de la tienda','2026-01-04 10:51:27.000000','Inicio','/home'),(2,'2026-01-04 10:51:27.000000','Listado completo de productos','2026-01-04 10:51:27.000000','Productos','/products'),(3,'2026-01-04 10:51:27.000000','Mi carrito de compras','2026-01-04 10:51:27.000000','Carrito','/cart'),(4,'2026-01-04 10:51:27.000000','Historial de pedidos del cliente','2026-01-04 10:51:27.000000','Mis Pedidos','/orders');
/*!40000 ALTER TABLE `shop_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_order`
--

DROP TABLE IF EXISTS `shop_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime(6) DEFAULT NULL,
  `deleted` datetime(6) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `poke_gift` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`poke_gift`)),
  `total_amount` double NOT NULL,
  `total_items` int(11) NOT NULL,
  `shop_order_status_id` int(11) NOT NULL,
  `web_user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9su9tdtwedfm2up6xjj864xpp` (`shop_order_status_id`),
  KEY `FKldvix51595kuwhopfjabyctn7` (`web_user_id`),
  CONSTRAINT `FK9su9tdtwedfm2up6xjj864xpp` FOREIGN KEY (`shop_order_status_id`) REFERENCES `shop_order_status` (`id`),
  CONSTRAINT `FKldvix51595kuwhopfjabyctn7` FOREIGN KEY (`web_user_id`) REFERENCES `web_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_order`
--

LOCK TABLES `shop_order` WRITE;
/*!40000 ALTER TABLE `shop_order` DISABLE KEYS */;
INSERT INTO `shop_order` VALUES (2,'2026-01-04 11:04:01.000000',NULL,'2026-01-04 11:04:01.000000','[{\"id\":100,\"name\":\"voltorb\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/100.png\"},{\"id\":134,\"name\":\"vaporeon\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/134.png\"},{\"id\":21,\"name\":\"spearow\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/21.png\"},{\"id\":67,\"name\":\"machoke\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/67.png\"},{\"id\":37,\"name\":\"vulpix\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/37.png\"},{\"id\":77,\"name\":\"ponyta\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/77.png\"},{\"id\":83,\"name\":\"farfetchd\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/83.png\"},{\"id\":86,\"name\":\"seel\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/86.png\"},{\"id\":142,\"name\":\"aerodactyl\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/142.png\"},{\"id\":16,\"name\":\"pidgey\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png\"}]',454.65000000000003,7,1,1),(3,'2026-01-04 11:04:29.000000',NULL,'2026-01-04 11:04:29.000000','[{\"id\":86,\"name\":\"seel\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/86.png\"},{\"id\":93,\"name\":\"haunter\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/93.png\"},{\"id\":79,\"name\":\"slowpoke\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/79.png\"},{\"id\":102,\"name\":\"exeggcute\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/102.png\"},{\"id\":81,\"name\":\"magnemite\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/81.png\"},{\"id\":95,\"name\":\"onix\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/95.png\"},{\"id\":48,\"name\":\"venonat\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/48.png\"},{\"id\":143,\"name\":\"snorlax\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/143.png\"},{\"id\":137,\"name\":\"porygon\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/137.png\"},{\"id\":38,\"name\":\"ninetales\",\"imageUrl\":\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/38.png\"}]',519.6,8,1,1);
/*!40000 ALTER TABLE `shop_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_order_item`
--

DROP TABLE IF EXISTS `shop_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `unit_price` double NOT NULL,
  `shop_order_id` int(11) NOT NULL,
  `shop_product_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq27wqsvqo8ss8lh5p8t1qm29c` (`shop_order_id`),
  KEY `FKnmkm7p41vq985c4co82vcqfy6` (`shop_product_id`),
  CONSTRAINT `FKnmkm7p41vq985c4co82vcqfy6` FOREIGN KEY (`shop_product_id`) REFERENCES `shop_product` (`id`),
  CONSTRAINT `FKq27wqsvqo8ss8lh5p8t1qm29c` FOREIGN KEY (`shop_order_id`) REFERENCES `shop_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_order_item`
--

LOCK TABLES `shop_order_item` WRITE;
/*!40000 ALTER TABLE `shop_order_item` DISABLE KEYS */;
INSERT INTO `shop_order_item` VALUES (1,7,454.65000000000003,64.95,2,1),(2,8,519.6,64.95,3,1);
/*!40000 ALTER TABLE `shop_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_order_status`
--

DROP TABLE IF EXISTS `shop_order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_order_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `deleted` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_order_status`
--

LOCK TABLES `shop_order_status` WRITE;
/*!40000 ALTER TABLE `shop_order_status` DISABLE KEYS */;
INSERT INTO `shop_order_status` VALUES (1,'PREPARING',NULL),(2,'SHIPPED',NULL),(3,'DELIVERED',NULL),(4,'CANCELLED',NULL);
/*!40000 ALTER TABLE `shop_order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_product`
--

DROP TABLE IF EXISTS `shop_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `short_description` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `current_stock` int(11) DEFAULT NULL,
  `shop_product_brand_id` int(11) DEFAULT NULL,
  `shop_product_measurement_id` int(11) DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `deleted` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpw3mq9s2a1pys78peye6i96vh` (`shop_product_brand_id`),
  KEY `FKrxb02aii93dj0wkxnnnoaf7f5` (`shop_product_measurement_id`),
  CONSTRAINT `FKpw3mq9s2a1pys78peye6i96vh` FOREIGN KEY (`shop_product_brand_id`) REFERENCES `shop_product_brand` (`id`),
  CONSTRAINT `FKrxb02aii93dj0wkxnnnoaf7f5` FOREIGN KEY (`shop_product_measurement_id`) REFERENCES `shop_product_measurement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_product`
--

LOCK TABLES `shop_product` WRITE;
/*!40000 ALTER TABLE `shop_product` DISABLE KEYS */;
INSERT INTO `shop_product` VALUES (1,'Medium Adult Dog Cambiado','Pienso para perros medianos','Nutrición equilibrada para perros de 11 a 25 kg con soporte inmunológico.',64.95,25,1,1,'2026-01-04 10:55:36.000000','2026-01-04 11:29:22.000000',NULL),(2,'Sterilised Cat Optirender','Comida para gatos esterilizados','Ayuda a mantener la salud renal y el peso ideal tras la esterilización.',28.5,100,2,3,'2026-01-04 10:55:36.000000',NULL,NULL),(3,'Acana Wild Coast','Pienso Grain Free para perros','Elaborado con pescado sostenible, ideal para pieles sensibles y dietas sin grano.',72,25,3,1,'2026-01-04 10:55:36.000000',NULL,NULL),(4,'Pack Húmedo Gastrointestinal','Dieta veterinaria para gatos','Pack de 12 latas de paté para gatos con problemas digestivos temporales.',19.9,60,4,2,'2026-01-04 10:55:36.000000',NULL,NULL);
/*!40000 ALTER TABLE `shop_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_product_brand`
--

DROP TABLE IF EXISTS `shop_product_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_product_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `deleted` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_product_brand`
--

LOCK TABLES `shop_product_brand` WRITE;
/*!40000 ALTER TABLE `shop_product_brand` DISABLE KEYS */;
INSERT INTO `shop_product_brand` VALUES (1,'Royal Canin',_binary '','2026-01-04 10:55:21.000000',NULL,NULL),(2,'Purina Pro Plan',_binary '','2026-01-04 10:55:21.000000',NULL,NULL),(3,'Acana',_binary '','2026-01-04 10:55:21.000000',NULL,NULL),(4,'Advance',_binary '','2026-01-04 10:55:21.000000',NULL,NULL);
/*!40000 ALTER TABLE `shop_product_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_product_measurement`
--

DROP TABLE IF EXISTS `shop_product_measurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_product_measurement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `deleted` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_product_measurement`
--

LOCK TABLES `shop_product_measurement` WRITE;
/*!40000 ALTER TABLE `shop_product_measurement` DISABLE KEYS */;
INSERT INTO `shop_product_measurement` VALUES (1,'Saco 15kg','Formato ahorro para perros grandes',15,'2026-01-04 10:55:29.000000',NULL,NULL),(2,'Pack Latas x12','Pack de comida húmeda 400g',12,'2026-01-04 10:55:29.000000',NULL,NULL),(3,'Saco 3kg','Formato para gatos o razas pequeñas',3,'2026-01-04 10:55:29.000000',NULL,NULL);
/*!40000 ALTER TABLE `shop_product_measurement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_user`
--

DROP TABLE IF EXISTS `web_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `last_name` varchar(150) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `cif` varchar(50) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `is_blocked` bit(1) NOT NULL,
  `data` text DEFAULT NULL,
  `last_time_login` datetime(6) DEFAULT NULL,
  `created` datetime(6) NOT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `deleted` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKkuqpi3q2x3r3e9gukuvnm6fho` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_user`
--

LOCK TABLES `web_user` WRITE;
/*!40000 ALTER TABLE `web_user` DISABLE KEYS */;
INSERT INTO `web_user` VALUES (1,'Erwin David','Lino Zabala','erwinlino011@gmail.com','1234','$2a$10$qzuLwmHFKzLhPA/B/Xn3oOS0LBVhH7rAI9Clbvf7Bx98C5vsUo0KG',_binary '',_binary '\0',NULL,NULL,'2026-01-04 10:46:08.000000','2026-01-04 11:21:32.000000',NULL);
/*!40000 ALTER TABLE `web_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'e-commerce'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-04 11:34:10
