-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mercadeoucab
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Cuidado Perso','Productos para la higiene y cuidado personal','Inactivo'),(2,'Cosmetic','Productos de belleza','Activo'),(3,'Limpieza del hogar','Productos para facilitar la limpieza e higiene de los hogares, las oficinas y otros ambientes','Activo');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudio`
--

DROP TABLE IF EXISTS `estudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tipoInstrumento` varchar(60) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date DEFAULT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_solicitud_estudio` int DEFAULT NULL,
  `fk_usuario` int DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_estudio_solicitud_estudio1_idx` (`fk_solicitud_estudio`),
  KEY `fk_estudio_usuario1_idx` (`fk_usuario`),
  CONSTRAINT `fk_estudio_solicitud_estudio1` FOREIGN KEY (`fk_solicitud_estudio`) REFERENCES `solicitud_estudio` (`id`),
  CONSTRAINT `fk_estudio_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudio`
--

LOCK TABLES `estudio` WRITE;
/*!40000 ALTER TABLE `estudio` DISABLE KEYS */;
INSERT INTO `estudio` VALUES (141,'Estudio sobre nuevo jabón Dove','Encuesta','2021-02-14',NULL,'Activo',34,21,'En espera',NULL);
/*!40000 ALTER TABLE `estudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hijo`
--

DROP TABLE IF EXISTS `hijo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hijo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fechaNacimiento` date NOT NULL,
  `genero` varchar(50) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_informacion` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hijo_informacion1_idx` (`fk_informacion`),
  CONSTRAINT `fk_hijo_informacion1` FOREIGN KEY (`fk_informacion`) REFERENCES `informacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hijo`
--

LOCK TABLES `hijo` WRITE;
/*!40000 ALTER TABLE `hijo` DISABLE KEYS */;
INSERT INTO `hijo` VALUES (1,'2010-12-01','masculino','Activo',6),(2,'2016-04-03','Femenino','Activo',9),(3,'2015-08-21','Masculino','Activo',6),(5,'2016-08-28','Femenino','Activo',7),(6,'2014-08-12','Masculino','Activo',11),(7,'2014-06-05','Masculino','Activo',10),(8,'2018-07-30','Masculino','Activo',8),(9,'2010-05-13','Masculino','Activo',10),(10,'2011-06-01','Masculino','Activo',7),(11,'2008-08-28','Femenino','Activo',5),(12,'2007-08-09','Femenino','Activo',14),(13,'2015-06-19','Masculino','Activo',15),(14,'2018-12-29','Masculino','Activo',8),(15,'2009-10-02','Masculino','Activo',13),(16,'2016-08-22','Femenino','Activo',9),(17,'2020-06-06','Femenino','Activo',7),(18,'2007-12-29','Femenino','Activo',3),(19,'2019-09-28','Femenino','Activo',7),(20,'2009-04-11','Femenino','Activo',13),(21,'2008-01-06','Femenino','Activo',3),(22,'2019-11-05','Masculino','Activo',6),(23,'2020-01-16','Femenino','Activo',8),(24,'2009-04-25','Femenino','Activo',10),(25,'2020-03-16','Femenino','Activo',12),(26,'2017-10-05','Masculino','Activo',6),(27,'2019-06-22','Femenino','Activo',12),(28,'2017-08-28','Masculino','Activo',6),(29,'2020-06-16','Masculino','Activo',10),(30,'2019-09-11','Femenino','Activo',13),(31,'2013-10-01','Masculino','Activo',8),(32,'2016-10-30','Femenino','Activo',7),(33,'2011-03-15','Femenino','Activo',6),(34,'2014-02-15','Masculino','Activo',10),(35,'2016-04-21','Femenino','Activo',13),(36,'2009-04-02','Masculino','Activo',9),(37,'2009-02-16','Masculino','Activo',3),(38,'2016-04-29','Masculino','Activo',6),(39,'2007-04-05','Masculino','Activo',15),(40,'2006-06-06','Masculino','Activo',11),(41,'2011-12-23','Femenino','Activo',1),(42,'2006-11-24','Femenino','Activo',11),(43,'2012-06-07','Masculino','Activo',1),(44,'2015-06-19','Masculino','Activo',4),(45,'2020-10-09','Masculino','Activo',4),(46,'2010-11-16','masculino','Activo',1),(47,'2013-11-16','masculino','Activo',1),(48,'2015-10-15','femenino','Activo',1);
/*!40000 ALTER TABLE `hijo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico_estado`
--

DROP TABLE IF EXISTS `historico_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historico_estado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fechaInicio` date NOT NULL,
  `fechaFin` date DEFAULT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_usuario` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_historico_estado_usuario1_idx` (`fk_usuario`),
  CONSTRAINT `fk_historico_estado_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico_estado`
--

LOCK TABLES `historico_estado` WRITE;
/*!40000 ALTER TABLE `historico_estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `historico_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion`
--

DROP TABLE IF EXISTS `informacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cedula` int NOT NULL,
  `primerNombre` varchar(100) NOT NULL,
  `segundoNombre` varchar(100) DEFAULT NULL,
  `primerApellido` varchar(100) NOT NULL,
  `segundoApellido` varchar(100) NOT NULL,
  `genero` varchar(80) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `estadoCivil` varchar(80) NOT NULL,
  `disponibilidadEnLinea` varchar(50) NOT NULL,
  `cantidadPersonas` int NOT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_lugar` int NOT NULL,
  `fk_nivelEconomico` int NOT NULL,
  `fk_nivel_academico` int NOT NULL,
  `fk_ocupacion` int NOT NULL,
  `fk_usuario` int NOT NULL,
  PRIMARY KEY (`id`,`fk_usuario`),
  KEY `fk_informacion_lugar1_idx` (`fk_lugar`),
  KEY `fk_informacion_nivel_economico1_idx` (`fk_nivelEconomico`),
  KEY `fk_informacion_nivel_academico1_idx` (`fk_nivel_academico`),
  KEY `fk_informacion_ocupacion1_idx` (`fk_ocupacion`),
  KEY `fk_informacion_usuario1_idx` (`fk_usuario`),
  CONSTRAINT `fk_informacion_lugar1` FOREIGN KEY (`fk_lugar`) REFERENCES `lugar` (`id`),
  CONSTRAINT `fk_informacion_nivel_academico1` FOREIGN KEY (`fk_nivel_academico`) REFERENCES `nivel_academico` (`id`),
  CONSTRAINT `fk_informacion_nivel_economico1` FOREIGN KEY (`fk_nivelEconomico`) REFERENCES `nivel_economico` (`id`),
  CONSTRAINT `fk_informacion_ocupacion1` FOREIGN KEY (`fk_ocupacion`) REFERENCES `ocupacion` (`id`),
  CONSTRAINT `fk_informacion_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion`
--

LOCK TABLES `informacion` WRITE;
/*!40000 ALTER TABLE `informacion` DISABLE KEYS */;
INSERT INTO `informacion` VALUES (1,10524058,'Lawton','Byram','Loukes','Lorey','Masculino','1992-03-27','divorciado','no',3,'Activo',68,2,2,21,1),(2,96812304,'Dirk','Flemming','Chill','Blundan','Masculino','1999-10-09','soltero','no',2,'Activo',248,1,4,16,2),(3,20027400,'Nevins','Thurston','Van Geffen','Chat','Masculino','1981-04-02','viudo','Si',6,'Inactivo',34,1,4,43,3),(4,20913187,'Parnell','Eskr','Guice','Scoffins','Masculino','1993-04-13','soltero','sí',5,'Activo',286,3,3,42,4),(5,40507855,'Sarah','Josefa','Perez','Perez','Femenino','1988-09-22','Casado','No',4,'Activo',68,1,3,25,5),(6,31438924,'Kevina','Raine','Bierman','Cyphus','Femenino','1996-09-23','soltero','sí',5,'Activo',49,3,2,35,6),(7,9724744,'Tremayne','Cart','Rawsthorn','Clewer','Masculino','1981-02-24','viudo','no',5,'Inactivo',183,4,5,34,7),(8,91822027,'María','Daniela','Lopez','Rodríguez','Femenino','1992-06-12','Casado','No',4,'Activo',154,2,5,6,8),(9,34795304,'Sidonia','Carmencita','Giurio','Cassley','Femenino','1990-02-04','casado','sí',6,'Activo',27,1,3,33,9),(10,50807440,'Silvan','Nev','Drinan','Caras','Masculino','1981-08-14','viudo','no',3,'Activo',241,3,1,13,10),(11,6325778,'Paxton','Ulick','Swindall','D\'Adda','Masculino','1986-10-05','divorciado','sí',4,'Activo',33,4,4,26,11),(12,31819827,'Spenser','Neale','Robardey','Delacourt','Masculino','1985-09-26','soltero','sí',2,'Inactivo',268,5,5,17,12),(13,77869858,'Konstantin','Rowney','Downing','Puxley','Masculino','1996-01-02','casado','sí',3,'Activo',211,4,3,39,13),(14,85467396,'Vyky','Lynn','Twiddy','Spofforth','Femenino','1981-04-30','divorciado','sí',5,'Inactivo',225,4,5,9,14),(15,29166047,'Blinny','Harli','Hoodlass','Holbury','Femenino','1997-07-26','viudo','no',6,'Inactivo',216,1,4,5,15),(16,12345678,'testEnc','superTest','apellido','otroapellido','Masculino','1998-07-07','Soltero','Si',3,'Activo',216,1,4,5,18),(17,23966123,'Carlos','Daniel','Ortiz','Verhelst','Masculino','1995-08-07','Soltero','Si',3,'Activo',216,1,4,5,24);
/*!40000 ALTER TABLE `informacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugar`
--

DROP TABLE IF EXISTS `lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `categoriaSocioEconomica` varchar(80) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_lugar` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lugar_lugar_idx` (`fk_lugar`),
  CONSTRAINT `fk_lugar_lugar` FOREIGN KEY (`fk_lugar`) REFERENCES `lugar` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=360 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugar`
--

LOCK TABLES `lugar` WRITE;
/*!40000 ALTER TABLE `lugar` DISABLE KEYS */;
INSERT INTO `lugar` VALUES (1,'Amazonas','Estado','Alta','Activo',359),(2,'Anzoátegui','Estado','Media baja','Activo',359),(3,'Apure','Estado','Media alta','Activo',359),(4,'Aragua','Estado','Media','Activo',359),(5,'Barinas','Estado','Media baja','Activo',359),(6,'Bolívar','Estado','Baja','Activo',359),(7,'Carabobo','Estado','Baja','Activo',359),(8,'Cojedes','Estado','Baja','Activo',359),(9,'Delta Amacuro','Estado','Media','Activo',359),(10,'Falcón','Estado','Media alta','Activo',359),(11,'Guárico','Estado','Baja','Activo',359),(12,'Lara','Estado','Media baja','Activo',359),(13,'Mérida','Estado','Baja','Activo',359),(14,'Miranda','Estado','Media baja','Activo',359),(15,'Monagas','Estado','Alta','Activo',359),(16,'Nueva Esparta','Estado','Baja','Activo',359),(17,'Portuguesa','Estado','Media alta','Activo',359),(18,'Sucre','Estado','Alta','Activo',359),(19,'Táchira','Estado','Media alta','Activo',359),(20,'Trujillo','Estado','Baja','Activo',359),(21,'Vargas','Estado','Alta','Activo',359),(22,'Yaracuy','Estado','Baja','Activo',359),(23,'Zulia','Estado','Media alta','Activo',359),(24,'Distrito Capital','Estado','Media baja','Activo',359),(25,'Alto Orinoco','Municipio','Media alta','Activo',1),(26,'Atabapo','Municipio','Media','Activo',1),(27,'Atures','Municipio','Media','Activo',1),(28,'Autana','Municipio','Media baja','Activo',1),(29,'Manapiare','Municipio','Media','Activo',1),(30,'Maroa','Municipio','Media baja','Activo',1),(31,'Río negro','Municipio','Media','Activo',1),(32,'Anaco','Municipio','Alta','Activo',2),(33,'Aragua','Municipio','Media','Activo',2),(34,'Diego Bautista Urbaneja','Municipio','Media','Activo',2),(35,'Fernando Peñalver','Municipio','Media baja','Activo',2),(36,'Francisco del Carmen Carvajal','Municipio','Baja','Activo',2),(37,'Francisco de Miranda','Municipio','Alta','Activo',2),(38,'Guanta','Municipio','Baja','Activo',2),(39,'Independencia','Municipio','Media baja','Activo',2),(40,'José GregoRío Monagas','Municipio','Baja','Activo',2),(41,'Juan Antonio Solito','Municipio','Media alta','Activo',2),(42,'Manuel Cajigal','Municipio','Media','Activo',2),(43,'Libertad','Municipio','Media baja','Activo',2),(44,'Manuel Ezequiel Bruzual','Municipio','Media','Activo',2),(45,'Pedro María Freites','Municipio','Media baja','Activo',2),(46,'Píritu','Municipio','Media','Activo',2),(47,'San José de Guanipa','Municipio','Media alta','Activo',2),(48,'San Juan de Capistrano','Municipio','Media','Activo',2),(49,'Santa Ana','Municipio','Media','Activo',2),(50,'Simón Bolívar','Municipio','Media baja','Activo',2),(51,'Simón Rodríguez','Municipio','Media alta','Activo',2),(52,'General Sir Arthur McGregor','Municipio','Alta','Activo',2),(53,'Achaguas','Municipio','Media baja','Activo',3),(54,'Biruaca','Municipio','Alta','Activo',3),(55,'Muñoz','Municipio','Alta','Activo',3),(56,'Pedro Camejo','Municipio','Media baja','Activo',3),(57,'San Fernando','Municipio','Media alta','Activo',3),(58,'Páez','Municipio','Baja','Activo',3),(59,'Gallegos','Municipio','Media','Activo',3),(60,'Bolívar','Municipio','Baja','Activo',4),(61,'Camatagua','Municipio','Media baja','Activo',4),(62,'Francisco Linares Alcántara','Municipio','Media alta','Activo',4),(63,'Girardot','Municipio','Baja','Activo',4),(64,'José Ángel Lamas','Municipio','Baja','Activo',4),(65,'José Félix Ribas','Municipio','Media','Activo',4),(66,'José Rafael Revenga','Municipio','Alta','Activo',4),(67,'Libertador','Municipio','Baja','Activo',4),(68,'MaRío Briceño Iragorry','Municipio','Media baja','Activo',4),(69,'Ocumare de la Costa de Oro','Municipio','Baja','Activo',4),(70,'San Casimiro','Municipio','Alta','Activo',4),(71,'San Sebastián','Municipio','Alta','Activo',4),(72,'Santiago Mariño','Municipio','Alta','Activo',4),(73,'Santos Michelena','Municipio','Media','Activo',4),(74,'Sucre','Municipio','Media baja','Activo',4),(75,'Tovar','Municipio','Media baja','Activo',4),(76,'Urdaneta','Municipio','Baja','Activo',4),(77,'Zamora','Municipio','Alta','Activo',4),(78,'Alberto Arvelo Torrealba','Municipio','Media alta','Activo',5),(79,'Andrés Eloy Blanco','Municipio','Baja','Activo',5),(80,'Antonio José de Sucre','Municipio','Alta','Activo',5),(81,'Arismendi','Municipio','Alta','Activo',5),(82,'Barinas','Municipio','Alta','Activo',5),(83,'Bolívar','Municipio','Media alta','Activo',5),(84,'Cruz Paredes','Municipio','Baja','Activo',5),(85,'Ezequiel Zamora','Municipio','Media baja','Activo',5),(86,'Obispos','Municipio','Media alta','Activo',5),(87,'Pedraza','Municipio','Alta','Activo',5),(88,'Rojas','Municipio','Media','Activo',5),(89,'Sosa','Municipio','Media','Activo',5),(90,'Caroní','Municipio','Media','Activo',6),(91,'Cedeño','Municipio','Alta','Activo',6),(92,'El Callao','Municipio','Baja','Activo',6),(93,'Gran Sabana','Municipio','Media baja','Activo',6),(94,'Heres','Municipio','Media alta','Activo',6),(95,'Padre Pedro Chien','Municipio','Baja','Activo',6),(96,'Piar','Municipio','Baja','Activo',6),(97,'Angostura','Municipio','Media','Activo',6),(98,'Roscio','Municipio','Alta','Activo',6),(99,'Sifontes','Municipio','Media alta','Activo',6),(100,'Sucre','Municipio','Media alta','Activo',6),(101,'Bejuma','Municipio','Baja','Activo',7),(102,'Carlos Arvelo','Municipio','Media baja','Activo',7),(103,'Diego Ibarra','Municipio','Baja','Activo',7),(104,'Guacara','Municipio','Baja','Activo',7),(105,'Juan José Mora','Municipio','Media','Activo',7),(106,'Libertador','Municipio','Media alta','Activo',7),(107,'Los Guayos','Municipio','Media alta','Activo',7),(108,'Miranda','Municipio','Media','Activo',7),(109,'Montalbán','Municipio','Media baja','Activo',7),(110,'Naguanagua','Municipio','Alta','Activo',7),(111,'Puerto Cabello','Municipio','Media baja','Activo',7),(112,'San Diego','Municipio','Baja','Activo',7),(113,'San Joaquín','Municipio','Alta','Activo',7),(114,'Valencia','Municipio','Baja','Activo',7),(115,'Anzoátegui','Municipio','Alta','Activo',8),(116,'Pao de San Juan Bautista','Municipio','Media alta','Activo',8),(117,'Falcón','Municipio','Media alta','Activo',8),(118,'Girardot','Municipio','Alta','Activo',8),(119,'Lima Blanco','Municipio','Baja','Activo',8),(120,'Ricaurte','Municipio','Media alta','Activo',8),(121,'Rómulo Gallegos','Municipio','Media alta','Activo',8),(122,'Ezequiel Zamora','Municipio','Baja','Activo',8),(123,'Tinaco','Municipio','Media baja','Activo',8),(124,'Antonio Díaz','Municipio','Media baja','Activo',9),(125,'Casacoima','Municipio','Baja','Activo',9),(126,'Pedernales','Municipio','Alta','Activo',9),(127,'Tucupita','Municipio','Media','Activo',9),(128,'Libertador de Caracas','Municipio','Baja','Activo',24),(129,'Acosta','Municipio','Media alta','Activo',10),(130,'Bolívar','Municipio','Media baja','Activo',10),(131,'Buchivacoa','Municipio','Media baja','Activo',10),(132,'Cacique Manaure','Municipio','Media alta','Activo',10),(133,'Carirubana','Municipio','Media alta','Activo',10),(134,'Colina','Municipio','Alta','Activo',10),(135,'Dabajuro','Municipio','Media','Activo',10),(136,'Democracia','Municipio','Alta','Activo',10),(137,'Falcón','Municipio','Media','Activo',10),(138,'Federación','Municipio','Media alta','Activo',10),(139,'Jacura','Municipio','Media alta','Activo',10),(140,'Los Taques','Municipio','Media baja','Activo',10),(141,'Mauroa','Municipio','Media baja','Activo',10),(142,'Miranda','Municipio','Alta','Activo',10),(143,'Monseñor Iturriza','Municipio','Media alta','Activo',10),(144,'Palmasola','Municipio','Media','Activo',10),(145,'Petit','Municipio','Media alta','Activo',10),(146,'Píritu','Municipio','Media baja','Activo',10),(147,'San Francisco','Municipio','Alta','Activo',10),(148,'José Laurencio Silva','Municipio','Media baja','Activo',10),(149,'Sucre','Municipio','Alta','Activo',10),(150,'Tocopero','Municipio','Media baja','Activo',10),(151,'Unión','Municipio','Baja','Activo',10),(152,'Urumaco','Municipio','Media alta','Activo',10),(153,'Zamora','Municipio','Alta','Activo',10),(154,'Camaguán','Municipio','Alta','Activo',11),(155,'Chaguaramas','Municipio','Alta','Activo',11),(156,'El Socorro','Municipio','Media alta','Activo',11),(157,'Francisco de Miranda','Municipio','Baja','Activo',11),(158,'José Félix Ribas','Municipio','Media alta','Activo',11),(159,'José Tadeo Monagas','Municipio','Media baja','Activo',11),(160,'Juan Germán Roscio','Municipio','Media alta','Activo',11),(161,'Julián Mellado','Municipio','Media baja','Activo',11),(162,'Las Mercedes','Municipio','Media','Activo',11),(163,'Leonardo Infante','Municipio','Media','Activo',11),(164,'Ortiz','Municipio','Media','Activo',11),(165,'Pedro Zaraza','Municipio','Media alta','Activo',11),(166,'San Gerónimo de Guayabal','Municipio','Media','Activo',11),(167,'San José de Guaribe','Municipio','Baja','Activo',11),(168,'Santa María de Ipire','Municipio','Baja','Activo',11),(169,'Andrés Eloy Blanco','Municipio','Media baja','Activo',12),(170,'Crespo','Municipio','Media baja','Activo',12),(171,'Morán','Municipio','Media','Activo',12),(172,'Palavecino','Municipio','Baja','Activo',12),(173,'Simón Planas','Municipio','Media','Activo',12),(174,'Torres','Municipio','Media baja','Activo',12),(175,'Urdaneta','Municipio','Alta','Activo',12),(176,'Iribarren','Municipio','Alta','Activo',12),(177,'Jiménez','Municipio','Media alta','Activo',12),(178,'Alberto Adriani','Municipio','Alta','Activo',13),(179,'Antonio Pinto Salinas','Municipio','Media alta','Activo',13),(180,'Andrés Bello','Municipio','Baja','Activo',13),(181,'Acarigua','Municipio','Baja','Activo',13),(182,'Arzobispo Chacón','Municipio','Baja','Activo',13),(183,'Campo Elías','Municipio','Baja','Activo',13),(184,'Caracciolo Parra Olmedo','Municipio','Alta','Activo',13),(185,'Cardenal Quintero','Municipio','Media baja','Activo',13),(186,'Guaraque','Municipio','Media baja','Activo',13),(187,'Julio César Salas','Municipio','Media alta','Activo',13),(188,'Justo Briceño','Municipio','Baja','Activo',13),(189,'Libertador','Municipio','Media','Activo',13),(190,'Miranda','Municipio','Baja','Activo',13),(191,'Obispo Ramos de Lora','Municipio','Media','Activo',13),(192,'Padre Noguera','Municipio','Media baja','Activo',13),(193,'Pueblo Llano','Municipio','Media','Activo',13),(194,'Rangel','Municipio','Media','Activo',13),(195,'Rivas Dávila','Municipio','Alta','Activo',13),(196,'Santos Marquina','Municipio','Baja','Activo',13),(197,'Sucre','Municipio','Alta','Activo',13),(198,'Tovar','Municipio','Media alta','Activo',13),(199,'Tulio Febres Cordero','Municipio','Media baja','Activo',13),(200,'Zea','Municipio','Alta','Activo',13),(201,'Acevedo','Municipio','Baja','Activo',14),(202,'Andrés Bello','Municipio','Media baja','Activo',14),(203,'Baruta','Municipio','Media alta','Activo',14),(204,'Brión','Municipio','Alta','Activo',14),(205,'Buroz','Municipio','Media baja','Activo',14),(206,'Carrizal','Municipio','Alta','Activo',14),(207,'Chacao','Municipio','Alta','Activo',14),(208,'Cristóbal Rojas','Municipio','Media alta','Activo',14),(209,'Guaicaipuro','Municipio','Media baja','Activo',14),(210,'Independencia','Municipio','Baja','Activo',14),(211,'Lander','Municipio','Baja','Activo',14),(212,'Los Salias','Municipio','Alta','Activo',14),(213,'Páez','Municipio','Media','Activo',14),(214,'Paz Castillo','Municipio','Media baja','Activo',14),(215,'Pedro Gual','Municipio','Media alta','Activo',14),(216,'Plaza','Municipio','Media baja','Activo',14),(217,'Simón Bolívar','Municipio','Alta','Activo',14),(218,'Urdaneta','Municipio','Baja','Activo',14),(219,'Sucre','Municipio','Media alta','Activo',14),(220,'Zamora','Municipio','Baja','Activo',14),(221,'El Hatillo','Municipio','Media alta','Activo',14),(222,'Acosta','Municipio','Media','Activo',15),(223,'Aguasay','Municipio','Baja','Activo',15),(224,'Bolívar','Municipio','Media baja','Activo',15),(225,'Caripe','Municipio','Alta','Activo',15),(226,'Cedeño','Municipio','Alta','Activo',15),(227,'Ezequiel Zamora','Municipio','Media alta','Activo',15),(228,'Libertador','Municipio','Media baja','Activo',15),(229,'Maturín','Municipio','Media baja','Activo',15),(230,'Piar','Municipio','Baja','Activo',15),(231,'Punceres','Municipio','Baja','Activo',15),(232,'Santa Bárbara','Municipio','Alta','Activo',15),(233,'Sotillo','Municipio','Media','Activo',15),(234,'Uracoa','Municipio','Media baja','Activo',15),(235,'Antolín del Campo','Municipio','Baja','Activo',16),(236,'Arismendi','Municipio','Baja','Activo',16),(237,'Antonio Díaz','Municipio','Media baja','Activo',16),(238,'García','Municipio','Media','Activo',16),(239,'Gómez','Municipio','Alta','Activo',16),(240,'Maneiro','Municipio','Baja','Activo',16),(241,'Marcano','Municipio','Media alta','Activo',16),(242,'Mariño','Municipio','Baja','Activo',16),(243,'Macanao','Municipio','Alta','Activo',16),(244,'Tubores','Municipio','Media alta','Activo',16),(245,'Villalba','Municipio','Media baja','Activo',16),(246,'Araure','Municipio','Media baja','Activo',17),(247,'Esteller','Municipio','Alta','Activo',17),(248,'Guanare','Municipio','Media','Activo',17),(249,'Guanarito','Municipio','Alta','Activo',17),(250,'Monseñor José Vicente de Unda','Municipio','Media','Activo',17),(251,'Ospino','Municipio','Media','Activo',17),(252,'Páez','Municipio','Baja','Activo',17),(253,'Papelón','Municipio','Media baja','Activo',17),(254,'San Genaro de Boconoíto','Municipio','Baja','Activo',17),(255,'San Rafael de Onoto','Municipio','Media alta','Activo',17),(256,'Santa Rosalía','Municipio','Media baja','Activo',17),(257,'Sucre','Municipio','Alta','Activo',17),(258,'Turén','Municipio','Media alta','Activo',17),(259,'Andrés Eloy Blanco','Municipio','Alta','Activo',18),(260,'Andrés Mata','Municipio','Alta','Activo',18),(261,'Arismendi','Municipio','Alta','Activo',18),(262,'Benítez','Municipio','Media alta','Activo',18),(263,'Bermúdez','Municipio','Alta','Activo',18),(264,'Bolívar','Municipio','Media baja','Activo',18),(265,'Cajigal','Municipio','Media','Activo',18),(266,'Cruz Salmerón Acosta','Municipio','Media','Activo',18),(267,'Libertador','Municipio','Alta','Activo',18),(268,'Mariño','Municipio','Media alta','Activo',18),(269,'Mejía','Municipio','Media alta','Activo',18),(270,'Montes','Municipio','Baja','Activo',18),(271,'Ribero','Municipio','Baja','Activo',18),(272,'Sucre','Municipio','Media','Activo',18),(273,'Valdez','Municipio','Media baja','Activo',18),(274,'Andrés Bello','Municipio','Media','Activo',19),(275,'Antonio Rómulo Costa','Municipio','Media baja','Activo',19),(276,'Ayacucho','Municipio','Alta','Activo',19),(277,'Bolívar','Municipio','Media alta','Activo',19),(278,'Cárdenas','Municipio','Media baja','Activo',19),(279,'Córdoba','Municipio','Media baja','Activo',19),(280,'Fernández Feo','Municipio','Alta','Activo',19),(281,'Francisco de Miranda','Municipio','Media baja','Activo',19),(282,'García de Hevia','Municipio','Baja','Activo',19),(283,'Guásimos','Municipio','Baja','Activo',19),(284,'Independencia','Municipio','Baja','Activo',19),(285,'Jáuregui','Municipio','Baja','Activo',19),(286,'José María Vargas','Municipio','Alta','Activo',19),(287,'Junín','Municipio','Media baja','Activo',19),(288,'Libertad','Municipio','Media baja','Activo',19),(289,'Libertador','Municipio','Media baja','Activo',19),(290,'Lobatera','Municipio','Media','Activo',19),(291,'Michelena','Municipio','Baja','Activo',19),(292,'Panamericano','Municipio','Media alta','Activo',19),(293,'Pedro María Ureña','Municipio','Media','Activo',19),(294,'Rafael Urdaneta','Municipio','Media baja','Activo',19),(295,'Samuel Darío Maldonado','Municipio','Baja','Activo',19),(296,'San Cristóbal','Municipio','Media baja','Activo',19),(297,'San Judas Tadeo','Municipio','Media baja','Activo',19),(298,'Seboruco','Municipio','Media baja','Activo',19),(299,'Simón Rodríguez','Municipio','Alta','Activo',19),(300,'Sucre','Municipio','Media','Activo',19),(301,'Torbes','Municipio','Media','Activo',19),(302,'Uribante','Municipio','Baja','Activo',19),(303,'Andrés Bello','Municipio','Media','Activo',20),(304,'Boconó','Municipio','Media alta','Activo',20),(305,'Bolívar','Municipio','Baja','Activo',20),(306,'Candelaria','Municipio','Media alta','Activo',20),(307,'Carache','Municipio','Baja','Activo',20),(308,'Escuque','Municipio','Media baja','Activo',20),(309,'José Felipe Márquez Cañizalez','Municipio','Baja','Activo',20),(310,'Juan Vicente Campos Elías','Municipio','Media alta','Activo',20),(311,'La Ceiba','Municipio','Alta','Activo',20),(312,'Miranda','Municipio','Media','Activo',20),(313,'Monte Carmelo','Municipio','Alta','Activo',20),(314,'Motatán','Municipio','Media baja','Activo',20),(315,'Pampán','Municipio','Media baja','Activo',20),(316,'Pamapanito','Municipio','Media alta','Activo',20),(317,'Rafael Rangel','Municipio','Media baja','Activo',20),(318,'San Rafael de Carvajal','Municipio','Media baja','Activo',20),(319,'Sucre','Municipio','Media','Activo',20),(320,'Trujillo','Municipio','Media alta','Activo',20),(321,'Urdaneta','Municipio','Media baja','Activo',20),(322,'Valera','Municipio','Baja','Activo',20),(323,'Vargas','Municipio','Media alta','Activo',21),(324,'Arístides Bastidas','Municipio','Media','Activo',22),(325,'Bolívar','Municipio','Media','Activo',22),(326,'Bruzual','Municipio','Media','Activo',22),(327,'Cocorote','Municipio','Media alta','Activo',22),(328,'Independencia','Municipio','Media baja','Activo',22),(329,'José Antonio Páez','Municipio','Media','Activo',22),(330,'La Trinidad','Municipio','Media','Activo',22),(331,'Manuel Monge','Municipio','Alta','Activo',22),(332,'Nirgua','Municipio','Media','Activo',22),(333,'Peña','Municipio','Alta','Activo',22),(334,'San Felipe','Municipio','Media alta','Activo',22),(335,'Sucre','Municipio','Alta','Activo',22),(336,'Urachiche','Municipio','Media baja','Activo',22),(337,'José Joaquín Veroes','Municipio','Baja','Activo',22),(338,'Almirante Padilla','Municipio','Media','Activo',23),(339,'Baralt','Municipio','Media baja','Activo',23),(340,'Cabimas','Municipio','Media alta','Activo',23),(341,'Catatumbo','Municipio','Alta','Activo',23),(342,'Colón','Municipio','Baja','Activo',23),(343,'Francisco Javier Pulgar','Municipio','Media','Activo',23),(344,'Jesús Enrique Lossada','Municipio','Media baja','Activo',23),(345,'Jesús María Semprún','Municipio','Media alta','Activo',23),(346,'La Cañada de Urdaneta','Municipio','Media','Activo',23),(347,'Lagunillas','Municipio','Alta','Activo',23),(348,'Machiques de Perijá','Municipio','Media alta','Activo',23),(349,'Mara','Municipio','Baja','Activo',23),(350,'Maracaibo','Municipio','Media baja','Activo',23),(351,'Miranda','Municipio','Baja','Activo',23),(352,'Guajira','Municipio','Alta','Activo',23),(353,'Rosario de Perijá','Municipio','Media','Activo',23),(354,'San Francisco','Municipio','Alta','Activo',23),(355,'Santa Rita','Municipio','Media alta','Activo',23),(356,'Simón Bolívar','Municipio','Baja','Activo',23),(357,'Sucre','Municipio','Media','Activo',23),(358,'Valmore Rodríguez','Municipio','Media','Activo',23),(359,'Venezuela','Pais','Alta','Activo',NULL);
/*!40000 ALTER TABLE `lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'Rexona','Cuidado personal','Activo'),(2,'Dove','Cuidado personal','Activo'),(3,'Colgate Palmolive','Cuidado personal y Limpieza del hogar','Activo'),(4,'MAC','Maquillaje y cosméticos','Activo'),(5,'Chanel','Maquillaje y cosméticos','Activo'),(6,'Dior','Maquillaje y cosméticos','Activo'),(7,'Procter & Gamble','Limpieza del hogar','Activo'),(8,'Unilever','Limpieza del hogar','Activo');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medio_comunicacion`
--

DROP TABLE IF EXISTS `medio_comunicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medio_comunicacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipoDeMedio` varchar(80) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_informacion` int NOT NULL,
  `fk_solicitud_estudio` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_medio_comunicacion_informacion1_idx` (`fk_informacion`),
  KEY `fk_medio_comunicacion_solicitud_estudio1_idx` (`fk_solicitud_estudio`),
  CONSTRAINT `fk_medio_comunicacion_informacion1` FOREIGN KEY (`fk_informacion`) REFERENCES `informacion` (`id`),
  CONSTRAINT `fk_medio_comunicacion_solicitud_estudio1` FOREIGN KEY (`fk_solicitud_estudio`) REFERENCES `solicitud_estudio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medio_comunicacion`
--

LOCK TABLES `medio_comunicacion` WRITE;
/*!40000 ALTER TABLE `medio_comunicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `medio_comunicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel_academico`
--

DROP TABLE IF EXISTS `nivel_academico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nivel_academico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_academico`
--

LOCK TABLES `nivel_academico` WRITE;
/*!40000 ALTER TABLE `nivel_academico` DISABLE KEYS */;
INSERT INTO `nivel_academico` VALUES (1,'Educación Preescolar','Activo'),(2,'Educación Básica','Activo'),(3,'Educación Media Diversificada','Activo'),(4,'Educación Superior','Activo'),(5,'Postgrado','Activo');
/*!40000 ALTER TABLE `nivel_academico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel_economico`
--

DROP TABLE IF EXISTS `nivel_economico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nivel_economico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_economico`
--

LOCK TABLES `nivel_economico` WRITE;
/*!40000 ALTER TABLE `nivel_economico` DISABLE KEYS */;
INSERT INTO `nivel_economico` VALUES (1,'Alta','Activo'),(2,'Media alta','Activo'),(3,'Media','Activo'),(4,'Media baja','Activo'),(5,'Baja','Activo');
/*!40000 ALTER TABLE `nivel_economico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocupacion`
--

DROP TABLE IF EXISTS `ocupacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocupacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocupacion`
--

LOCK TABLES `ocupacion` WRITE;
/*!40000 ALTER TABLE `ocupacion` DISABLE KEYS */;
INSERT INTO `ocupacion` VALUES (1,'Mecánico','Activo'),(2,'Contador','Activo'),(3,'Peluquero','Activo'),(4,'Frutero','Activo'),(5,'Albañil','Activo'),(6,'Agricultor','Activo'),(7,'Cocinero','Activo'),(8,'Carpintero','Activo'),(9,'Soldador','Activo'),(10,'Vendedor','Activo'),(11,'Albañil','Activo'),(12,'Pescador','Activo'),(13,'Panadero','Activo'),(14,'Cocinero','Activo'),(15,'Lavandero','Activo'),(16,'Electricista','Activo'),(17,'Locutor','Activo'),(18,'Vigilante','Activo'),(19,'Biólogo','Activo'),(20,'Artesano','Activo'),(21,'Soldador','Activo'),(22,'Secretario','Activo'),(23,'Vigilante','Activo'),(24,'Ingeniero','Activo'),(25,'Agricultor','Activo'),(26,'Biólogo','Activo'),(27,'Cerrajero','Activo'),(28,'Albañil','Activo'),(29,'Contador','Activo'),(30,'Obrero','Activo'),(31,'Escultor','Activo'),(32,'Soldador','Activo'),(33,'Lechero','Activo'),(34,'Vigilante','Activo'),(35,'Carnicero','Activo'),(36,'Chofer','Activo'),(37,'Biólogo','Activo'),(38,'Barrendero','Activo'),(39,'Lechero','Activo'),(40,'Electricista','Activo'),(41,'Pescador','Activo'),(42,'Policía','Activo'),(43,'Cajero','Activo');
/*!40000 ALTER TABLE `ocupacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta_encuesta`
--

DROP TABLE IF EXISTS `pregunta_encuesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pregunta_encuesta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `tipoPregunta` varchar(80) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_usuario` int DEFAULT NULL,
  `fk_subcategoria` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pregunta_encuesta_usuario1_idx` (`fk_usuario`),
  KEY `fk_pregunta_encuesta_subcategoria1_idx` (`fk_subcategoria`),
  CONSTRAINT `fk_pregunta_encuesta_subcategoria1` FOREIGN KEY (`fk_subcategoria`) REFERENCES `subcategoria` (`id`),
  CONSTRAINT `fk_pregunta_encuesta_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta_encuesta`
--

LOCK TABLES `pregunta_encuesta` WRITE;
/*!40000 ALTER TABLE `pregunta_encuesta` DISABLE KEYS */;
INSERT INTO `pregunta_encuesta` VALUES (91,'¿Cuáles serían las características que te gustaría que tuviera un jabón?','Abierta','Activa',22,2),(92,'¿Los jabones para bañarse son efectivos para lavar platos?','Verdadero o Falso','Activa',22,2),(93,'¿Cuántas veces utilizas jabón al día?','Selección Simple','Activa',22,2),(94,'De todas las opciones, marque los elementos más importantes que debería tener un jabón','Selección Múltiple','Activa',22,2),(95,'Del uno al cinco ¿Qué te pareció esta encuesta?','Escala','Activa',22,2),(96,'¿Comprarías gel corporal?','Verdadero o Falso','Activa',22,3),(97,'¿Cuántas veces utilizas gel corporal al día?','Selección Simple','Activa',22,3),(98,'De todas las opciones, marque los elementos más importantes que debería tener un gel corporal','Selección Múltiple','Activa',22,3),(99,'Del uno al cinco ¿Qué te pareció esta encuesta?','Escala','Activa',22,3),(100,'¿Qué efectos secundarios has tenido al aplicarte un gel corporal?','Abierta','Activa',22,3),(101,'¿De niño te gustaba comer pasta dental?, explique por qué','Abierta','Activa',22,4),(102,'¿La primera pasta dentífrica fue creada por los egipcios hace 4000 años?','Verdadero o Falso','Activa',22,4),(103,'¿Cuántas veces utilizas pasta de dientes al día?','Selección Simple','Activa',22,4),(104,'De todas las opciones, marque los elementos más importantes que debería tener una pasta de dientes','Selección Múltiple','Activa',22,4),(105,'Del uno al cinco ¿Qué te pareció esta encuesta?','Escala','Activa',22,4),(106,'¿Cuáles serían las características ideales de un champú? Explique por qué','Abierta','Activa',22,5),(107,'¿El término y el servicio fueron introducidos en el Reino Unido por Sake Dean Mahomed?','Verdadero o Falso','Activa',22,5),(108,'¿Cuántas veces utilizas champú al día?','Selección Simple','Activa',22,5),(109,'De todas las opciones, marque los elementos más importantes que debería tener un champú','Selección Múltiple','Activa',22,5),(110,'Del uno al cinco ¿Qué te pareció esta encuesta?','Escala','Activa',22,5),(111,'¿Te reseca la piel?','Verdadero o Falso','Activo',1,2),(112,'¿Tiene buen aroma?','Verdadero o Falso','Activo',1,2),(113,'¿Qué tanto le gustó el jabón?','Escala','Activo',1,2);
/*!40000 ALTER TABLE `pregunta_encuesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preguntas_estudio`
--

DROP TABLE IF EXISTS `preguntas_estudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preguntas_estudio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estatus` varchar(50) NOT NULL,
  `fk_estudio` int NOT NULL,
  `fk_pregunta_encuesta` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_preguntas_estudio_estudio1_idx` (`fk_estudio`),
  KEY `fk_preguntas_estudio_pregunta_encuesta1_idx` (`fk_pregunta_encuesta`),
  CONSTRAINT `fk_preguntas_estudio_estudio1` FOREIGN KEY (`fk_estudio`) REFERENCES `estudio` (`id`),
  CONSTRAINT `fk_preguntas_estudio_pregunta_encuesta1` FOREIGN KEY (`fk_pregunta_encuesta`) REFERENCES `pregunta_encuesta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntas_estudio`
--

LOCK TABLES `preguntas_estudio` WRITE;
/*!40000 ALTER TABLE `preguntas_estudio` DISABLE KEYS */;
INSERT INTO `preguntas_estudio` VALUES (147,'Activo',141,111),(148,'Activo',141,112),(149,'Activo',141,113);
/*!40000 ALTER TABLE `preguntas_estudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presentacion`
--

DROP TABLE IF EXISTS `presentacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presentacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentacion`
--

LOCK TABLES `presentacion` WRITE;
/*!40000 ALTER TABLE `presentacion` DISABLE KEYS */;
INSERT INTO `presentacion` VALUES (1,'1 unidad','1 unidad','Activo'),(2,'2 unidades','2 unidades','Activo'),(3,'3 unidades','3 unidades','Inactivo'),(4,'4 unidades','4 unidades','Activo'),(5,'5 unidades','5 unidades','Activo'),(6,'6 unidades','6 unidades','Activo'),(7,'10 unidades','10 unidades','Activo'),(8,'12 unidades','12 unidades','Activo'),(9,'100 unidades','100 unidades','Activo');
/*!40000 ALTER TABLE `presentacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_usuario` int NOT NULL,
  `fk_subcategoria` int NOT NULL,
  `fk_marca` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_producto_usuario1_idx` (`fk_usuario`),
  KEY `fk_producto_subcategoria1_idx` (`fk_subcategoria`),
  KEY `fk_producto_marca1_idx` (`fk_marca`),
  CONSTRAINT `fk_producto_marca1` FOREIGN KEY (`fk_marca`) REFERENCES `marca` (`id`),
  CONSTRAINT `fk_producto_subcategoria1` FOREIGN KEY (`fk_subcategoria`) REFERENCES `subcategoria` (`id`),
  CONSTRAINT `fk_producto_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Jabón Dove','Nuevo jabón para larvarse el cuerpo','Activo',20,2,2),(2,'Gel corporal Dove','Nuevo Gel corporal ','Activo',20,3,2),(3,'Pasta de dientes Colgate','Pasta de dientes de menta','Activo',20,4,3),(4,'Pasta de dientes OralB','Nueva pasta de dientes de fresa','Activo',20,4,3),(5,'Champú P&G','Nuevo champú control caída','Activo',20,5,7);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_presentacion_tipo`
--

DROP TABLE IF EXISTS `producto_presentacion_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_presentacion_tipo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estatus` varchar(50) NOT NULL,
  `fk_producto` int NOT NULL,
  `fk_presentacion` int NOT NULL,
  `fk_tipo` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_producto_presentacion_presentacion1_idx` (`fk_presentacion`),
  KEY `fk_producto_presentacion_producto1_idx` (`fk_producto`),
  KEY `fk_producto_presentacion_tipo_tipo1_idx` (`fk_tipo`),
  CONSTRAINT `fk_producto_presentacion_presentacion1` FOREIGN KEY (`fk_presentacion`) REFERENCES `presentacion` (`id`),
  CONSTRAINT `fk_producto_presentacion_producto1` FOREIGN KEY (`fk_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `fk_producto_presentacion_tipo_tipo1` FOREIGN KEY (`fk_tipo`) REFERENCES `tipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_presentacion_tipo`
--

LOCK TABLES `producto_presentacion_tipo` WRITE;
/*!40000 ALTER TABLE `producto_presentacion_tipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto_presentacion_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuesta`
--

DROP TABLE IF EXISTS `respuesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuesta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `respuestaAbierta` varchar(500) DEFAULT NULL,
  `escala` varchar(100) DEFAULT NULL,
  `verdaderoFalso` varchar(30) DEFAULT NULL,
  `respuestaSimple` varchar(100) DEFAULT NULL,
  `respuestaMultiple` varchar(100) DEFAULT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_preguntas_estudio` int NOT NULL,
  `fk_usuario` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_respuesta_preguntas_estudio1_idx` (`fk_preguntas_estudio`),
  KEY `fk_respuesta_usuario1_idx` (`fk_usuario`),
  CONSTRAINT `fk_respuesta_preguntas_estudio1` FOREIGN KEY (`fk_preguntas_estudio`) REFERENCES `preguntas_estudio` (`id`),
  CONSTRAINT `fk_respuesta_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuesta`
--

LOCK TABLES `respuesta` WRITE;
/*!40000 ALTER TABLE `respuesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuesta_pregunta`
--

DROP TABLE IF EXISTS `respuesta_pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuesta_pregunta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_pregunta_encuesta` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_respuesta_pregunta_pregunta_encuesta1_idx` (`fk_pregunta_encuesta`),
  CONSTRAINT `fk_respuesta_pregunta_pregunta_encuesta1` FOREIGN KEY (`fk_pregunta_encuesta`) REFERENCES `pregunta_encuesta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuesta_pregunta`
--

LOCK TABLES `respuesta_pregunta` WRITE;
/*!40000 ALTER TABLE `respuesta_pregunta` DISABLE KEYS */;
INSERT INTO `respuesta_pregunta` VALUES (1,'Una vez al día','Activo',93),(2,'Dos veces al día','Activo',93),(3,'Tres veces al día','Activo',93),(4,'Más de tres veces al día','Activo',93),(5,'No utilizo jabón','Activo',93),(6,'Buena forma','Activo',94),(7,'Buena consistencia','Activo',94),(8,'Buen olor','Activo',94),(9,'Que quite los malos olores','Activo',94),(10,'Que haga bastante espuma','Activo',94),(11,'Una vez al día','Activo',97),(12,'Dos veces al día','Activo',97),(13,'Tres veces al día','Activo',97),(14,'Más de tres veces al día','Activo',97),(15,'No utilizo gel corporal','Activo',97),(16,'Cremosidad','Activo',98),(17,'Mentolado','Activo',98),(18,'Buen olor','Activo',98),(19,'Que quite los malos olores','Activo',98),(20,'Que haga bastante espuma','Activo',98),(21,'Una vez al día','Activo',103),(22,'Dos veces al día','Activo',103),(23,'Tres veces al día','Activo',103),(24,'Más de tres veces al día','Activo',103),(25,'No utilizo pasta de dientes','Activo',103),(26,'Cremosidad','Activo',104),(27,'Mentolado','Activo',104),(28,'Buen olor','Activo',104),(29,'Que quite la suciedad de los dientes','Activo',104),(30,'Que haga bastante espuma','Activo',104),(31,'Una vez al día','Activo',108),(32,'Dos veces al día','Activo',108),(33,'Tres veces al día','Activo',108),(34,'Más de tres veces al día','Activo',108),(35,'No utilizo champú','Activo',108),(36,'Cremosidad','Activo',109),(37,'Duración','Activo',109),(38,'Buen olor','Activo',109),(39,'Que quite la suciedad del cabello','Activo',109),(40,'Que haga bastante espuma','Activo',109);
/*!40000 ALTER TABLE `respuesta_pregunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador','Activo'),(2,'Cliente','Activo'),(3,'Analista','Activo'),(4,'Encuestado','Activo');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_estudio`
--

DROP TABLE IF EXISTS `solicitud_estudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud_estudio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `genero` varchar(80) DEFAULT NULL,
  `edadMaxima` int NOT NULL,
  `edadMinima` int NOT NULL,
  `estadoCivil` varchar(80) DEFAULT NULL,
  `disponibilidadEnLinea` varchar(50) DEFAULT NULL,
  `cantidadPersonas` int DEFAULT NULL,
  `cantidadHijos` int DEFAULT NULL,
  `generoHijos` varchar(80) DEFAULT NULL,
  `edadMinimaHijos` int DEFAULT NULL,
  `edadMaximaHijos` int DEFAULT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_nivelEconomico` int DEFAULT NULL,
  `fk_usuario` int NOT NULL,
  `fk_producto` int NOT NULL,
  `fk_ocupacion` int DEFAULT NULL,
  `fk_nivelAcademico` int DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_solicitud_estudio_nivel_economico1_idx` (`fk_nivelEconomico`),
  KEY `fk_solicitud_estudio_usuario1_idx` (`fk_usuario`),
  KEY `fk_solicitud_estudio_producto1_idx` (`fk_producto`),
  KEY `fk_solicitud_estudio_ocupacion1_idx` (`fk_ocupacion`),
  KEY `fk_solicitud_estudio_nivel_academico1_idx` (`fk_nivelAcademico`),
  CONSTRAINT `fk_solicitud_estudio_nivel_academico1` FOREIGN KEY (`fk_nivelAcademico`) REFERENCES `nivel_academico` (`id`),
  CONSTRAINT `fk_solicitud_estudio_nivel_economico1` FOREIGN KEY (`fk_nivelEconomico`) REFERENCES `nivel_economico` (`id`),
  CONSTRAINT `fk_solicitud_estudio_ocupacion1` FOREIGN KEY (`fk_ocupacion`) REFERENCES `ocupacion` (`id`),
  CONSTRAINT `fk_solicitud_estudio_producto1` FOREIGN KEY (`fk_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `fk_solicitud_estudio_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_estudio`
--

LOCK TABLES `solicitud_estudio` WRITE;
/*!40000 ALTER TABLE `solicitud_estudio` DISABLE KEYS */;
INSERT INTO `solicitud_estudio` VALUES (34,'Estudio sobre nuevo jabón Dove','Masculino',40,20,'Soltero','Si',3,0,'',0,0,'Activo',1,20,1,1,1,'Procesado'),(35,'Estudio sobre jabón Suave','Masculino',40,20,'Soltero','Si',3,0,'',0,0,'Activo',1,20,1,1,1,'Procesado'),(36,'Estudio sobre pasta de dientes Colgate','Femenino',50,20,'Casado','No',4,0,'',0,0,'Activo',1,20,3,1,2,'En espera');
/*!40000 ALTER TABLE `solicitud_estudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitudestudio_lugar`
--

DROP TABLE IF EXISTS `solicitudestudio_lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitudestudio_lugar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estatus` varchar(50) NOT NULL,
  `fk_lugar` int NOT NULL,
  `fk_solicitud_estudio` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_estudio_lugar_lugar1_idx` (`fk_lugar`),
  KEY `fk_estudio_lugar_solicitud_estudio1_idx` (`fk_solicitud_estudio`),
  CONSTRAINT `fk_estudio_lugar_lugar1` FOREIGN KEY (`fk_lugar`) REFERENCES `lugar` (`id`),
  CONSTRAINT `fk_estudio_lugar_solicitud_estudio1` FOREIGN KEY (`fk_solicitud_estudio`) REFERENCES `solicitud_estudio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudestudio_lugar`
--

LOCK TABLES `solicitudestudio_lugar` WRITE;
/*!40000 ALTER TABLE `solicitudestudio_lugar` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitudestudio_lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategoria`
--

DROP TABLE IF EXISTS `subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_categoria` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subcategoria_categoria1_idx` (`fk_categoria`),
  CONSTRAINT `fk_subcategoria_categoria1` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,'Desodorante','Ayuda a que tus axilas huelas mejor','Inactivo',1),(2,'Jabón','Limpia tu cuerpo','Activo',1),(3,'Gel corporal','Limpia tu cuerpo','Activo',1),(4,'Pasta de dientes','Ayuda en la higiene de tus dientes','Activo',1),(5,'Champú','Mantiene tu cabello más sano','Activo',1),(6,'Base','Es una base para aplicar al maquillarse','Activo',2),(7,'Corrector','Es un corrector para la piel que se aplica al maquillarse','Activo',2),(8,'Rubor','Es un rubor para agregar al proceso de maquillaje facial','Activo',2),(9,'Bronceador','Es un bronceador','Activo',2),(10,'Iluminador','Ilumina tu rostro','Activo',2),(11,'Desinfectante','Elimina bacterias de cualquier espacio de tu hogar','Activo',3),(12,'Detergente líquido','Limpia el piso de tu casa de una forma muy efectiva','Activo',3),(13,'Limpiamuebles','Quítale la suciedad de los muebles que no usas','Activo',3),(14,'Anti cal','No sabemos para que es esto pero deberías comprarlo','Activo',3),(15,'Quitamanchas','Quita cualquier mancha de tu alrededor. ¡Incluso a tu ex!','Activo',3),(16,'Jabones perfumados grasientos plus','Mas olor exquisito a grasa','Activo',1);
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono`
--

DROP TABLE IF EXISTS `telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefono` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(30) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `estatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fk_informacion` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_telefono_informacion1_idx` (`fk_informacion`),
  CONSTRAINT `fk_telefono_informacion1` FOREIGN KEY (`fk_informacion`) REFERENCES `informacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono`
--

LOCK TABLES `telefono` WRITE;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` VALUES (1,'0414-5539834','Activo',5),(2,'0412-2744937','Activo',8),(3,'0414-3195586','Activo',16),(4,'0416-5789413','Activo',17);
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `estatus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES (1,'Aerosol','Desodorantes','Activo'),(2,'Roll-on','Desodorantes','Activo'),(3,'Barra','Desodorante o jabón','Inactivo'),(4,'En polvo compacto','Maquillaje','Activo'),(5,'En polvo suelto','Maquillaje','Activo'),(6,'Líquida','Maquillaje','Activo');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `codigoRecuperacion` varchar(50) DEFAULT NULL,
  `estatus` varchar(50) NOT NULL,
  `fk_rol` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_rol1_idx` (`fk_rol`),
  CONSTRAINT `fk_usuario_rol1` FOREIGN KEY (`fk_rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'lkorn','amacgaughey0@nba.com',' ','Inactivo',2),(2,'bmcfarland1','lgregs1@cnbc.com','3iI-AzB','Activo',3),(3,'amcisaac2','cwestell2@360.cn',' ','Inactivo',1),(4,'bguillart3','ccoad3@newyorker.com','4pP-meI','Activo',3),(5,'Sarah Perez','bsoutherns4@si.edu',' ','Activo',4),(6,'acaulcott5','pbellam5@deviantart.com','3eH-0s4','Activo',1),(7,'cvears6','ypetti6@is.gd',' ','Inactivo',2),(8,'María Lopez','icharlewood7@studiopress.com',' ','Inactivo',4),(9,'lpetyakov8','adarbishire8@parallels.com','4gK-v7R','Activo',4),(10,'gmacteggart9','hfeldman9@meetup.com','3tH-ouL','Activo',1),(11,'jgiraudouxa','billema@cpanel.net','8hR-f1T','Activo',3),(12,'egriffeyb','rdrakersb@istockphoto.com',' ','Inactivo',1),(13,'ashieldsc','smacgrathc@unicef.org',' ','Activo',2),(14,'gbeamand','csmitherd@artisteer.com',' ','Inactivo',2),(15,'ndiboldie','rolyffe@liveinternet.ru',' ','Inactivo',1),(17,'greggspinetti','greggspinetti@gmail.com','564','Activo',1),(18,'testEncuestado','encuestado@gmail.com','','Activo',4),(20,'cliente','cliente@gmail.com','','Activo',2),(21,'analista','analista@gmail.com','','Activo',3),(22,'admin','admin@gmail.com','','Activo',1),(24,'Carlos ','carlos123@gmail.com','','Activo',4);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_estudio`
--

DROP TABLE IF EXISTS `usuario_estudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_estudio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estatus` varchar(50) NOT NULL,
  `fk_usuario` int NOT NULL,
  `fk_estudio` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_historico_estado_usuario1_idx` (`fk_usuario`),
  KEY `fk_historico_estado_estudio1_idx` (`fk_estudio`),
  CONSTRAINT `fk_historico_estado_estudio1` FOREIGN KEY (`fk_estudio`) REFERENCES `estudio` (`id`),
  CONSTRAINT `fk_historico_estado_usuario12` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_estudio`
--

LOCK TABLES `usuario_estudio` WRITE;
/*!40000 ALTER TABLE `usuario_estudio` DISABLE KEYS */;
INSERT INTO `usuario_estudio` VALUES (1,'En espera',18,141),(2,'En espera',24,141);
/*!40000 ALTER TABLE `usuario_estudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mercadeoucab'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-14 20:58:57
