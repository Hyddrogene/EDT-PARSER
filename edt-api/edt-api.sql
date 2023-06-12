-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 12, 2023 at 01:32 PM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `edt-api`
--

-- --------------------------------------------------------

--
-- Table structure for table `etapes`
--

DROP TABLE IF EXISTS `etapes`;
CREATE TABLE IF NOT EXISTS `etapes` (
  `etape_id` varchar(25) NOT NULL,
  PRIMARY KEY (`etape_id`),
  UNIQUE KEY `etape_id` (`etape_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `etapes`
--

INSERT INTO `etapes` (`etape_id`) VALUES
('TL1IMA1'),
('TL1IMA11'),
('TL1MAI1'),
('TL1MAI11'),
('TL1MIP1'),
('TL1MIP2'),
('TL1MP12'),
('TL1MP13'),
('TL1MP14'),
('TL2BI11'),
('TL2BIS11'),
('TL2GE11'),
('TL2GES11'),
('TL2IMA11'),
('TL2IN11'),
('TL2INS11'),
('TL2MA11'),
('TL2MA13'),
('TL2MAI11'),
('TL2MAS11'),
('TL2ME11'),
('TL2MIP1'),
('TL2MIP2'),
('TL2MP12'),
('TL2MP13'),
('TL2PC11'),
('TL2PC12'),
('TL2PC13'),
('TL2PC14'),
('TL2PCS11'),
('TL2PP11'),
('TL2SV12'),
('TL2SV13'),
('TL3BC11'),
('TL3BCS11'),
('TL3BO11'),
('TL3BOS11'),
('TL3BV11'),
('TL3BV12'),
('TL3BVS11'),
('TL3CE11'),
('TL3CE12'),
('TL3CES11'),
('TL3CM11'),
('TL3CMS11'),
('TL3GE11'),
('TL3GES11'),
('TL3IMA11'),
('TL3IN11'),
('TL3INS11'),
('TL3LM11'),
('TL3LMS11'),
('TL3MAA11'),
('TL3MAI11'),
('TL3MAS11'),
('TL3MAT11'),
('TL3MAT13'),
('TL3ME11'),
('TL3MF11'),
('TL3MFS11'),
('TL3MTS11'),
('TL3PA11'),
('TL3PA12'),
('TL3PAS11'),
('TL3PP11'),
('TM1BS3'),
('TM1BV3'),
('TM1BV4'),
('TM1DBN3'),
('TM1GBI1'),
('TM1INF3'),
('TM1LUM3'),
('TM1MAD1'),
('TM1MFA3'),
('TM1PPP1'),
('TM1PSI3'),
('TM1PSI4'),
('TM1SIE3'),
('TM1THE1'),
('TM2CD1'),
('TM2DBN3'),
('TM2IA1'),
('TM2LUM3'),
('TM2NCM3'),
('TM2PPP1'),
('TM2PSI3'),
('TM2PSI4'),
('TM2SIE3'),
('TM2THE1');

-- --------------------------------------------------------

--
-- Table structure for table `etape_calendars`
--

DROP TABLE IF EXISTS `etape_calendars`;
CREATE TABLE IF NOT EXISTS `etape_calendars` (
  `etape_id` varchar(25) NOT NULL,
  `start` int NOT NULL,
  `end` int NOT NULL,
  PRIMARY KEY (`etape_id`),
  KEY `etape_id` (`etape_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `etape_calendars`
--

INSERT INTO `etape_calendars` (`etape_id`, `start`, `end`) VALUES
('TL1IMA1', 36, 75),
('TL1IMA11', 36, 75),
('TL1MAI1', 36, 75),
('TL1MAI11', 36, 75),
('TL1MIP1', 36, 75),
('TL1MIP2', 36, 75),
('TL1MP12', 36, 75),
('TL1MP13', 36, 75),
('TL1MP14', 36, 75),
('TL2BI11', 36, 75),
('TL2BIS11', 36, 75),
('TL2GE11', 36, 75),
('TL2GES11', 36, 75),
('TL2IMA11', 37, 75),
('TL2IN11', 37, 75),
('TL2INS11', 37, 75),
('TL2MA11', 37, 75),
('TL2MA13', 37, 75),
('TL2MAI11', 37, 75),
('TL2MAS11', 37, 75),
('TL2ME11', 37, 75),
('TL2MIP1', 37, 75),
('TL2MIP2', 37, 75),
('TL2MP12', 37, 75),
('TL2MP13', 37, 75),
('TL2PC11', 37, 75),
('TL2PC12', 37, 75),
('TL2PC13', 37, 75),
('TL2PC14', 37, 75),
('TL2PCS11', 37, 75),
('TL2PP11', 36, 75),
('TL2SV12', 36, 75),
('TL2SV13', 36, 75),
('TL3BC11', 36, 75),
('TL3BCS11', 36, 75),
('TL3BO11', 36, 75),
('TL3BOS11', 36, 75),
('TL3BV11', 36, 75),
('TL3BV12', 36, 75),
('TL3BVS11', 36, 75),
('TL3CE11', 36, 75),
('TL3CE12', 36, 75),
('TL3CES11', 36, 75),
('TL3CM11', 36, 75),
('TL3CMS11', 36, 75),
('TL3GE11', 36, 75),
('TL3GES11', 36, 75),
('TL3IMA11', 36, 75),
('TL3IN11', 36, 75),
('TL3INS11', 36, 75),
('TL3LM11', 36, 75),
('TL3LMS11', 36, 75),
('TL3MAA11', 36, 75),
('TL3MAI11', 36, 75),
('TL3MAS11', 36, 75),
('TL3MAT11', 36, 75),
('TL3MAT13', 36, 75),
('TL3ME11', 36, 75),
('TL3MF11', 36, 75),
('TL3MFS11', 36, 75),
('TL3MTS11', 36, 75),
('TL3PA11', 36, 75),
('TL3PA12', 36, 75),
('TL3PAS11', 36, 75),
('TL3PP11', 36, 75),
('TM1BS3', 36, 75),
('TM1BV3', 36, 75),
('TM1BV4', 35, 75),
('TM1DBN3', 36, 75),
('TM1GBI1', 36, 75),
('TM1INF3', 36, 75),
('TM1LUM3', 36, 75),
('TM1MAD1', 36, 75),
('TM1MFA3', 36, 75),
('TM1PPP1', 36, 75),
('TM1PSI3', 36, 75),
('TM1PSI4', 36, 75),
('TM1SIE3', 36, 75),
('TM1THE1', 36, 75),
('TM2CD1', 36, 75),
('TM2DBN3', 36, 75),
('TM2IA1', 36, 75),
('TM2LUM3', 36, 75),
('TM2NCM3', 36, 75),
('TM2PPP1', 36, 75),
('TM2PSI3', 36, 75),
('TM2PSI4', 37, 75),
('TM2SIE3', 36, 75),
('TM2THE1', 36, 75);

-- --------------------------------------------------------

--
-- Table structure for table `student_counts`
--

DROP TABLE IF EXISTS `student_counts`;
CREATE TABLE IF NOT EXISTS `student_counts` (
  `etape_id` varchar(25) NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`etape_id`),
  KEY `etape_id` (`etape_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `student_counts`
--

INSERT INTO `student_counts` (`etape_id`, `count`) VALUES
('TL1IMA1', 0),
('TL1IMA11', 0),
('TL1MAI1', 0),
('TL1MAI11', 0),
('TL1MIP1', 0),
('TL1MIP2', 0),
('TL1MP12', 0),
('TL1MP13', 0),
('TL1MP14', 0),
('TL2BI11', 0),
('TL2BIS11', 0),
('TL2GE11', 0),
('TL2GES11', 0),
('TL2IMA11', 0),
('TL2IN11', 0),
('TL2INS11', 0),
('TL2MA11', 0),
('TL2MA13', 0),
('TL2MAI11', 0),
('TL2MAS11', 0),
('TL2ME11', 0),
('TL2MIP1', 0),
('TL2MIP2', 0),
('TL2MP12', 0),
('TL2MP13', 0),
('TL2PC11', 0),
('TL2PC12', 0),
('TL2PC13', 0),
('TL2PC14', 0),
('TL2PCS11', 0),
('TL2PP11', 0),
('TL2SV12', 0),
('TL2SV13', 0),
('TL3BC11', 0),
('TL3BCS11', 0),
('TL3BO11', 0),
('TL3BOS11', 0),
('TL3BV11', 0),
('TL3BV12', 0),
('TL3BVS11', 0),
('TL3CE11', 0),
('TL3CE12', 0),
('TL3CES11', 0),
('TL3CM11', 0),
('TL3CMS11', 0),
('TL3GE11', 0),
('TL3GES11', 0),
('TL3IMA11', 0),
('TL3IN11', 0),
('TL3INS11', 0),
('TL3LM11', 0),
('TL3LMS11', 0),
('TL3MAA11', 0),
('TL3MAI11', 0),
('TL3MAS11', 0),
('TL3MAT11', 0),
('TL3MAT13', 0),
('TL3ME11', 0),
('TL3MF11', 0),
('TL3MFS11', 0),
('TL3MTS11', 0),
('TL3PA11', 0),
('TL3PA12', 0),
('TL3PAS11', 0),
('TL3PP11', 0),
('TM1BS3', 0),
('TM1BV3', 0),
('TM1BV4', 0),
('TM1DBN3', 0),
('TM1GBI1', 0),
('TM1INF3', 0),
('TM1LUM3', 0),
('TM1MAD1', 0),
('TM1MFA3', 0),
('TM1PPP1', 0),
('TM1PSI3', 0),
('TM1PSI4', 0),
('TM1SIE3', 0),
('TM1THE1', 0),
('TM2CD1', 0),
('TM2DBN3', 0),
('TM2IA1', 0),
('TM2LUM3', 0),
('TM2NCM3', 0),
('TM2PPP1', 0),
('TM2PSI3', 0),
('TM2PSI4', 0),
('TM2SIE3', 0),
('TM2THE1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
CREATE TABLE IF NOT EXISTS `teachers` (
  `teacher_uuid` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `affectation` varchar(255) NOT NULL,
  `libelleServiceAffectation` varchar(255) NOT NULL,
  `typeEnseignant` char(1) NOT NULL,
  `volumeHoraire` float NOT NULL,
  `volumeHoraireAnnee` varchar(4) NOT NULL,
  PRIMARY KEY (`teacher_uuid`),
  KEY `teacher_uuid` (`teacher_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`teacher_uuid`, `nom`, `prenom`, `affectation`, `libelleServiceAffectation`, `typeEnseignant`, `volumeHoraire`, `volumeHoraireAnnee`) VALUES
('0001', 'Teacher', '1', 'UFR SCIENCES', 'AESEIN', 'E', 192, '2022'),
('0002', 'Teacher', '2', 'UFR SCIENCES', 'AESEIN', 'E', 192, '2022'),
('0003', 'Teacher', '3', 'UFR SCIENCES', 'AESEPH', 'E', 192, '2022'),
('0004', 'Teacher', '4', 'UFR SCIENCES', 'AESEPH', 'E', 192, '2022'),
('0005', 'Teacher', '5', 'UFR SCIENCES', 'AESEMA', 'E', 192, '2022'),
('0006', 'Teacher', '6', 'UFR SCIENCES', 'AESEMA', 'E', 192, '2022'),
('0007', 'Teacher', '7', 'UFR SCIENCES', 'AESEGE', 'E', 192, '2022'),
('0008', 'Teacher', '8', 'UFR SCIENCES', 'AESEGE', 'E', 192, '2022'),
('0009', 'Teacher', '9', 'UFR SCIENCES', 'AESECH', 'E', 192, '2022'),
('0010', 'Teacher', '10', 'UFR SCIENCES', 'AESECH', 'E', 192, '2022');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_service_sheet`
--

DROP TABLE IF EXISTS `teacher_service_sheet`;
CREATE TABLE IF NOT EXISTS `teacher_service_sheet` (
  `course_id` varchar(25) NOT NULL,
  `type` enum('CM','TD','TP','CMTD') NOT NULL,
  `teacher_uuid` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `hours` float NOT NULL,
  PRIMARY KEY (`course_id`,`type`,`teacher_uuid`),
  KEY `teacher_uuid` (`teacher_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `teacher_service_sheet`
--

INSERT INTO `teacher_service_sheet` (`course_id`, `type`, `teacher_uuid`, `hours`) VALUES
('TL2PC11', 'CM', '0003', 8),
('TL2PC11', 'TD', '0003', 8),
('TL2PC11', 'TD', '0004', 8),
('TMX1105U', 'CMTD', '0001', 32),
('TMX1105U', 'CMTD', '0002', 48),
('TMX2301V', 'CM', '0005', 8),
('TMX2301V', 'TD', '0005', 12),
('TMX2301V', 'TD', '0006', 12),
('TSV2207U', 'CM', '0007', 13.3),
('TSV2207U', 'TD', '0008', 6.7),
('TSV2409U', 'CM', '0009', 6.7),
('TSV2409U', 'TD', '0010', 6.7),
('TSV2409U', 'TP', '0009', 3),
('TSV2409U', 'TP', '0010', 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `etape_calendars`
--
ALTER TABLE `etape_calendars`
  ADD CONSTRAINT `etape_calendars_etape_id` FOREIGN KEY (`etape_id`) REFERENCES `etapes` (`etape_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student_counts`
--
ALTER TABLE `student_counts`
  ADD CONSTRAINT `student_counts_etape_id` FOREIGN KEY (`etape_id`) REFERENCES `etapes` (`etape_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teacher_service_sheet`
--
ALTER TABLE `teacher_service_sheet`
  ADD CONSTRAINT `teacher_service_sheet_teacher_uuid` FOREIGN KEY (`teacher_uuid`) REFERENCES `teachers` (`teacher_uuid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
