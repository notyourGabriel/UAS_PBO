-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 29, 2024 at 10:21 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ShowroomMobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `Mobil`
--

CREATE TABLE `Mobil` (
  `Id_Mobil` int(11) NOT NULL,
  `Merk` varchar(50) NOT NULL,
  `Tahun` year(4) NOT NULL,
  `Harga` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Mobil`
--

INSERT INTO `Mobil` (`Id_Mobil`, `Merk`, `Tahun`, `Harga`) VALUES
(1, 'Toyota Avanza', '2020', 200000000.00),
(2, 'Honda Jazz', '2018', 220000000.00),
(3, 'Suzuki Ertiga', '2021', 250000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `Pelanggan`
--

CREATE TABLE `Pelanggan` (
  `Id_Pelanggan` int(11) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `NIK` char(16) NOT NULL,
  `No_Telp` varchar(15) DEFAULT NULL,
  `Alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Pelanggan`
--

INSERT INTO `Pelanggan` (`Id_Pelanggan`, `Nama`, `NIK`, `No_Telp`, `Alamat`) VALUES
(1, 'Andi', '3201234567891234', '08123456789', 'Jl. Merdeka No. 1'),
(2, 'Budi', '3202234567891234', '08234567890', 'Jl. Sudirman No. 2'),
(3, 'Citra', '3203234567891234', '08345678901', 'Jl. Gatot Subroto No. 3');

-- --------------------------------------------------------

--
-- Table structure for table `Penjualan`
--

CREATE TABLE `Penjualan` (
  `Id_Penjualan` int(11) NOT NULL,
  `Id_Mobil` int(11) DEFAULT NULL,
  `Id_Pelanggan` int(11) DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL,
  `Total_Biaya` decimal(15,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Penjualan`
--

INSERT INTO `Penjualan` (`Id_Penjualan`, `Id_Mobil`, `Id_Pelanggan`, `Qty`, `Total_Biaya`) VALUES
(1, 1, 1, 1, 200000000.00),
(2, 2, 2, 1, 350000000.00),
(3, 3, 3, 2, 360000000.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Mobil`
--
ALTER TABLE `Mobil`
  ADD PRIMARY KEY (`Id_Mobil`);

--
-- Indexes for table `Pelanggan`
--
ALTER TABLE `Pelanggan`
  ADD PRIMARY KEY (`Id_Pelanggan`);

--
-- Indexes for table `Penjualan`
--
ALTER TABLE `Penjualan`
  ADD PRIMARY KEY (`Id_Penjualan`),
  ADD KEY `Id_Mobil` (`Id_Mobil`),
  ADD KEY `Id_Pelanggan` (`Id_Pelanggan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Mobil`
--
ALTER TABLE `Mobil`
  MODIFY `Id_Mobil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `Pelanggan`
--
ALTER TABLE `Pelanggan`
  MODIFY `Id_Pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `Penjualan`
--
ALTER TABLE `Penjualan`
  MODIFY `Id_Penjualan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Penjualan`
--
ALTER TABLE `Penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`Id_Mobil`) REFERENCES `Mobil` (`Id_Mobil`),
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`Id_Pelanggan`) REFERENCES `Pelanggan` (`Id_Pelanggan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
