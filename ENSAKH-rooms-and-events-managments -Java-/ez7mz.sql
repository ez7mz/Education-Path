-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 28, 2022 at 01:31 PM
-- Server version: 8.0.29
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ez7mz`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
CREATE TABLE IF NOT EXISTS `admins` (
  `IdAdmin` int NOT NULL,
  `Nom` varchar(20) NOT NULL,
  `Prenom` varchar(20) NOT NULL,
  `Fonction` varchar(20) NOT NULL,
  PRIMARY KEY (`IdAdmin`),
  UNIQUE KEY `IdAdmin` (`IdAdmin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`IdAdmin`, `Nom`, `Prenom`, `Fonction`) VALUES
(1, 'Ad', 'Admin', 'CEO'),
(7, 'ez7mz', 'EZ', 'Owner');

-- --------------------------------------------------------

--
-- Table structure for table `blocks`
--

DROP TABLE IF EXISTS `blocks`;
CREATE TABLE IF NOT EXISTS `blocks` (
  `CodeBlock` varchar(1) NOT NULL,
  `Departement` varchar(20) NOT NULL,
  PRIMARY KEY (`CodeBlock`),
  UNIQUE KEY `CodeBlock` (`CodeBlock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `blocks`
--

INSERT INTO `blocks` (`CodeBlock`, `Departement`) VALUES
('A', 'Genie Electrique'),
('B', 'Resaux et Telecom'),
('C', 'Math Info'),
('D', 'Genie Procedes'),
('E', 'Genie Procedes');

-- --------------------------------------------------------

--
-- Table structure for table `etudiants`
--

DROP TABLE IF EXISTS `etudiants`;
CREATE TABLE IF NOT EXISTS `etudiants` (
  `IdEtudiant` int NOT NULL,
  `Nom` varchar(20) NOT NULL,
  `Prenom` varchar(20) NOT NULL,
  `Age` int NOT NULL,
  `Ville` varchar(20) DEFAULT NULL,
  `BornDate` date NOT NULL,
  `Filiere` varchar(20) NOT NULL,
  PRIMARY KEY (`IdEtudiant`),
  UNIQUE KEY `IdEtudiant` (`IdEtudiant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `etudiants`
--

INSERT INTO `etudiants` (`IdEtudiant`, `Nom`, `Prenom`, `Age`, `Ville`, `BornDate`, `Filiere`) VALUES
(120111, 'Mesrar', 'Hamza', 21, 'Your City', '2000-11-21', 'IID'),
(180222, 'Imariren', 'Amine', 20, 'Casablanca', '2001-09-03', 'IID'),
(180333, 'Mbrouk', 'Rida', 21, 'Kalaat Mgouna', '2000-10-03', 'IRIC'),
(180444, 'Najid', 'Mohhamed', 23, 'Casablanca', '1999-06-07', 'IID'),
(180555, 'Elmazouti', 'Ilias', 22, 'Casablanca', '2000-04-04', 'IID'),
(180666, 'Elfiti', 'Anass', 23, 'Agadire', '1999-03-24', 'GE');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `EventNum` tinyint NOT NULL AUTO_INCREMENT,
  `Sujet` varchar(50) NOT NULL,
  `EventDate` date NOT NULL,
  `IdAdmin` int NOT NULL,
  `IdProf` int NOT NULL,
  `IdEtudiant` int NOT NULL,
  `SalleNum` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`EventNum`),
  UNIQUE KEY `EventNum` (`EventNum`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`EventNum`, `Sujet`, `EventDate`, `IdAdmin`, `IdProf`, `IdEtudiant`, `SalleNum`) VALUES
(15, 'Rentrer universitaire', '2022-09-16', 7, 0, 0, 'E1');

-- --------------------------------------------------------

--
-- Table structure for table `professeurs`
--

DROP TABLE IF EXISTS `professeurs`;
CREATE TABLE IF NOT EXISTS `professeurs` (
  `IdProf` int NOT NULL,
  `Nom` varchar(20) NOT NULL,
  `Prenom` varchar(20) NOT NULL,
  `Departement` varchar(20) NOT NULL,
  PRIMARY KEY (`IdProf`),
  UNIQUE KEY `IdProf` (`IdProf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `professeurs`
--

INSERT INTO `professeurs` (`IdProf`, `Nom`, `Prenom`, `Departement`) VALUES
(111, 'Gherabi', 'Noureddin', 'Math Informatique'),
(222, 'Khalfi', 'Hamza', 'Math Informatique'),
(333, 'Maleh', 'Yassin', 'Resaux et Telecome'),
(444, 'Abid', 'Aziza', 'Genie Procedee'),
(555, 'Maaider', 'Kamal', 'Electrique'),
(666, 'Ghezdali', 'Abdelghani', 'Math Informatique'),
(777, 'Mounir', 'Soufyane', 'Resaux et Telecome');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `ResvNum` int NOT NULL AUTO_INCREMENT,
  `ResvDate` date NOT NULL,
  `IdAdmin` int DEFAULT NULL,
  `IdProf` int DEFAULT NULL,
  `IdEtudiant` int DEFAULT NULL,
  `SalleNum` varchar(2) NOT NULL,
  PRIMARY KEY (`ResvNum`),
  UNIQUE KEY `ResvNum` (`ResvNum`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`ResvNum`, `ResvDate`, `IdAdmin`, `IdProf`, `IdEtudiant`, `SalleNum`) VALUES
(45, '2022-09-16', 7, 0, 0, 'E1'),
(47, '2022-05-11', 0, 1, 0, 'C3'),
(48, '2022-11-15', 0, 333, 0, 'B1'),
(49, '2022-12-02', 0, 0, 180444, 'A2'),
(50, '2022-12-12', 2, 0, 0, 'D1'),
(51, '2022-11-23', 0, 222, 0, 'C1'),
(52, '2022-09-17', 7, 0, 0, 'E1'),
(54, '2022-03-15', 0, 111, 0, 'D2');

-- --------------------------------------------------------

--
-- Table structure for table `salles`
--

DROP TABLE IF EXISTS `salles`;
CREATE TABLE IF NOT EXISTS `salles` (
  `SalleNum` varchar(2) NOT NULL,
  `Capacity` int NOT NULL,
  `Block` varchar(1) NOT NULL,
  PRIMARY KEY (`SalleNum`),
  UNIQUE KEY `SalleNum` (`SalleNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `salles`
--

INSERT INTO `salles` (`SalleNum`, `Capacity`, `Block`) VALUES
('A1', 50, 'A'),
('A2', 65, 'A'),
('A3', 50, 'A'),
('B1', 50, 'B'),
('B2', 75, 'B'),
('B3', 80, 'B'),
('C1', 55, 'C'),
('C2', 65, 'C'),
('C3', 75, 'C'),
('D1', 50, 'D'),
('D2', 50, 'D'),
('D3', 50, 'D'),
('E1', 50, 'E'),
('E2', 50, 'E'),
('E3', 50, 'E');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
