-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.54-1ubuntu4


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema connect
--

CREATE DATABASE IF NOT EXISTS connect;
USE connect;

--
-- Definition of table `connect`.`osf_activities`
--

DROP TABLE IF EXISTS `connect`.`osf_activities`;
CREATE TABLE  `connect`.`osf_activities` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_description` longtext NOT NULL,
  `_entered` datetime NOT NULL,
  `_entity` varchar(255) DEFAULT NULL,
  `_format` varchar(255) NOT NULL,
  `_linked_id` bigint(20) DEFAULT NULL,
  `_modified` datetime NOT NULL,
  `_type` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) DEFAULT NULL,
  `_parent_id` bigint(20) DEFAULT NULL,
  `_project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKDFC7CD4A482DA038` (`_parent_id`),
  KEY `FKDFC7CD4AFA11FB90` (`_entered_by_id`),
  KEY `FKDFC7CD4AADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_activities`
--

/*!40000 ALTER TABLE `osf_activities` DISABLE KEYS */;
LOCK TABLES `osf_activities` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_activities` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_albums`
--

DROP TABLE IF EXISTS `connect`.`osf_albums`;
CREATE TABLE  `connect`.`osf_albums` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK376417C1FA11FB90` (`_entered_by_id`),
  KEY `FK376417C1ADE2AB90` (`_project_id`),
  KEY `FK376417C18DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_albums`
--

/*!40000 ALTER TABLE `osf_albums` DISABLE KEYS */;
LOCK TABLES `osf_albums` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_albums` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_answers`
--

DROP TABLE IF EXISTS `connect`.`osf_answers`;
CREATE TABLE  `connect`.`osf_answers` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKB978EC38FA11FB90` (`_entered_by_id`),
  KEY `FKB978EC38B7153A81` (`_question_id`),
  KEY `FKB978EC388DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_answers`
--

/*!40000 ALTER TABLE `osf_answers` DISABLE KEYS */;
LOCK TABLES `osf_answers` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_answers` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_attachments`
--

DROP TABLE IF EXISTS `connect`.`osf_attachments`;
CREATE TABLE  `connect`.`osf_attachments` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content_type` varchar(255) NOT NULL,
  `_entered` datetime DEFAULT NULL,
  `_file_name` varchar(255) NOT NULL,
  `_size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_attachments`
--

/*!40000 ALTER TABLE `osf_attachments` DISABLE KEYS */;
LOCK TABLES `osf_attachments` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_attachments` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_authorizations`
--

DROP TABLE IF EXISTS `connect`.`osf_authorizations`;
CREATE TABLE  `connect`.`osf_authorizations` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_secret` varchar(255) NOT NULL,
  `_target` varchar(255) NOT NULL,
  `_token` varchar(255) NOT NULL,
  `_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKAFECC9F717F46124` (`_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_authorizations`
--

/*!40000 ALTER TABLE `osf_authorizations` DISABLE KEYS */;
LOCK TABLES `osf_authorizations` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_authorizations` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_comments`
--

DROP TABLE IF EXISTS `connect`.`osf_comments`;
CREATE TABLE  `connect`.`osf_comments` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_entity` varchar(255) DEFAULT NULL,
  `_linked_id` bigint(20) NOT NULL,
  `_modified` datetime NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK6F832291FA11FB90` (`_entered_by_id`),
  KEY `FK6F8322918DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_comments`
--

/*!40000 ALTER TABLE `osf_comments` DISABLE KEYS */;
LOCK TABLES `osf_comments` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_comments` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_events`
--

DROP TABLE IF EXISTS `connect`.`osf_events`;
CREATE TABLE  `connect`.`osf_events` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_all_day` bit(1) DEFAULT NULL,
  `_description` longtext,
  `_enabled` bit(1) DEFAULT NULL,
  `_end_date` datetime NOT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime NOT NULL,
  `_start_date` datetime NOT NULL,
  `_title` varchar(255) NOT NULL,
  `_type` varchar(255) NOT NULL,
  `_url` varchar(255) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK3EC5A8F6FA11FB90` (`_entered_by_id`),
  KEY `FK3EC5A8F6ADE2AB90` (`_project_id`),
  KEY `FK3EC5A8F68DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_events`
--

/*!40000 ALTER TABLE `osf_events` DISABLE KEYS */;
LOCK TABLES `osf_events` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_events` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_files`
--

DROP TABLE IF EXISTS `connect`.`osf_files`;
CREATE TABLE  `connect`.`osf_files` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_entered_by_id` bigint(20) DEFAULT NULL,
  `_folder_id` bigint(20) DEFAULT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_real_file_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKAF7A02FA99FFD4D8` (`_folder_id`),
  KEY `FKAF7A02FA402E06BB` (`_real_file_id`),
  KEY `FKAF7A02FAFA11FB90` (`_entered_by_id`),
  KEY `FKAF7A02FA8DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_files`
--

/*!40000 ALTER TABLE `osf_files` DISABLE KEYS */;
LOCK TABLES `osf_files` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_files` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_folders`
--

DROP TABLE IF EXISTS `connect`.`osf_folders`;
CREATE TABLE  `connect`.`osf_folders` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime NOT NULL,
  `_name` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_parent_id` bigint(20) DEFAULT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKC341A588F5F5AF1C` (`_parent_id`),
  KEY `FKC341A588FA11FB90` (`_entered_by_id`),
  KEY `FKC341A588ADE2AB90` (`_project_id`),
  KEY `FKC341A5888DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_folders`
--

/*!40000 ALTER TABLE `osf_folders` DISABLE KEYS */;
LOCK TABLES `osf_folders` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_folders` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_forums`
--

DROP TABLE IF EXISTS `connect`.`osf_forums`;
CREATE TABLE  `connect`.`osf_forums` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_allow_attachment` bit(1) DEFAULT NULL,
  `_description` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_level` int(11) DEFAULT NULL,
  `_modified` datetime NOT NULL,
  `_name` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK401DDF4FFA11FB90` (`_entered_by_id`),
  KEY `FK401DDF4FADE2AB90` (`_project_id`),
  KEY `FK401DDF4F8DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_forums`
--

/*!40000 ALTER TABLE `osf_forums` DISABLE KEYS */;
LOCK TABLES `osf_forums` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_forums` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_keywords`
--

DROP TABLE IF EXISTS `connect`.`osf_keywords`;
CREATE TABLE  `connect`.`osf_keywords` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entered` datetime DEFAULT NULL,
  `_keyword` varchar(255) NOT NULL,
  `_number` bigint(20) NOT NULL,
  `_type` varchar(255) NOT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  `_entered_by_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKB299E44791C8ED4B` (`_category_id`),
  KEY `FKB299E447FA11FB90` (`_entered_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_keywords`
--

/*!40000 ALTER TABLE `osf_keywords` DISABLE KEYS */;
LOCK TABLES `osf_keywords` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_keywords` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_links`
--

DROP TABLE IF EXISTS `connect`.`osf_links`;
CREATE TABLE  `connect`.`osf_links` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_entity` varchar(255) NOT NULL,
  `_title` varchar(255) DEFAULT NULL,
  `_to_id` bigint(20) NOT NULL,
  `_from_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKAFCE983C4B929A81` (`_from_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_links`
--

/*!40000 ALTER TABLE `osf_links` DISABLE KEYS */;
LOCK TABLES `osf_links` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_links` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_mail_settings`
--

DROP TABLE IF EXISTS `connect`.`osf_mail_settings`;
CREATE TABLE  `connect`.`osf_mail_settings` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_host` varchar(255) DEFAULT NULL,
  `_password` varchar(255) DEFAULT NULL,
  `_port` int(11) DEFAULT NULL,
  `_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_mail_settings`
--

/*!40000 ALTER TABLE `osf_mail_settings` DISABLE KEYS */;
LOCK TABLES `osf_mail_settings` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_mail_settings` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_messages`
--

DROP TABLE IF EXISTS `connect`.`osf_messages`;
CREATE TABLE  `connect`.`osf_messages` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_entered` datetime NOT NULL,
  `_read` bit(1) DEFAULT NULL,
  `_subject` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_from_id` bigint(20) NOT NULL,
  `_read_by_id` bigint(20) DEFAULT NULL,
  `_to_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK77E045E9CAB7480D` (`_read_by_id`),
  KEY `FK77E045E98B22A250` (`_to_id`),
  KEY `FK77E045E94B929A81` (`_from_id`),
  KEY `FK77E045E9FA11FB90` (`_entered_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_messages`
--

/*!40000 ALTER TABLE `osf_messages` DISABLE KEYS */;
LOCK TABLES `osf_messages` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_messages` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_permissions`
--

DROP TABLE IF EXISTS `connect`.`osf_permissions`;
CREATE TABLE  `connect`.`osf_permissions` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_category_id` bigint(20) NOT NULL,
  `_resource_id` bigint(20) NOT NULL,
  `_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK93788EC772C99D44` (`_role_id`),
  KEY `FK93788EC791C8ED4B` (`_category_id`),
  KEY `FK93788EC7B73B2144` (`_resource_id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_permissions`
--

/*!40000 ALTER TABLE `osf_permissions` DISABLE KEYS */;
LOCK TABLES `osf_permissions` WRITE;
INSERT INTO `connect`.`osf_permissions` VALUES  (1,0x01,1,43,2),
 (2,0x01,1,44,1),
 (3,0x01,1,45,3),
 (4,0x01,1,47,1),
 (5,0x01,1,48,1),
 (6,0x01,1,49,3),
 (7,0x01,1,57,3),
 (8,0x01,1,58,1),
 (9,0x01,1,59,3),
 (10,0x01,1,6,1),
 (11,0x01,1,7,1),
 (12,0x01,1,8,2),
 (13,0x01,1,30,2),
 (14,0x01,1,27,1),
 (15,0x01,1,29,2),
 (16,0x01,1,28,3),
 (17,0x01,1,31,1),
 (18,0x01,1,32,1),
 (19,0x01,1,33,3),
 (20,0x01,1,17,1),
 (21,0x01,1,18,1),
 (22,0x01,1,19,3),
 (23,0x01,1,35,1),
 (24,0x01,1,36,1),
 (25,0x01,1,37,2),
 (26,0x01,1,39,3),
 (27,0x01,1,40,1),
 (28,0x01,1,41,1),
 (29,0x01,1,50,1),
 (30,0x01,1,51,1),
 (31,0x01,1,52,3),
 (32,0x01,1,10,1),
 (33,0x01,1,13,1),
 (34,0x01,1,14,1),
 (35,0x01,1,15,3),
 (36,0x01,1,11,1),
 (37,0x01,1,12,3),
 (38,0x01,1,2,1),
 (39,0x01,1,3,1),
 (40,0x01,1,4,1),
 (41,0x01,1,42,1),
 (42,0x01,1,9,3),
 (43,0x01,1,5,2),
 (44,0x01,1,16,3),
 (45,0x01,1,26,3),
 (46,0x01,1,46,3),
 (47,0x01,1,53,3),
 (48,0x01,1,38,1),
 (49,0x01,1,1,3),
 (50,0x01,1,34,2),
 (51,0x01,1,54,3),
 (52,0x01,1,55,1),
 (53,0x01,1,56,3),
 (54,0x01,1,23,3),
 (55,0x01,1,24,1),
 (56,0x01,1,25,3),
 (57,0x01,1,20,3),
 (58,0x01,1,21,1),
 (59,0x01,1,22,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_permissions` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_photos`
--

DROP TABLE IF EXISTS `connect`.`osf_photos`;
CREATE TABLE  `connect`.`osf_photos` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_album_id` bigint(20) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_real_file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK50CA505E37D54C87` (`_album_id`),
  KEY `FK50CA505E402E06BB` (`_real_file_id`),
  KEY `FK50CA505EFA11FB90` (`_entered_by_id`),
  KEY `FK50CA505E8DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_photos`
--

/*!40000 ALTER TABLE `osf_photos` DISABLE KEYS */;
LOCK TABLES `osf_photos` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_photos` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_post_categories`
--

DROP TABLE IF EXISTS `connect`.`osf_post_categories`;
CREATE TABLE  `connect`.`osf_post_categories` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_label` varchar(255) NOT NULL,
  `_level` int(11) DEFAULT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKC1E21FDEADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_post_categories`
--

/*!40000 ALTER TABLE `osf_post_categories` DISABLE KEYS */;
LOCK TABLES `osf_post_categories` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_post_categories` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_posts`
--

DROP TABLE IF EXISTS `connect`.`osf_posts`;
CREATE TABLE  `connect`.`osf_posts` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime DEFAULT NULL,
  `_keywords` varchar(255) DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_title` varchar(255) NOT NULL,
  `_top` bit(1) DEFAULT NULL,
  `_type` varchar(255) NOT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKB009C456CD8B5B71` (`_category_id`),
  KEY `FKB009C456FA11FB90` (`_entered_by_id`),
  KEY `FKB009C456ADE2AB90` (`_project_id`),
  KEY `FKB009C4568DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_posts`
--

/*!40000 ALTER TABLE `osf_posts` DISABLE KEYS */;
LOCK TABLES `osf_posts` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_posts` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_profiles`
--

DROP TABLE IF EXISTS `connect`.`osf_profiles`;
CREATE TABLE  `connect`.`osf_profiles` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_attributes` longtext,
  `_description` longtext,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_short_description` longtext,
  `_title` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_logo_id` bigint(20) DEFAULT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK57ADF087FA11FB90` (`_entered_by_id`),
  KEY `FK57ADF087ADE2AB90` (`_project_id`),
  KEY `FK57ADF0878DB1780` (`_modified_by_id`),
  KEY `FK57ADF087BCE64EAF` (`_logo_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_profiles`
--

/*!40000 ALTER TABLE `osf_profiles` DISABLE KEYS */;
LOCK TABLES `osf_profiles` WRITE;
INSERT INTO `connect`.`osf_profiles` VALUES  (1,NULL,NULL,'2011-05-31 15:35:02','2011-05-31 15:35:02',NULL,'Gavin Hu',1,NULL,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_profiles` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_project_categories`
--

DROP TABLE IF EXISTS `connect`.`osf_project_categories`;
CREATE TABLE  `connect`.`osf_project_categories` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_label` varchar(255) NOT NULL,
  `_level` int(11) DEFAULT NULL,
  `_sensitive` bit(1) DEFAULT NULL,
  `_site_id` bigint(20) NOT NULL,
  `_parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKEBC97B5F7DDB8E5F` (`_parent_id`),
  KEY `FKEBC97B5F9DE3DFA4` (`_site_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_project_categories`
--

/*!40000 ALTER TABLE `osf_project_categories` DISABLE KEYS */;
LOCK TABLES `osf_project_categories` WRITE;
INSERT INTO `connect`.`osf_project_categories` VALUES  (1,'people',0x01,'成员',5,0x00,1,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_project_categories` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_project_features`
--

DROP TABLE IF EXISTS `connect`.`osf_project_features`;
CREATE TABLE  `connect`.`osf_project_features` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_label` varchar(255) DEFAULT NULL,
  `_level` int(11) DEFAULT NULL,
  `_show` bit(1) DEFAULT NULL,
  `_project_id` bigint(20) NOT NULL,
  `_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKBBB18E4072C99D44` (`_role_id`),
  KEY `FKBBB18E40ADE2AB90` (`_project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_project_features`
--

/*!40000 ALTER TABLE `osf_project_features` DISABLE KEYS */;
LOCK TABLES `osf_project_features` WRITE;
INSERT INTO `connect`.`osf_project_features` VALUES  (1,'profile','个人主页',0,0x01,1,3),
 (2,'knowledge','问答',1,0x01,1,3),
 (3,'calendar','日历',2,0x01,1,2),
 (4,'blog','博客',3,0x01,1,3),
 (5,'gallery','相册',4,0x01,1,3),
 (6,'discussion','讨论',5,0x01,1,3),
 (7,'document','文档',6,0x01,1,2),
 (8,'team','好友',7,0x01,1,3),
 (9,'message','消息',8,0x01,1,1),
 (10,'admin','管理',9,0x01,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_project_features` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_projects`
--

DROP TABLE IF EXISTS `connect`.`osf_projects`;
CREATE TABLE  `connect`.`osf_projects` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime NOT NULL,
  `_publish` bit(1) DEFAULT NULL,
  `_title` varchar(255) NOT NULL,
  `_unique_id` varchar(255) NOT NULL,
  `_category_id` bigint(20) NOT NULL,
  `_entered_by_id` bigint(20) DEFAULT NULL,
  `_modified_by_id` bigint(20) DEFAULT NULL,
  `_sub_category1_id` bigint(20) DEFAULT NULL,
  `_sub_category2_id` bigint(20) DEFAULT NULL,
  `_sub_category3_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_unique_id` (`_unique_id`),
  KEY `FK57E45D17C2E9DEF3` (`_sub_category1_id`),
  KEY `FK57E45D1791C8ED4B` (`_category_id`),
  KEY `FK57E45D17C2EA5352` (`_sub_category2_id`),
  KEY `FK57E45D17FA11FB90` (`_entered_by_id`),
  KEY `FK57E45D17C2EAC7B1` (`_sub_category3_id`),
  KEY `FK57E45D178DB1780` (`_modified_by_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_projects`
--

/*!40000 ALTER TABLE `osf_projects` DISABLE KEYS */;
LOCK TABLES `osf_projects` WRITE;
INSERT INTO `connect`.`osf_projects` VALUES  (1,0x01,'2011-05-31 15:35:02','2011-05-31 15:35:02',0x01,'Gavin Hu','gavin-hu-1',1,1,1,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_projects` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_questions`
--

DROP TABLE IF EXISTS `connect`.`osf_questions`;
CREATE TABLE  `connect`.`osf_questions` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_title` varchar(255) NOT NULL,
  `_answer_id` bigint(20) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK6FC29050FA11FB90` (`_entered_by_id`),
  KEY `FK6FC29050ADE2AB90` (`_project_id`),
  KEY `FK6FC290508DB1780` (`_modified_by_id`),
  KEY `FK6FC29050DA978141` (`_answer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_questions`
--

/*!40000 ALTER TABLE `osf_questions` DISABLE KEYS */;
LOCK TABLES `osf_questions` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_questions` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_rating`
--

DROP TABLE IF EXISTS `connect`.`osf_rating`;
CREATE TABLE  `connect`.`osf_rating` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entered` datetime NOT NULL,
  `_linked_id` bigint(20) NOT NULL,
  `_rating` int(11) NOT NULL,
  `_target` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK53D3797AFA11FB90` (`_entered_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_rating`
--

/*!40000 ALTER TABLE `osf_rating` DISABLE KEYS */;
LOCK TABLES `osf_rating` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_rating` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_replies`
--

DROP TABLE IF EXISTS `connect`.`osf_replies`;
CREATE TABLE  `connect`.`osf_replies` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` varchar(255) NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime DEFAULT NULL,
  `_subject` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) DEFAULT NULL,
  `_quote_id` bigint(20) DEFAULT NULL,
  `_topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK2D3804CBFA11FB90` (`_entered_by_id`),
  KEY `FK2D3804CB9CC52D89` (`_topic_id`),
  KEY `FK2D3804CB8DB1780` (`_modified_by_id`),
  KEY `FK2D3804CBA14D62D7` (`_quote_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_replies`
--

/*!40000 ALTER TABLE `osf_replies` DISABLE KEYS */;
LOCK TABLES `osf_replies` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_replies` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_resources`
--

DROP TABLE IF EXISTS `connect`.`osf_resources`;
CREATE TABLE  `connect`.`osf_resources` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_description` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_code` (`_code`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_resources`
--

/*!40000 ALTER TABLE `osf_resources` DISABLE KEYS */;
LOCK TABLES `osf_resources` WRITE;
INSERT INTO `connect`.`osf_resources` VALUES  (1,'project-profile-view','',0x01,'Profile模块浏览'),
 (2,'profile-add','',0x01,'主页添加'),
 (3,'profile-edit','',0x01,'主页编辑'),
 (4,'profile-view','',0x01,'主页查看'),
 (5,'project-calendar-view','',0x01,'Calendar模块浏览'),
 (6,'event-add','',0x01,'日历添加'),
 (7,'event-edit','',0x01,'日历编辑'),
 (8,'event-view','',0x01,'日历查看'),
 (9,'project-blog-view','',0x01,'Blog模块浏览'),
 (10,'post-add','',0x01,'博文添加'),
 (11,'post-edit','',0x01,'博文编辑'),
 (12,'post-view','',0x01,'博文查看'),
 (13,'post-category-add','',0x01,'博客分类添加'),
 (14,'post-category-edit','',0x01,'博客分类编辑'),
 (15,'post-category-view','',0x01,'博客分类查看'),
 (16,'project-discussion-view','',0x01,'Discussion模块浏览'),
 (17,'forum-add','',0x01,'讨论版块添加'),
 (18,'forum-edit','',0x01,'讨论版块编辑'),
 (19,'forum-view','',0x01,'讨论版块查看'),
 (20,'topic-add','',0x01,'讨论话题添加'),
 (21,'topic-edit','',0x01,'讨论话题编辑'),
 (22,'topic-view','',0x01,'讨论话题查看'),
 (23,'reply-add','',0x01,'讨论回复添加'),
 (24,'reply-edit','',0x01,'讨论回复编辑'),
 (25,'reply-view','',0x01,'讨论回复查看'),
 (26,'project-document-view','',0x01,'Document模块浏览'),
 (27,'file-edit','',0x01,'文档文件编辑'),
 (28,'file-view','',0x01,'文档文件查看'),
 (29,'file-upload','',0x01,'文档文件下载'),
 (30,'file-download','',0x01,'文档文件下载'),
 (31,'folder-add','',0x01,'文档目录添加'),
 (32,'folder-edit','',0x01,'文档目录编辑'),
 (33,'folder-view','',0x01,'文档目录查看'),
 (34,'project-team-view','',0x00,'Team模块浏览'),
 (35,'member-add','',0x01,'团队成员添加'),
 (36,'member-edit','',0x01,'团队成员编辑'),
 (37,'member-view','',0x01,'团队成员查看'),
 (38,'project-message-view','',0x01,'Message模块浏览'),
 (39,'message-add','',0x01,'消息添加'),
 (40,'message-edit','',0x01,'消息编辑'),
 (41,'message-view','',0x01,'消息查看'),
 (42,'project-admin-view','',0x01,'Admin模块浏览'),
 (43,'activity-add','',0x01,'微薄添加'),
 (44,'activity-edit','',0x01,'微薄编辑'),
 (45,'activity-view','',0x01,'微薄查看'),
 (46,'project-gallery-view','',0x01,'Gallery模块浏览'),
 (47,'album-add','',0x01,'相册添加'),
 (48,'album-edit','',0x01,'相册编辑'),
 (49,'album-view','',0x01,'相册查看'),
 (50,'photo-add','',0x01,'相片上传'),
 (51,'photo-edit','',0x01,'相片编辑'),
 (52,'photo-view','',0x01,'相片查看'),
 (53,'project-knowledge-view','',0x01,'Knowledge模块浏览'),
 (54,'question-add','',0x01,'问题添加'),
 (55,'question-edit','',0x01,'问题编辑'),
 (56,'question-view','',0x01,'问题查看'),
 (57,'answer-add','',0x01,'答案添加'),
 (58,'answer-edit','',0x01,'答案编辑'),
 (59,'answer-view','',0x01,'答案查看');
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_resources` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_roles`
--

DROP TABLE IF EXISTS `connect`.`osf_roles`;
CREATE TABLE  `connect`.`osf_roles` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_description` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_level` int(11) DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKB025D74091C8ED4B` (`_category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_roles`
--

/*!40000 ALTER TABLE `osf_roles` DISABLE KEYS */;
LOCK TABLES `osf_roles` WRITE;
INSERT INTO `connect`.`osf_roles` VALUES  (1,'admin','',0x01,5,'主人',1),
 (2,'friend','',0x01,50,'好友',1),
 (3,'guest','',0x01,100,'游客',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_roles` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_site_links`
--

DROP TABLE IF EXISTS `connect`.`osf_site_links`;
CREATE TABLE  `connect`.`osf_site_links` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) DEFAULT NULL,
  `_href` varchar(255) DEFAULT NULL,
  `_text` varchar(255) DEFAULT NULL,
  `_site_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK8BD9521E9DE3DFA4` (`_site_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_site_links`
--

/*!40000 ALTER TABLE `osf_site_links` DISABLE KEYS */;
LOCK TABLES `osf_site_links` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_site_links` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_sites`
--

DROP TABLE IF EXISTS `connect`.`osf_sites`;
CREATE TABLE  `connect`.`osf_sites` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_context_path` varchar(255) NOT NULL,
  `_copyright` varchar(255) DEFAULT NULL,
  `_description` longtext,
  `_domain` varchar(255) NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_keywords` varchar(255) DEFAULT NULL,
  `_port` int(11) DEFAULT NULL,
  `_ssl` bit(1) DEFAULT NULL,
  `_title` varchar(255) NOT NULL,
  `_mail_settings_id` bigint(20) DEFAULT NULL,
  `_theme_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_domain` (`_domain`),
  KEY `FKB031528FA290D111` (`_mail_settings_id`),
  KEY `FKB031528F387B1310` (`_theme_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_sites`
--

/*!40000 ALTER TABLE `osf_sites` DISABLE KEYS */;
LOCK TABLES `osf_sites` WRITE;
INSERT INTO `connect`.`osf_sites` VALUES  (1,'connect-web','','','localhost',0x01,'',8080,0x00,'Demo',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_sites` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_statistics`
--

DROP TABLE IF EXISTS `connect`.`osf_statistics`;
CREATE TABLE  `connect`.`osf_statistics` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_count` bigint(20) DEFAULT NULL,
  `_entity` varchar(255) DEFAULT NULL,
  `_linked_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK60094280ADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_statistics`
--

/*!40000 ALTER TABLE `osf_statistics` DISABLE KEYS */;
LOCK TABLES `osf_statistics` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_statistics` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_tags`
--

DROP TABLE IF EXISTS `connect`.`osf_tags`;
CREATE TABLE  `connect`.`osf_tags` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entity` varchar(255) NOT NULL,
  `_linked_id` bigint(20) NOT NULL,
  `_name` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_tags`
--

/*!40000 ALTER TABLE `osf_tags` DISABLE KEYS */;
LOCK TABLES `osf_tags` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_tags` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_team_members`
--

DROP TABLE IF EXISTS `connect`.`osf_team_members`;
CREATE TABLE  `connect`.`osf_team_members` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_status` varchar(255) DEFAULT NULL,
  `_project_id` bigint(20) NOT NULL,
  `_role_id` bigint(20) NOT NULL,
  `_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK5D9CB35472C99D44` (`_role_id`),
  KEY `FK5D9CB35417F46124` (`_user_id`),
  KEY `FK5D9CB354ADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_team_members`
--

/*!40000 ALTER TABLE `osf_team_members` DISABLE KEYS */;
LOCK TABLES `osf_team_members` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_team_members` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_templates`
--

DROP TABLE IF EXISTS `connect`.`osf_templates`;
CREATE TABLE  `connect`.`osf_templates` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_content` longtext NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK501C123C91C8ED4B` (`_category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_templates`
--

/*!40000 ALTER TABLE `osf_templates` DISABLE KEYS */;
LOCK TABLES `osf_templates` WRITE;
INSERT INTO `connect`.`osf_templates` VALUES  (1,'people-features','[label=个人主页,code=profile,show=true,role=guest]\r\n[label=问答,code=knowledge,show=true,role=guest]\r\n[label=日历,code=calendar,show=true,role=friend]\r\n[label=博客,code=blog,show=true,role=guest]\r\n[label=相册,code=gallery,show=true,role=guest]\r\n[label=讨论,code=discussion,show=true,role=guest]\r\n[label=文档,code=document,show=true,role=friend]\r\n[label=好友,code=team,show=true,role=guest]\r\n[label=消息,code=message,show=true,role=admin]\r\n[label=管理,code=admin,show=true,role=admin]',0x01,'自定义模块',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_templates` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_themes`
--

DROP TABLE IF EXISTS `connect`.`osf_themes`;
CREATE TABLE  `connect`.`osf_themes` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_layout` varchar(255) DEFAULT NULL,
  `_layout_popup` varchar(255) DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_name` (`_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_themes`
--

/*!40000 ALTER TABLE `osf_themes` DISABLE KEYS */;
LOCK TABLES `osf_themes` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_themes` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_topic_categories`
--

DROP TABLE IF EXISTS `connect`.`osf_topic_categories`;
CREATE TABLE  `connect`.`osf_topic_categories` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_label` varchar(255) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK6CB962C9ADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_topic_categories`
--

/*!40000 ALTER TABLE `osf_topic_categories` DISABLE KEYS */;
LOCK TABLES `osf_topic_categories` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_topic_categories` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_topics`
--

DROP TABLE IF EXISTS `connect`.`osf_topics`;
CREATE TABLE  `connect`.`osf_topics` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` varchar(255) NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime NOT NULL,
  `_question` bit(1) DEFAULT NULL,
  `_subject` varchar(255) NOT NULL,
  `_answer_id` bigint(20) DEFAULT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_forum_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK5800A1018DE2D65A` (`_category_id`),
  KEY `FK5800A101F1C6D049` (`_forum_id`),
  KEY `FK5800A101FA11FB90` (`_entered_by_id`),
  KEY `FK5800A1018DB1780` (`_modified_by_id`),
  KEY `FK5800A101DC808B77` (`_answer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_topics`
--

/*!40000 ALTER TABLE `osf_topics` DISABLE KEYS */;
LOCK TABLES `osf_topics` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_topics` ENABLE KEYS */;


--
-- Definition of table `connect`.`osf_users`
--

DROP TABLE IF EXISTS `connect`.`osf_users`;
CREATE TABLE  `connect`.`osf_users` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_email` varchar(255) NOT NULL,
  `_enabled` bit(1) NOT NULL,
  `_entered` datetime NOT NULL,
  `_last_login` datetime DEFAULT NULL,
  `_locale` varchar(255) DEFAULT NULL,
  `_nickname` varchar(255) NOT NULL,
  `_password` varchar(255) NOT NULL,
  `_timezone` varchar(255) DEFAULT NULL,
  `_token` varchar(255) DEFAULT NULL,
  `_username` varchar(255) NOT NULL,
  `_project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_email` (`_email`),
  UNIQUE KEY `_username` (`_username`),
  KEY `FKB051D68BADE2AB90` (`_project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`osf_users`
--

/*!40000 ALTER TABLE `osf_users` DISABLE KEYS */;
LOCK TABLES `osf_users` WRITE;
INSERT INTO `connect`.`osf_users` VALUES  (1,'gavin.hu@opensourceforce.org',0x01,'2011-05-31 15:35:02','2011-05-31 15:35:02',NULL,'Gavin Hu','123456',NULL,'dbebb70a-bcac-4150-8425-e19c82992e52','gavin.hu@opensourceforce.org',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `osf_users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
