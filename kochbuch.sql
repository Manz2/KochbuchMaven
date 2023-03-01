-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Erstellungszeit: 01. Mrz 2023 um 12:18
-- Server-Version: 10.6.4-MariaDB-log
-- PHP-Version: 7.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `kochbuch`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `gericht`
--

CREATE TABLE `gericht` (
  `name` varchar(64) COLLATE latin1_german1_ci NOT NULL,
  `bild` varchar(128) COLLATE latin1_german1_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `gericht`
--

INSERT INTO `gericht` (`name`, `bild`) VALUES
('Carne a la Pizzaiola', 'C:\\Users\\erikm\\Documents\\Projekte\\KochbuchMaven\\src\\main\\resources\\pictures\\IMG_1338.jpg'),
('Huhn süß sauer', 'C:\\Users\\erikm\\Documents\\Projekte\\KochbuchMaven\\src\\main\\resources\\pictures\\huhn.jpg'),
('Krautwickel', 'C:\\Users\\erikm\\Documents\\Projekte\\KochbuchMaven\\src\\main\\resources\\pictures\\IMG_1416.jpg'),
('Penne Arabiata', 'C:\\Users\\erikm\\Documents\\Projekte\\KochbuchMaven\\src\\main\\resources\\pictures\\PenneArabiata.jpg'),
('Tortellini mit Sahnesauce', 'C:\\Users\\erikm\\Documents\\Projekte\\KochbuchMaven\\src\\main\\resources\\pictures\\tortellini.jpg');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hatzutaten`
--

CREATE TABLE `hatzutaten` (
  `name` varchar(64) COLLATE latin1_german1_ci NOT NULL,
  `ZutatenName` varchar(64) COLLATE latin1_german1_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `hatzutaten`
--

INSERT INTO `hatzutaten` (`name`, `ZutatenName`) VALUES
('Carne a la Pizzaiola', 'Baguette'),
('Carne a la Pizzaiola', 'Basilikum'),
('Carne a la Pizzaiola', 'Hähnchenbrust'),
('Carne a la Pizzaiola', 'Mozzarella'),
('Carne a la Pizzaiola', 'Oliven'),
('Carne a la Pizzaiola', 'Olivenöl'),
('Carne a la Pizzaiola', 'Oregano'),
('Carne a la Pizzaiola', 'Passierte Tomaten'),
('Carne a la Pizzaiola', 'Pfeffer'),
('Carne a la Pizzaiola', 'Salz'),
('Carne a la Pizzaiola', 'Tymian'),
('Carne a la Pizzaiola', 'Zwiebeln'),
('Huhn süß sauer', 'Hähnchenfilet'),
('Huhn süß sauer', 'Zwiebeln'),
('Krautwickel', 'Hackfleisch 1kg'),
('Krautwickel', 'Kraut'),
('Penne Arabiata', 'Knoblauch'),
('Penne Arabiata', 'Passierte Tomaten'),
('Penne Arabiata', 'Penne'),
('Penne Arabiata', 'Rauchfleisch'),
('Tortellini mit Sahnesauce', 'Knoblauch'),
('Tortellini mit Sahnesauce', 'Lauch'),
('Tortellini mit Sahnesauce', 'Sahne'),
('Tortellini mit Sahnesauce', 'Schinken'),
('Tortellini mit Sahnesauce', 'Tortellini'),
('Tortellini mit Sahnesauce', 'Zwiebeln');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `zutaten`
--

CREATE TABLE `zutaten` (
  `ZutatenName` varchar(64) COLLATE latin1_german1_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `zutaten`
--

INSERT INTO `zutaten` (`ZutatenName`) VALUES
('Baguette'),
('Basilikum'),
('Bier'),
('Hackfleisch'),
('Hackfleisch 1kg'),
('Hähnchenbrust'),
('Hähnchenfilet'),
('Kartoffeln'),
('Knoblauch'),
('Kraut'),
('Kraut,Hackfleisch'),
('Lauch'),
('Marmelade'),
('Mozzarella'),
('Oliven'),
('Olivenöl'),
('Oregano'),
('Passierte Tomaten'),
('Penne'),
('Pfeffer'),
('Rauchfleisch'),
('Sahne'),
('Salz'),
('Schinken'),
('Schokolade'),
('Schweinebraten'),
('Tortellini'),
('Tymian'),
('Zwiebeln');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `gericht`
--
ALTER TABLE `gericht`
  ADD PRIMARY KEY (`name`);

--
-- Indizes für die Tabelle `hatzutaten`
--
ALTER TABLE `hatzutaten`
  ADD PRIMARY KEY (`name`,`ZutatenName`),
  ADD KEY `hatZutaten_fk2` (`ZutatenName`);

--
-- Indizes für die Tabelle `zutaten`
--
ALTER TABLE `zutaten`
  ADD PRIMARY KEY (`ZutatenName`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `hatzutaten`
--
ALTER TABLE `hatzutaten`
  ADD CONSTRAINT `hatZutaten_fk1` FOREIGN KEY (`name`) REFERENCES `gericht` (`name`),
  ADD CONSTRAINT `hatZutaten_fk2` FOREIGN KEY (`ZutatenName`) REFERENCES `zutaten` (`ZutatenName`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
