CREATE TABLE `adresa` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Qyteti` varchar(50) DEFAULT NULL,
  `Rruga` varchar(100) DEFAULT NULL,
  `Numri` int DEFAULT NULL,
  `NumriPostal` int DEFAULT NULL,
  `LlojiVendbanimit` char(1) DEFAULT NULL,
  `GjeresiaGjeografike` varchar(50) DEFAULT NULL,
  `GjatesiaGjeografike` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);


Create TABLE `qytetari` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `NrPersonal` char(10) DEFAULT NULL,
  `Emri` varchar(50) DEFAULT NULL,
  `EmriBabait` varchar(50) DEFAULT NULL,
  `EmriNenes` varchar(50) DEFAULT NULL,
  `Mbiemri` varchar(50) DEFAULT NULL,
  `Ditelindja` varchar(10) DEFAULT NULL,
  `Email` varchar(150) DEFAULT NULL,
  `NrTelefonit` varchar(20) DEFAULT NULL,
  `Gjinia` varchar(20) DEFAULT NULL,
  `Adresa` int DEFAULT NULL,
  `Created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Adresa` (`Adresa`),
 FOREIGN KEY (`Adresa`) REFERENCES `adresa` (`Id`)
)
