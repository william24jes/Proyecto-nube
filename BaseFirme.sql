CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `contenido` varchar(500) NOT NULL,
  `idIncidencia` int NOT NULL,
  `idComentario` varchar(45) NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idComentario`),
  KEY `fk_IS_has_incidencias_incidencias1_idx` (`idIncidencia`),
  KEY `fk_comentarios_usuarios1_idx` (`idUsuario`),
  CONSTRAINT `fk_comentarios_usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`),
  CONSTRAINT `fk_IS_has_incidencias_incidencias1` FOREIGN KEY (`idIncidencia`) REFERENCES `incidencias` (`idIncidencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidencias`
--

DROP TABLE IF EXISTS `incidencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidencias` (
  `idIncidencia` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `idSeguridad` int DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `idzonaPucp` int NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `ubicacion` varchar(200) DEFAULT NULL,
  `foto` blob,
  `destacado` int NOT NULL,
  `fechaHora` datetime NOT NULL,
  `anonimo` tinyint(1) NOT NULL,
  `urgencia` varchar(20) NOT NULL,
  `estadoIncidencia` varchar(30) NOT NULL,
  `numEstrellas` int DEFAULT NULL,
  PRIMARY KEY (`idIncidencia`),
  KEY `fk_incidencias_zonaPucp1_idx` (`idzonaPucp`),
  KEY `fk_incidencias_usuarios1_idx` (`idUsuario`),
  KEY `fk_incidencias_usuarios2_idx` (`idSeguridad`),
  CONSTRAINT `fk_incidencias_usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`),
  CONSTRAINT `fk_incidencias_usuarios2` FOREIGN KEY (`idSeguridad`) REFERENCES `usuarios` (`idUsuario`),
  CONSTRAINT `fk_incidencias_zonaPucp1` FOREIGN KEY (`idzonaPucp`) REFERENCES `zonapucp` (`idzonaPucp`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidencias`
--

LOCK TABLES `incidencias` WRITE;
/*!40000 ALTER TABLE `incidencias` DISABLE KEYS */;
INSERT INTO `incidencias` VALUES (1,3,9,'Accidente en escaleras del pabellón Z ','Debido a las lluvias una compañera se resbaló subiendo las escaleras',2,'Accidente y otros',NULL,NULL,0,'2022-10-14 08:50:00',1,'moderado','Registrado',NULL),(2,4,9,'TI perdido en digimundo','Encontré un TI con código 20191476 en digimundo, lo dejaré secretaría del V',1,'Objetos perdidos',NULL,NULL,1,'2022-10-14 10:40:00',0,'moderado','Registrado',NULL),(3,5,12,'Expendedora de café del pabellón V se traga las monedas','Cuidado con la expendedora, no está funcionando bien',3,'Infraestructura en mal estado',NULL,NULL,0,'2022-10-14 18:06:00',1,'critico','Registrado',NULL),(4,6,2,'Microondas en comedor de letras sin funcionar','Tomen sus precauciones si van a usar el microondas del comedor de letras',4,'Infraestructura en mal estado',NULL,NULL,0,'2022-10-15 09:25:00',0,'moderado','Registrado',NULL),(5,7,2,'No hay agua en los baños de EE.GG.CC','No hay agua en los baños de EE.GG.CC',5,'Infraestructura en mal estado',NULL,NULL,0,'2022-10-15 16:20:00',1,'leve','Registrado',NULL),(6,8,19,'No hay electricidad en el A-501 ','No hay electricidad',6,'Infraestructura en mal estado',NULL,NULL,0,'2022-10-15 17:19:00',1,'leve','Registrado',NULL),(7,10,24,'D.N.I encontrado en terrazas de EE.GG.CC','Encontré este DNI frente a sala de eventos EE.GG.CC, lo dejaré con el sheriff',7,'Objetos perdidos',NULL,NULL,0,'2022-10-15 20:12:00',0,'leve','Registrado',NULL),(8,11,30,'Ascensores de CIA malogrados','Los ascensores están sin funcionar desde hace 15 min ',8,'Infraestructura en mal estado',NULL,NULL,0,'2022-10-17 11:15:00',0,'moderado','Registrado',NULL),(9,13,24,'Auto chocado en estacionamiento de CEPREPUCP ','Se ha presenciado un auto chocado cerca al estacionamiento de CeprePUCP ',1,'Accidente y otros',NULL,NULL,0,'2022-10-17 13:12:00',0,'moderado','Registrado',NULL),(10,14,21,'Celular encontrado en baño de mujeres-pabellón A','El modelo encontrado es un iphone13, lo dejaré con el sheriff.',2,'Objetos perdidos',NULL,NULL,0,'2022-10-18 09:59:00',1,'critico','Registrado',NULL),(11,15,12,'Mesas del comedor central en mal estado','Las mesas del 2do piso del comedor central están completamente mojadas ',3,'Infraestructura en mal estado',NULL,NULL,1,'2022-10-19 11:45:00',1,'leve','Registrado',NULL),(12,16,30,'Incidente de robo en las afueras de la puerta de EE.GG.CC','Compañeros, tengan cuidado si salen por la puerta de EE.GG.CC',4,'Reporte de robos',NULL,NULL,1,'2022-10-19 13:25:00',0,'leve','Registrado',NULL),(13,17,2,'Accidente en cancha de minas','Una compañera se ha lastimado la pierna jugando futbol',5,'Ambulancia PUCO',NULL,NULL,0,'2022-10-19 15:23:00',0,'critico','Registrado',NULL),(14,18,9,'Juan Valdez no atenderá el día de hoy 20/10','Adjunto foto del anuncio de la cafetería',6,'Accidentes y otros',NULL,NULL,1,'2022-10-20 08:48:00',1,'moderado','Registrado',NULL),(15,20,28,'Calculadora Texas encontrada en el A-307','Se ha encontrado una calculadora Texas, voy a entregarle al sheriff.',7,'Objetos perdidos',NULL,NULL,0,'2022-10-20 14:04:00',0,'moderado','Registrado',NULL),(16,22,24,'Ventana rota en gimnasio PUCP ','Debido a un accidente se ha roto una ventana en el gimnasio',8,'Infraestructura en mal estado',NULL,NULL,0,'2022-10-20 15:28:00',1,'leve','Registrado',NULL),(17,23,30,'Pérdida de carnet universitario en comedor de FARES','Perdí mi carnet universitario en el comedor de fares, si lo encuentran dejenlas con el sherif. Gracias ',9,'Objetos perdidos',NULL,NULL,1,'2022-10-21 19:12:00',1,'moderado','Registrado',NULL),(18,25,21,'robo de laptop en exteriores del polideportivo ','Me sacaron la laptop de la mochila mientras estaba jugando en las canchas!!',1,'Reporte de robos',NULL,NULL,0,'2022-10-22 18:20:00',0,'critico','Registrado',NULL),(19,26,28,'Baños del Polideportivo clausurados','Si piensan usar los baños del Polideportivo tomen sus precauciones',2,'Accidentes y otros',NULL,NULL,1,'2022-10-22 21:15:00',0,'leve','Registrado',NULL),(20,27,24,'Urinarios en mal estado - CIA 3er Piso','Los urinarios del 3er Piso de Cia están malogrados',3,'Infraestructura en mal estado',NULL,NULL,0,'2022-10-24 10:30:00',0,'leve','Registrado',NULL),(21,3,NULL,'me robaron','pipipi',2,'Reporte de robos',NULL,NULL,1,'2022-10-04 10:40:00',0,'critico','No registrado',NULL),(22,3,NULL,'uwu','pipipi',2,'Accidente y otros',NULL,NULL,1,'2022-10-21 18:30:00',0,'leve','No registrado',NULL);
/*!40000 ALTER TABLE `incidencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidencias_destacadas`
--

DROP TABLE IF EXISTS `incidencias_destacadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidencias_destacadas` (
  `idUsuario` int NOT NULL,
  `idIncidencia` int NOT NULL,
  PRIMARY KEY (`idUsuario`,`idIncidencia`),
  KEY `fk_usuarios_has_incidencias_incidencias1_idx` (`idIncidencia`),
  KEY `fk_usuarios_has_incidencias_usuarios1_idx` (`idUsuario`),
  CONSTRAINT `fk_usuarios_has_incidencias_incidencias1` FOREIGN KEY (`idIncidencia`) REFERENCES `incidencias` (`idIncidencia`),
  CONSTRAINT `fk_usuarios_has_incidencias_usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidencias_destacadas`
--

LOCK TABLES `incidencias_destacadas` WRITE;
/*!40000 ALTER TABLE `incidencias_destacadas` DISABLE KEYS */;
/*!40000 ALTER TABLE `incidencias_destacadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `celular` varchar(15) NOT NULL,
  `codigoPucp` varchar(10) NOT NULL,
  `correoPucp` varchar(50) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `contrasena` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Luis Enrique','Gonzales Polanco','35465689','956540054','20196431','a20196431@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(2,'Carolina','Dominguez Quispe','84569162','956784125','20209874','caro.dominguez@pucp.edu.pe','Alumno','Seguridad',NULL),(3,'David','Garcia Muñoz','78795420','987121906','20206498','d.garcia@pucp.edu.pe','Profesor','Usuario PUCP',NULL),(4,'Brenda ','Valverde Torres','64234657','971543311','20193487','a20193487@pucp.edu.pe','Administrativo','Usuario PUCP',NULL),(5,'Jose Antonio','Manrique Garcia','84506597','963174588','20196732','a20196732@pucp.edu.pe','Alumno','Usuario PUCP','usuario'),(6,'Alexandra ','Messi Nazario','54983165','915453287','20169782','ale.messi@pucp.edu.pe','Jefe de practica','Usuario PUCP',NULL),(7,'Angela','Fernandez Vargas','65489874','912781154','20193478','a.fernandez@pucp.edu.pe','Jefe de practica','Usuario PUCP',NULL),(8,'Jorge','De la Cruz Silva','65148595','900641348','20193784','a20193784@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(9,'Lucia Mirella','Martinez Rivera','84659752','956214782','20179463','lu.marivera@pucp.edu.pe','Alumno','Seguridad','seguridad'),(10,'Antony','Gomez Perez','37895462','978121224','20197451','a20197451@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(11,'Max Anderson','Medina Saravia','21034568','931264511','20163498','a20163498@pucp.edu.pe','Profesor','Usuario PUCP',NULL),(12,'Xiomara','Rivera Muñoz','97421354','965112247','20189743','xiomara.rivera@pucp.edu.pe','Egresado','Seguridad',NULL),(13,'Allison','Paredes Morales','16448746','935412145','20197862','aparedes@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(14,'Brenda Alejandra','Aguilar Flores','94612451','978121451','20193476','a20193476@pucp.edu.pe','Egresado','Usuario PUCP',NULL),(15,'Jose Manuel','Vasquez Torres','84651224','934556415','20209403','jvazquez@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(16,'Luis Angel','Diaz Perez','94613151','916455874','20193449','a20193449@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(17,'Antonio','Mendoza Saravia','63784221','968412131','20193479','a20193479@pucp.edu.pe','Profesor','Usuario PUCP',NULL),(18,'Bertha Lucia','Manrique Romero','21357584','935478151','20203197','bertha.mr@pucp.edu.pe','Jefe de practica','Usuario PUCP',NULL),(19,'Mathias','Campos Huamani','34589622','978421445','20200034','mathias.camos@pucp.edu.pe','Alumno','Seguridad',NULL),(20,'Rodrigo Antonio','Palomino Diaz','54512445','924484127','20183194','a20183194@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(21,'Juan Luis','Soto Quispe','97512213','941248454','20203100','jsoto@pucp.edu.pe','Alumno','Seguridad',NULL),(22,'Magaly','Sandoval Ortiz','34579811','994154516','20183119','a20183119@pucp.edu.pe','Profesor','Usuario PUCP',NULL),(23,'Freddy Enrique','Villanueva Dominguez','86513154','964544125','20203194','a20203194@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(24,'Maria Alejandra','Salas Palacios','43615749','936477599','20197431','a20197431@pucp.edu.pe','Egresado','Seguridad',NULL),(25,'Diego Alonso','Palacios Gomez','54619715','935941215','20156497','dpalacios@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(26,'Alexandra ','Bustamante Rios','67941124','900815054','20206461','a20206461@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(27,'Miriam ','Coronado Martinez','28413154','972116410','20191616','a20191616@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(28,'Rosmery Kyara','Valdez Rivera','49761313','945164215','20183265','a20183265@pucp.edu.pe','Profesor','Seguridad',NULL),(29,'Lionel Andres','Miranda Montenegro','44514662','971311411','20193184','lio.mirand@pucp.edu.pe','Alumno','Usuario PUCP',NULL),(30,'Catalina Trisia','Manrique Valverde','57613314','934615154','20169449','a2169449@pucp.edu.pe','Jefe de practica','Seguridad',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zonapucp`
--

DROP TABLE IF EXISTS `zonapucp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zonapucp` (
  `idzonaPucp` int NOT NULL AUTO_INCREMENT,
  `nombreZona` varchar(100) NOT NULL,
  PRIMARY KEY (`idzonaPucp`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zonapucp`
--

LOCK TABLES `zonapucp` WRITE;
/*!40000 ALTER TABLE `zonapucp` DISABLE KEYS */;
INSERT INTO `zonapucp` VALUES (1,'338 Café Gourmet (PUCP)'),(2,'Alianza Francesa'),(3,'Anfiteatro Armando Zolezzi'),(4,'Anfiteatro José Dammert Bellido - Facultad de Derecho'),(5,'Asociación de egresados y graduados'),(6,'Auditorio de Ciencias Sociales (Gustavo Gutiérrez Merino)'),(7,'Auditorio de Derecho'),(8,'Auditorio de Física'),(9,'Auditorio de Humanidades'),(10,'Auditorio de la Biblioteca Central (Tercer piso)'),(11,'Auditorio de la Facultad de Arte y Diseño'),(12,'Auditorio de la Facultad de Ciencias e Ingeniería'),(13,'Auditorio de la Facultad de Educación'),(14,'Auditorio Juan Pablo II'),(15,'Aula H411 informática (DTI) - Aula informática'),(16,'Aula H412 informática (DTI) - Aula informática'),(17,'Aula H413 informática (DTI) - Aula informática'),(18,'Aula L103 Auditorio N° 1 de Estudios Generales Letras'),(19,'Aula L___ informática (DTI) en Estudios Generales Letras - Aula informática'),(20,'Aula N221 informática (DTI) - Aula informática');
/*!40000 ALTER TABLE `zonapucp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-10 15:44:32
