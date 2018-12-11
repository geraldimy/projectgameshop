-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2018 at 03:20 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gameshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `banner`
--

CREATE TABLE `banner` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Link` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `banner`
--

INSERT INTO `banner` (`ID`, `Name`, `Link`) VALUES
(1, 'Sports', 'https://i.ibb.co/WyGScMM/pes-2019-demo.jpg\r\n'),
(2, 'Action', 'https://i.ibb.co/BsHNBWp/baner7.jpg'),
(3, 'Racing', 'https://i.ibb.co/vwhRKBF/baner6.jpg'),
(4, 'RPG', 'https://i.ibb.co/7X6yx3p/baner11.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Link` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ID`, `Name`, `Link`) VALUES
(1, 'Action', 'https://i.ibb.co/K06Vz5H/assassins-creed-odyssey-product-tile-01-ps4-us-12jun18.jpg'),
(2, 'Racing', 'https://i.ibb.co/0XzLBRP/download-2.jpg'),
(3, 'RPG', 'https://i.ibb.co/93JycPv/16709202b0b76f1efb9dca21.jpg'),
(4, 'Sports', 'https://i.ibb.co/gZMkFdH/image.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Link` text NOT NULL,
  `Price` float NOT NULL,
  `CategoryId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`ID`, `Name`, `Link`, `Price`, `CategoryId`) VALUES
(1, 'Assasins Creed Oddsey', 'https://i.ibb.co/K06Vz5H/assassins-creed-odyssey-product-tile-01-ps4-us-12jun18.jpg', 50, 1),
(2, 'Hitman 2', 'https://i.ibb.co/FBYvFX6/packshot-04d2b3223179468a1932670a7b7ed212.jpg', 50, 1),
(3, 'Tomb Raider 3', 'https://i.ibb.co/16LhrPs/Shadow-of-the-Tomb-Raider-capa.png', 40, 1),
(4, 'Need For Speed Payback', 'https://i.ibb.co/L94Kq68/download-1.jpg', 35, 2),
(5, 'Forza Horizon 4', 'https://i.ibb.co/0XzLBRP/download-2.jpg', 40, 2),
(7, 'Final Fantasy XV', 'https://i.ibb.co/93JycPv/16709202b0b76f1efb9dca21.jpg', 30, 3),
(8, 'FIFA 19', 'https://i.ibb.co/gZMkFdH/image.jpg', 50, 4),
(9, 'PES 19', 'https://i.ibb.co/882MSMD/503164-pes-2019-pro-evolution-soccer-xbox-one-front-cover.jpg', 45, 4),
(10, 'Skyrim', 'https://i.ibb.co/8dXsGCN/376858-the-elder-scrolls-v-skyrim-special-edition-xbox-one-front-cover.jpg', 25, 3),
(11, 'GTA V', 'https://i.ibb.co/fnprtHs/hDmLfQu.jpg', 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `OrderId` bigint(20) NOT NULL,
  `OrderStatus` tinyint(4) NOT NULL,
  `OrderPrice` float NOT NULL,
  `OrderDetail` text NOT NULL,
  `OrderComment` text NOT NULL,
  `OrderAddress` text NOT NULL,
  `UserPhone` text NOT NULL,
  `PaymentMethod` varchar(11) NOT NULL DEFAULT 'Braintree'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`OrderId`, `OrderStatus`, `OrderPrice`, `OrderDetail`, `OrderComment`, `OrderAddress`, `UserPhone`, `PaymentMethod`) VALUES
(15, -1, 80, '[{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/K06Vz5H/assassins-creed-odyssey-product-tile-01-ps4-us-12jun18.jpg\",\"name\":\"Assasins Creed Oddsey\",\"platf\":\"XBOX\",\"price\":80.0,\"amount\":1,\"id\":13}]', 'Go Fast', 'UI', '+6285718223690', 'Braintree'),
(17, -1, 65, '[{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/L94Kq68/download-1.jpg\",\"name\":\"Need For Speed Payback\",\"platf\":\"PC\",\"price\":65.0,\"amount\":1,\"id\":15}]', 'ghi', 'Griya Depok Asri Blok G4 No 3', '+6285718223690', 'Braintree'),
(18, -1, 65, '[{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/L94Kq68/download-1.jpg\",\"name\":\"Need For Speed Payback\",\"platf\":\"PC\",\"price\":65.0,\"amount\":1,\"id\":17}]', '', 'Griya Depok Asri Blok G4 No 3', '+6285718223690', 'Braintree'),
(19, -1, 353, '[{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/K06Vz5H/assassins-creed-odyssey-product-tile-01-ps4-us-12jun18.jpg\",\"name\":\"Assasins Creed Oddsey\",\"platf\":\"PS4\",\"price\":80.0,\"amount\":1,\"id\":19},{\"edition\":\"Standard\",\"link\":\"https://i.ibb.co/K06Vz5H/assassins-creed-odyssey-product-tile-01-ps4-us-12jun18.jpg\",\"name\":\"Assasins Creed Oddsey\",\"platf\":\"PC\",\"price\":50.0,\"amount\":1,\"id\":20},{\"edition\":\"Gold\",\"link\":\"https://i.ibb.co/K06Vz5H/assassins-creed-odyssey-product-tile-01-ps4-us-12jun18.jpg\",\"name\":\"Assasins Creed Oddsey\",\"platf\":\"PC\",\"price\":63.0,\"amount\":1,\"id\":21},{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/K06Vz5H/assassins-creed-odyssey-product-tile-01-ps4-us-12jun18.jpg\",\"name\":\"Assasins Creed Oddsey\",\"platf\":\"PC\",\"price\":160.0,\"amount\":2,\"id\":22}]', '', 'Griya Depok Asri Blok G4 No 3', '+6285718223690', 'COD'),
(20, 0, 65, '[{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/L94Kq68/download-1.jpg\",\"name\":\"Need For Speed Payback\",\"platf\":\"PC\",\"price\":65.0,\"amount\":1,\"id\":23}]', '', 'Griya Depok Asri Blok G4 No 3', '+6285718223690', 'Braintree'),
(21, -1, 225, '[{\"amount\":3,\"edition\":\"Premium\",\"id\":1,\"link\":\"https://i.ibb.co/882MSMD/503164-pes-2019-pro-evolution-soccer-xbox-one-front-cover.jpg\",\"name\":\"PES 19\",\"platf\":\"PC\",\"price\":225.0}]', '', '1999-10-21', '+6285714735100', 'Braintree'),
(22, 0, 195, '[{\"amount\":3,\"edition\":\"Premium\",\"id\":2,\"link\":\"https://i.ibb.co/L94Kq68/download-1.jpg\",\"name\":\"Need For Speed Payback\",\"platf\":\"XBOX\",\"price\":195.0}]', 'Keren', '1999-10-21', '+6285714735100', 'COD'),
(23, 0, 110, '[{\"amount\":1,\"edition\":\"Premium\",\"id\":3,\"link\":\"https://i.ibb.co/0XzLBRP/download-2.jpg\",\"name\":\"Forza Horizon 4\",\"platf\":\"PS4\",\"price\":70.0},{\"amount\":1,\"edition\":\"Standard\",\"id\":4,\"link\":\"https://i.ibb.co/0XzLBRP/download-2.jpg\",\"name\":\"Forza Horizon 4\",\"platf\":\"PC\",\"price\":40.0}]', '', '1999-10-21', '+6285714735100', 'Braintree'),
(24, 0, 65, '[{\"amount\":1,\"edition\":\"Premium\",\"id\":5,\"link\":\"https://i.ibb.co/L94Kq68/download-1.jpg\",\"name\":\"Need For Speed Payback\",\"platf\":\"PS4\",\"price\":65.0}]', '', 'Gunung Putri', '+6285714735100', 'Braintree'),
(25, 0, 50, '[{\"amount\":1,\"edition\":\"Standard\",\"id\":6,\"link\":\"https://i.ibb.co/FBYvFX6/packshot-04d2b3223179468a1932670a7b7ed212.jpg\",\"name\":\"Hitman 2\",\"platf\":\"PC\",\"price\":50.0}]', '', 'Gunung Putri', '+6285714735100', 'Braintree'),
(26, -1, 580, '[{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/L94Kq68/download-1.jpg\",\"name\":\"Need For Speed Payback\",\"platf\":\"PC\",\"price\":260.0,\"amount\":4,\"id\":1},{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/gZMkFdH/image.jpg\",\"name\":\"FIFA 19\",\"platf\":\"XBOX\",\"price\":320.0,\"amount\":4,\"id\":2}]', 'wawa', 'ui', '+6285718223690', 'Braintree'),
(27, -1, 240, '[{\"edition\":\"Premium\",\"link\":\"https://i.ibb.co/K06Vz5H/assassins-creed-odyssey-product-tile-01-ps4-us-12jun18.jpg\",\"name\":\"Assasins Creed Oddsey\",\"platf\":\"PC\",\"price\":240.0,\"amount\":3,\"id\":1}]', '', 'Griya Depok Asri Blok G4/3', '+6285718223690', 'Braintree');

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `store`
--

INSERT INTO `store` (`id`, `name`, `lat`, `lng`) VALUES
(1, 'Game Shop Store', -6.37151, 106.83314),
(2, 'Game Shop Store', -6.368981, 106.833795),
(3, 'Game Shop Store', -6.363103, 106.83347);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Phone` varchar(20) NOT NULL,
  `avatarUrl` text NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Birthdate` date NOT NULL,
  `Address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Phone`, `avatarUrl`, `Name`, `Birthdate`, `Address`) VALUES
('+6281586352670', '+6281586352670.jpg', 'Muji', '0000-00-00', '1965-01-01'),
('+6285714735100', '', 'Geraldi', '1999-10-21', 'Gunung Putri'),
('+6285718223690', '+6285718223690.jpg', 'Tjokorda Wisnu', '1999-04-20', 'Griya Depok Asri'),
('+6287880800747', '', 'Gaffari', '2005-09-10', 'Bogor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banner`
--
ALTER TABLE `banner`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CategoryId` (`CategoryId`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`OrderId`);

--
-- Indexes for table `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Phone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banner`
--
ALTER TABLE `banner`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `OrderId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `store`
--
ALTER TABLE `store`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
