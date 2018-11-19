-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2018 at 02:39 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecom`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrateur`
--

CREATE TABLE `administrateur` (
  `idAdmin` int(3) NOT NULL,
  `idUser` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `catalogue`
--

CREATE TABLE `catalogue` (
  `idCatalogue` int(3) NOT NULL,
  `nom_catalogue` varchar(25) NOT NULL,
  `date_catalogue` date NOT NULL,
  `img` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `catalogue`
--

INSERT INTO `catalogue` (`idCatalogue`, `nom_catalogue`, `date_catalogue`, `img`) VALUES
(1, 'peripheriques', '2018-02-12', 'https://www.meshcomputers.com/img/home/home-office-500x500.png'),
(2, 'Jouets, Enfants et Bebes', '2018-03-08', 'https://www.harrisonsdirect.co.uk/media/catalog/category/clearance_-_toys_and_gifts_-_category_image.png'),
(3, 'epicerie', '2018-03-05', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQacG-wngN6c-IuepR3ixyN2F5ryoj-u3aJpPjDppdDcV70SvXd'),
(4, 'Vetements', '2018-03-01', 'http://club.jan.com.pk/wp-content/uploads/2017/09/JAN-DAR-SPECIAL-.png');

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id_cat` int(3) NOT NULL,
  `taux_reduction` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `idClient` int(3) NOT NULL,
  `idUser` int(3) DEFAULT NULL,
  `date_naissance` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`idClient`, `idUser`, `date_naissance`) VALUES
(2, 23, '2012-12-12'),
(3, 35, '2018-02-11'),
(4, 37, '2018-02-08'),
(5, 38, '2016-03-02'),
(6, 8, '2012-12-12');

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `idCommande` int(3) NOT NULL,
  `adresse_livraison` varchar(50) NOT NULL,
  `date_commande` date NOT NULL,
  `idLivreur` int(3) DEFAULT NULL,
  `idClient` int(3) DEFAULT NULL,
  `Etat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`idCommande`, `adresse_livraison`, `date_commande`, `idLivreur`, `idClient`, `Etat`) VALUES
(2, 'adr', '2018-03-12', 1, 2, 1),
(3, 'adr', '2018-03-12', 1, 2, 1),
(4, 'adr', '2018-03-12', 1, 2, 0),
(5, 'adr', '2018-03-12', 1, 2, 0),
(6, 'adr', '2018-03-12', 1, 2, 0),
(7, 'adr', '2018-03-18', 1, 2, 0),
(8, 'adr', '2018-03-18', 1, 2, 0),
(9, 'adr', '2018-03-18', 1, 2, 0),
(10, 'adr', '2018-03-18', 1, 2, 0),
(11, 'adr', '2018-03-07', NULL, 2, 0),
(13, 'adr', '2018-03-19', 1, 2, 0),
(14, 'adr', '2018-04-29', NULL, 6, 0),
(15, 'aaa', '2018-05-01', NULL, 3, 0),
(16, 'adr aaax', '2018-05-01', NULL, 3, 0),
(17, 'mohammed roudani', '2018-05-01', NULL, 6, 0),
(18, 'saidi', '2018-05-01', NULL, 6, 0);

-- --------------------------------------------------------

--
-- Table structure for table `evaluer`
--

CREATE TABLE `evaluer` (
  `description` varchar(50) NOT NULL,
  `rating` double NOT NULL,
  `idClient` int(3) DEFAULT NULL,
  `idLivreur` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lignecommande`
--

CREATE TABLE `lignecommande` (
  `idLcmd` int(11) NOT NULL,
  `idCommande` int(3) NOT NULL,
  `idProduit` int(3) NOT NULL,
  `Qte_cmd` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lignecommande`
--

INSERT INTO `lignecommande` (`idLcmd`, `idCommande`, `idProduit`, `Qte_cmd`) VALUES
(2, 13, 3, 3),
(6, 13, 3, 6),
(7, 13, 1, 9),
(8, 13, 2, 6),
(9, 13, 1, 7),
(10, 14, 1, 7),
(11, 14, 2, 11),
(12, 15, 1, 7),
(13, 15, 2, 11),
(14, 16, 2, 5),
(15, 17, 1, 7),
(16, 17, 2, 11),
(17, 18, 1, 7),
(18, 18, 2, 11);

-- --------------------------------------------------------

--
-- Table structure for table `livreur`
--

CREATE TABLE `livreur` (
  `idLivreur` int(3) NOT NULL,
  `idUser` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `livreur`
--

INSERT INTO `livreur` (`idLivreur`, `idUser`) VALUES
(1, 36),
(2, 39);

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `idProduit` int(3) NOT NULL,
  `nom_produit` varchar(25) NOT NULL,
  `type_produit` varchar(25) NOT NULL,
  `prix` double NOT NULL,
  `idCatalogue` int(3) DEFAULT NULL,
  `img` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`idProduit`, `nom_produit`, `type_produit`, `prix`, `idCatalogue`, `img`) VALUES
(1, 'souris', 'peripheriques', 50, 1, 'https://5.imimg.com/data5/BY/BL/GLADMIN-28453056/intex-mouse-optical-fast-usb-500x500.png'),
(2, 'clavier', 'peripheriques', 30, 1, 'https://lilliputdirect.com/image/cache/catalog/Products/Pi%20Stuff/kbandmouse-500x500.png'),
(3, 'ecran', 'peripheriques', 250, 1, 'https://www.windowscentral.com/sites/wpcentral.com/files/styles/large/public/field/image/2017/10/acer-g257hl-best.png?itok=iWYsWam9'),
(4, 'Chemise', 'Vetements', 500, 4, 'http://www.clubmonaco.ca/graphics/product_images/pCMCA1-26250753_alternate2_dt.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `idUser` int(3) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `email` varchar(30) NOT NULL,
  `motdepass` varchar(32) NOT NULL,
  `login` varchar(25) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `telephone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`idUser`, `nom`, `prenom`, `email`, `motdepass`, `login`, `adresse`, `telephone`) VALUES
(1, 'kamali', 'mounir', 'kamali@gmail.com', '1A1DC91C907325C69271DDF0C944BC72', 'kamali', 'Rue Al Irfan ensias Rabat', '065321278'),
(2, 'malouki', 'said', 'malouki@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'maloukis', 'Rue Hassan II Casablanca', '0699887755'),
(4, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(5, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(6, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(7, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(8, 'chakir', 'ayoub', 'ayoub.chakir@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'chakir', 'Rue al IRFAN RABAT', '0655112233'),
(9, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(10, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(11, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(12, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(13, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(14, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(15, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(16, 'mourabit', 'amin', 'moura.am@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'mourabit', 'rue nassim rabat', '0684576233'),
(17, 'karimo', 'momo', 'm.momo@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'momo', 'adr adre', '0655223344'),
(18, 'toto', 'tata', 'toto.tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'toto', 'rue hassan', '0644778899'),
(19, 'toto', 'tata', 'toto.tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'toto', 'rue hassan', '0644778899'),
(20, 'toto', 'tata', 'toto.tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'toto', 'rue hassan', '0644778899'),
(21, 'toto', 'tata', 'toto.tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'toto', 'rue hassan', '0644778899'),
(22, 'test', 'test', 'test@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'test', 'adr', '0655888223'),
(23, 'toto', 'tata', 'toto.tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'toto', 'rue hassan', '0644778899'),
(24, 'OJ', 'OJ', 'OJ@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'OJ', 'adr', '0655448877'),
(25, 'tata', 'tata', 'tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'tata', 'adr', '0655223311'),
(26, 'tata', 'tata', 'tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'tata', 'adr', '0655223311'),
(27, 'tata', 'tato', 'tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'tata', 'adr', '0655223311'),
(28, 'tata', 'tato', 'tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'tata', 'adr', '0655223311'),
(29, 'tata', 'tato', 'tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'tata', 'adr', '0655223311'),
(30, 'tata', 'tato', 'tata@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'tata', 'adr', '0655223311'),
(31, 'koko', 'koko', 'koko@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'koko', 'adr adr', '0655889912'),
(32, 'hhh', 'hh', 'h.m@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'hh', 'here', '0655883311'),
(33, 'hhh', 'hh', 'h.m@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'hh', 'here', '0655883311'),
(34, 'hhh', 'hh', 'h.m@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'hh', 'here', '0655883311'),
(35, 'yyy', 'yyy', 'yy@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'yy', 'yy', '0655774422'),
(36, 'saidi', 'ayoub', 'saidi@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'saidiayoub', 'ADR RUE MH', '0655882233'),
(37, 'Ahmadi', 'saad', 'ahmadi@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'ahm', 'adr adr', '0658851278'),
(38, 'xyxy', 'xyxy', 'xyxy@gmail.com', '3f6f55ba521257932bb08684e0181fa1', 'xyxy', 'xyxy', '06050402'),
(39, 'chaoki', 'ahmed', 'chaokiahmed@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'chaoki', 'adr chaowwki', '0655227766');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`idAdmin`),
  ADD KEY `FK_ADMIN_USER` (`idUser`);

--
-- Indexes for table `catalogue`
--
ALTER TABLE `catalogue`
  ADD PRIMARY KEY (`idCatalogue`);

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_cat`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `FK_CLIENT_USER` (`idUser`);

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`idCommande`),
  ADD KEY `FK_COMMANDE_CLIENT` (`idClient`),
  ADD KEY `FK_COMMANDE_LIVREUR` (`idLivreur`);

--
-- Indexes for table `evaluer`
--
ALTER TABLE `evaluer`
  ADD KEY `FK_EVALUER_CLIENT` (`idClient`),
  ADD KEY `FK_EVALUER_Livreur` (`idLivreur`);

--
-- Indexes for table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD PRIMARY KEY (`idLcmd`),
  ADD KEY `FK_LigneCommande_PRODUIT` (`idProduit`),
  ADD KEY `FK_LigneCommande_commande` (`idCommande`);

--
-- Indexes for table `livreur`
--
ALTER TABLE `livreur`
  ADD PRIMARY KEY (`idLivreur`),
  ADD KEY `FK_LIVREUR_USER` (`idUser`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`idProduit`),
  ADD KEY `FK_PRODUIT_CATA` (`idCatalogue`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `idAdmin` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `catalogue`
--
ALTER TABLE `catalogue`
  MODIFY `idCatalogue` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_cat` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `idCommande` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `lignecommande`
--
ALTER TABLE `lignecommande`
  MODIFY `idLcmd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `livreur`
--
ALTER TABLE `livreur`
  MODIFY `idLivreur` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `idProduit` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `idUser` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `FK_ADMIN_USER` FOREIGN KEY (`idUser`) REFERENCES `utilisateur` (`idUser`);

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_CLIENT_USER` FOREIGN KEY (`idUser`) REFERENCES `utilisateur` (`idUser`);

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_COMMANDE_CLIENT` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `FK_COMMANDE_LIVREUR` FOREIGN KEY (`idLivreur`) REFERENCES `livreur` (`idLivreur`);

--
-- Constraints for table `evaluer`
--
ALTER TABLE `evaluer`
  ADD CONSTRAINT `FK_EVALUER_CLIENT` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `FK_EVALUER_Livreur` FOREIGN KEY (`idLivreur`) REFERENCES `livreur` (`idLivreur`);

--
-- Constraints for table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD CONSTRAINT `FK_LigneCommande_PRODUIT` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`),
  ADD CONSTRAINT `FK_LigneCommande_commande` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`idCommande`);

--
-- Constraints for table `livreur`
--
ALTER TABLE `livreur`
  ADD CONSTRAINT `FK_LIVREUR_USER` FOREIGN KEY (`idUser`) REFERENCES `utilisateur` (`idUser`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_PRODUIT_CATA` FOREIGN KEY (`idCatalogue`) REFERENCES `catalogue` (`idCatalogue`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
