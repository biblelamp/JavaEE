-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.12-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table bookstore.author
DROP TABLE IF EXISTS `author`;
CREATE TABLE IF NOT EXISTS `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_or6k6jmywerxbme223c988bmg` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.author: ~0 rows (approximately)
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` (`id`, `name`) VALUES
	(2, 'Andrew Tanenbaum'),
	(1, 'Bruce Eckel'),
	(4, 'Ian Darwin'),
	(3, 'Robert Lafore'),
	(5, 'Stephen King');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;

-- Dumping structure for table bookstore.book
DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `author_of_book_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmgduq3hpx93l0hjnh5gflagjn` (`author_of_book_id`),
  CONSTRAINT `FKmgduq3hpx93l0hjnh5gflagjn` FOREIGN KEY (`author_of_book_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.book: ~0 rows (approximately)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `name`, `author_of_book_id`) VALUES
	(1, 'Thinking in Java', 1),
	(2, 'Modern Operating System', 2),
	(3, 'Computer Architecture', 2),
	(4, 'Programming in Scala', NULL),
	(5, 'Java Cookbook', 4),
	(6, 'Pet Sematary', 5),
	(7, 'The Shining', 5),
	(8, 'The Green Mile', 5);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Dumping structure for table bookstore.book_genre
DROP TABLE IF EXISTS `book_genre`;
CREATE TABLE IF NOT EXISTS `book_genre` (
  `book_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL,
  KEY `FK8l6ops8exmjrlr89hmfow4mmo` (`genre_id`),
  KEY `FK52evq6pdc5ypanf41bij5u218` (`book_id`),
  CONSTRAINT `FK52evq6pdc5ypanf41bij5u218` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK8l6ops8exmjrlr89hmfow4mmo` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.book_genre: ~0 rows (approximately)
/*!40000 ALTER TABLE `book_genre` DISABLE KEYS */;
INSERT INTO `book_genre` (`book_id`, `genre_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(5, 2),
	(6, 3),
	(7, 3),
	(8, 4);
/*!40000 ALTER TABLE `book_genre` ENABLE KEYS */;

-- Dumping structure for table bookstore.genre
DROP TABLE IF EXISTS `genre`;
CREATE TABLE IF NOT EXISTS `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ctffrbu4484ft8dlsa5vmqdka` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.genre: ~0 rows (approximately)
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` (`id`, `name`) VALUES
	(2, 'Cookbook'),
	(3, 'Horror'),
	(1, 'Technical writing'),
	(4, 'Thriller');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
