-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: thuexetaplai
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bill_amount` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `late_charge` bigint DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `rent_time` bigint DEFAULT NULL,
  `slug` varchar(255) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `car_id` bigint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKnw26amfg9btxkpb9veani0xgi` (`slug`),
  KEY `IDX_Search` (`fullname`,`phone_number`,`email`),
  KEY `FKca6qp8h7nywor437hfrws1t6b` (`car_id`),
  KEY `FKn0v1223948060lpbkrx1gnima` (`course_id`),
  KEY `FKqx70tpbw36xkamb2ogahv6dtd` (`staff_id`),
  KEY `FK7mpw57xrttcc0w3e2qkifana9` (`modified_by`),
  CONSTRAINT `FK7mpw57xrttcc0w3e2qkifana9` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FKca6qp8h7nywor437hfrws1t6b` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),
  CONSTRAINT `FKn0v1223948060lpbkrx1gnima` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKqx70tpbw36xkamb2ogahv6dtd` FOREIGN KEY (`staff_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (1,360000,'2021-06-05 22:59:14','trantheduyk13@gmail.com','Duy',15350000,'2021-06-05 23:13:50','0798455595',2,'$2a$10$LmHt0THGFbDsvTEilaxqyej2IvtPPqAdgdXqdsNJvBgpQiq7KO1y','2021-05-30 11:15:00','PAID','SA_HINH',2,NULL,3,3),(7,360000,'2021-06-05 23:21:00','trantheduyk13@gmail.com','Duy',15400000,'2021-06-05 23:21:17','0798455595',2,'$2a$10$pwX4BkRnXRWuPGzBjydGlukelo4jOjsGI6pjtoBmC7xMDLVy9ENC','2021-05-30 11:15:00','PAID','SA_HINH',2,NULL,3,3),(8,360000,'2021-06-05 23:22:38','trantheduyk13@gmail.com','Duy',15400000,'2021-06-05 23:22:45','0798455595',2,'$2a$10$cnn3PGhgR.HA1HEeojznmu7idlqOYhdCgVJVDztu70vDitT5lyWu2','2021-05-30 11:15:00','PAID','SA_HINH',2,NULL,3,3),(9,360000,'2021-06-05 23:29:25','trantheduyk13@gmail.com','Duy',15400000,'2021-06-05 23:30:11','0798455595',2,'$2a$10$.kzrxXYAsLi.2VmjiijX4OkneTGYRvs7DiDcX3JuyGgLEjoA.gKHK','2021-05-30 11:15:00','PAID','SA_HINH',2,NULL,3,3);
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cost_per_hour` int NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `license_plate` varchar(255) DEFAULT NULL,
  `manufacturing_year` int NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK4spu7er7qu6w1neutebr3aw5o` (`name`,`slug`,`license_plate`),
  KEY `IDX_Search` (`name`,`manufacturing_year`,`license_plate`,`created_date`,`modified_date`),
  KEY `FKlhe2wv7nsbluwejany6eiq2eq` (`created_by`),
  KEY `FK9cereqqo7etoqfo1grt7ky3sa` (`modified_by`),
  KEY `FK310labqcoawyj05t4gvvfjbg9` (`category_id`),
  CONSTRAINT `FK310labqcoawyj05t4gvvfjbg9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FK9cereqqo7etoqfo1grt7ky3sa` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FKlhe2wv7nsbluwejany6eiq2eq` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,150000,'2021-06-04 11:42:27','https://tran-the-duy-208.s3.ap-southeast-1.amazonaws.com/images/car/040620211142-kia-morning-kia-morning-so-san-2016.jpg','51G - 165.00',2016,'2021-06-04 11:42:27','Kia Morning','kia-morning','AVAILABLE','ENABLE',2,2,5),(2,180000,'2021-06-04 11:45:17','https://tran-the-duy-208.s3.ap-southeast-1.amazonaws.com/images/car/040620211145-toyota-vios-toyota-vios-so-san-2021.jpg','51G - 691.59',2021,'2021-06-04 11:45:17','Toyota Vios','toyota-vios','AVAILABLE','ENABLE',2,2,5),(3,160000,'2021-06-04 11:45:50','https://tran-the-duy-208.s3.ap-southeast-1.amazonaws.com/images/car/040620211145-innova-e-innova-e-so-san-2015.jpg','51G - 742.33',2015,'2021-06-04 11:45:50','Innova E','innova-e','AVAILABLE','ENABLE',2,2,6),(4,120000,'2021-06-04 11:46:22','https://tran-the-duy-208.s3.ap-southeast-1.amazonaws.com/images/car/040620211146-toyota-zace-toyota-zace-so-san-2005.jpg','51G - 270.52',2005,'2021-06-04 11:46:22','Toyota Zace','toyota-zace','AVAILABLE','ENABLE',2,2,6),(5,160000,'2021-06-04 11:49:45','https://tran-the-duy-208.s3.ap-southeast-1.amazonaws.com/images/car/040620211149-hyundai-grand-i10-Hyundai-Grand-i10-tu-dong-2017.jpg','51G - 375.23',2017,'2021-06-04 11:49:45','Hyundai Grand i10','hyundai-grand-i10','AVAILABLE','ENABLE',2,2,3),(6,160000,'2021-06-04 11:53:43','https://tran-the-duy-208.s3.ap-southeast-1.amazonaws.com/images/car/040620211153-mitsubishi-attrage-Mitsubishi-Attrage-tu-dong-2018.jpg','51G - 262.28',2018,'2021-06-04 11:53:43','Mitsubishi Attrage','mitsubishi-attrage','AVAILABLE','ENABLE',2,2,3),(7,180000,'2021-06-04 11:54:20','https://tran-the-duy-208.s3.ap-southeast-1.amazonaws.com/images/car/040620211154-mitsubishi-xpander-Mitsubishi-Xpander-so-tu-dong-2019.jpg','51G - 272.13',2019,'2021-06-04 11:54:20','Mitsubishi Xpander','mitsubishi-xpander','AVAILABLE','ENABLE',2,2,4),(8,200000,'2021-06-04 11:54:59','https://tran-the-duy-208.s3.ap-southeast-1.amazonaws.com/images/car/040620211154-toyota-innova-toyota-innova-so-tu-dong-2021.jpg','51G - 454.57',2021,'2021-06-04 11:54:59','Toyota Innova','toyota-innova','AVAILABLE','ENABLE',2,2,4);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsdd1ik90kw2ldv7c1xxy8wtcf` (`name`,`slug`),
  KEY `IDX_Search` (`name`,`modified_date`,`created_date`),
  KEY `FK5yfru0au6kpyqs4tonky5vfne` (`created_by`),
  KEY `FKbxwpgyorhtg7fclpa7gagswc5` (`modified_by`),
  KEY `FKsaok720gsu4u2wrgbk10b5n8d` (`parent_id`),
  CONSTRAINT `FK5yfru0au6kpyqs4tonky5vfne` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FKbxwpgyorhtg7fclpa7gagswc5` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsaok720gsu4u2wrgbk10b5n8d` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'2021-06-04 11:21:56','Xe số tự động','2021-06-04 11:21:56','Xe số tự động','xe-so-tu-dong','ENABLE',2,2,NULL),(2,'2021-06-04 11:22:07','Xe số sàn','2021-06-04 11:22:07','Xe số sàn','xe-so-san','ENABLE',2,2,NULL),(3,'2021-06-04 11:24:58','Xe số tự động 4 chỗ','2021-06-04 11:24:58','Xe số tự động 4 chỗ','xe-so-tu-dong-4-cho','ENABLE',2,2,1),(4,'2021-06-04 11:25:09','Xe số tự động 7 chỗ','2021-06-04 11:25:09','Xe số tự động 7 chỗ','xe-so-tu-dong-7-cho','ENABLE',2,2,1),(5,'2021-06-04 11:25:23','Xe số sàn 4 chỗ','2021-06-04 11:25:23','Xe số sàn 4 chỗ','xe-so-san-4-cho','ENABLE',2,2,2),(6,'2021-06-04 11:25:28','Xe số sàn 7 chỗ','2021-06-04 11:25:28','Xe số sàn 7 chỗ','xe-so-san-7-cho','ENABLE',2,2,2);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL,
  `dislike` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `level` int NOT NULL,
  `likes` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlri30okf66phtcgbe5pok7cc0` (`parent_id`),
  KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`),
  CONSTRAINT `FKh4c7lvsc298whoyd4w9ta25cr` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`),
  CONSTRAINT `FKlri30okf66phtcgbe5pok7cc0` FOREIGN KEY (`parent_id`) REFERENCES `comments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `discount` float NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `price` bigint NOT NULL,
  `slug` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `time_course` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKd902ql0s6x8jvmbk26gw0653t` (`title`,`slug`),
  KEY `FK4u40nf46n1nqa5h38sn5g17ac` (`created_by`),
  KEY `FK1l2lvqqocnosyl3r151vx74yj` (`modified_by`),
  CONSTRAINT `FK1l2lvqqocnosyl3r151vx74yj` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FK4u40nf46n1nqa5h38sn5g17ac` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `slug` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK2kowh7xsrnrmtueyay3q04vix` (`title`,`slug`),
  KEY `IDX_Search` (`title`,`status`,`modified_date`,`created_date`),
  KEY `FK4s8wtgwpo1h8p5tsy9f04ybjg` (`created_by`),
  KEY `FKmg4r33ry5iurbaguqr614qeas` (`modified_by`),
  CONSTRAINT `FK4s8wtgwpo1h8p5tsy9f04ybjg` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FKmg4r33ry5iurbaguqr614qeas` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts_tags`
--

DROP TABLE IF EXISTS `posts_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts_tags` (
  `post_id` bigint NOT NULL,
  `tag_id` bigint NOT NULL,
  PRIMARY KEY (`post_id`,`tag_id`),
  KEY `FK4svsmj4juqu2l8yaw6whr1v4v` (`tag_id`),
  CONSTRAINT `FK4svsmj4juqu2l8yaw6whr1v4v` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`),
  CONSTRAINT `FKcreclgob71ibo58gsm6l5wp6` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts_tags`
--

LOCK TABLES `posts_tags` WRITE;
/*!40000 ALTER TABLE `posts_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`),
  KEY `IDX_Search` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MODERATOR'),(3,'ROLE_STAFF');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKaylasyx5dmytvddg00a5cd8hj` (`slug`,`name`),
  KEY `IDX_Search` (`name`,`created_date`,`modified_date`),
  KEY `FK3sh3rn8hrvjb08s6vu09bldt7` (`created_by`),
  KEY `FK1v76mh3ly859uuw9467askjmg` (`modified_by`),
  CONSTRAINT `FK1v76mh3ly859uuw9467askjmg` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FK3sh3rn8hrvjb08s6vu09bldt7` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,'2021-06-04 10:58:59','2021-06-04 10:58:59','Học lái xe','hoc-lai-xe',2,2),(2,'2021-06-04 10:59:06','2021-06-04 10:59:06','A1','a1',2,2),(3,'2021-06-04 10:59:59','2021-06-04 10:59:59','A2','a2',1,1),(4,'2021-06-04 11:03:33','2021-06-04 11:03:33','giao thông mỗi ngày','giao-thong-moi-ngay',1,1);
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfnranlqhubvw04boopn028e6` (`username`,`email`),
  KEY `IDX_Search` (`username`,`email`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'$2a$10$lplH6MMGoSbpHrIUzeayNeJdjRQ7eQfBTbGT1Q9rVX/gtk15FqOkO','ENABLE','admin'),(2,'','$2a$10$qS.20L8MmsX0CNmafgFqyevRLf1fi3GeFfSBoNjzRmSPgZlI7Q5nK','ENABLE','duytt1'),(3,'','$2a$10$vQk4HSkydtO4ctzVMZNGseXqBNoQmEYv6gco0CaX/ICRZlF5hW1ai','ENABLE','duytt2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-06 17:24:50
