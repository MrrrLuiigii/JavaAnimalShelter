-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Gegenereerd op: 06 nov 2019 om 08:13
-- Serverversie: 5.7.24
-- PHP-versie: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `animalshelter`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `addReservor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addReservor` (IN `pName` VARCHAR(50), OUT `pId` INT(50))  BEGIN

insert into reservor (name) values(pName);
set pId = LAST_INSERT_ID();

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `cat`
--

DROP TABLE IF EXISTS `cat`;
CREATE TABLE IF NOT EXISTS `cat` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `gender` enum('Male','Female') NOT NULL,
  `badhabbits` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `cat`
--

INSERT INTO `cat` (`id`, `name`, `gender`, `badhabbits`) VALUES
(1, 'Doerak', 'Male', 'being lazy'),
(2, 'Moppie', 'Female', 'sneezing'),
(3, 'Rijvel', 'Male', 'Fighting');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `dog`
--

DROP TABLE IF EXISTS `dog`;
CREATE TABLE IF NOT EXISTS `dog` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `gender` enum('Male','Female') NOT NULL,
  `lastwalk` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `needswalk` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `dog`
--

INSERT INTO `dog` (`id`, `name`, `gender`, `lastwalk`, `needswalk`) VALUES
(1, 'Mitch', 'Male', '2019-10-26 16:03:28', 0),
(2, 'Bolt', 'Male', '2019-11-04 10:02:32', 0);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `product`
--

INSERT INTO `product` (`id`, `name`, `price`) VALUES
(1, 'Bone', 10),
(2, 'Collar', 12),
(3, 'Pillow', 60);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `reservor_id` int(11) NOT NULL,
  `animal_id` int(11) NOT NULL,
  `animaltype` enum('Dog','Cat') NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `reservation`
--

INSERT INTO `reservation` (`reservor_id`, `animal_id`, `animaltype`) VALUES
(1, 1, 'Dog'),
(2, 2, 'Dog'),
(3, 3, 'Cat');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `reservor`
--

DROP TABLE IF EXISTS `reservor`;
CREATE TABLE IF NOT EXISTS `reservor` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `reservor`
--

INSERT INTO `reservor` (`id`, `name`) VALUES
(1, 'Nicky'),
(2, 'Marian'),
(3, 'Heaven');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
