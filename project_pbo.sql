-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2021 at 05:09 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id` int(11) NOT NULL,
  `kode` varchar(8) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `stok` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `fk_jenis_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id`, `kode`, `nama`, `stok`, `harga`, `status`, `fk_jenis_barang`) VALUES
(1, 'MOLO0001', 'Mouse Logitech M190  ', 400, 134000, 1, 3),
(2, 'RAIM0001', 'RAM Imperion 16GB DDR4', 300, 625000, 1, 5),
(3, 'KELO0001', 'Keyboard Logitech Classic K100', 500, 79300, 1, 4),
(4, 'HASE0001', 'Hardisk Seagate Barracuda 1TB', 600, 669000, 1, 6),
(5, 'MOLE0001', 'Monitor LED Philips 272E1 27\" 1080p 144Hz ', 200, 2899000, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `kode` varchar(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `kode`, `nama`, `gender`, `alamat`, `tanggal_lahir`, `status`) VALUES
(1, 'BUPU0001', 'Budi Purnama', 'L', 'Jalan Pavilion No. 21', '1995-04-07', 1),
(2, 'UMVA0001', 'Umar Valentin', 'L', 'Jalan Mendoan No. 4', '1991-03-12', 1),
(3, 'EMBR0001', 'Emili Brendan', 'P', 'Jalan Kursi Terbang No. 12', '2000-05-18', 1),
(4, 'ALAR0001', 'Alice Artur', 'P', 'Jalan Ikan Badut No. 91', '1987-12-15', 0),
(5, 'FANI0001', 'Fabian Nicodemo', 'L', 'Jalan Pavilion No. 21', '1982-07-13', 0);

-- --------------------------------------------------------

--
-- Table structure for table `diskon`
--

CREATE TABLE `diskon` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `potongan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `diskon`
--

INSERT INTO `diskon` (`id`, `nama`, `potongan`) VALUES
(1, 'DISKONMURAH', 150000),
(2, 'POTONGHARGA', 75000),
(3, 'BANYAKDISKON', 100000),
(4, 'AYOBELI', 200000),
(5, 'BOLEHBELI', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `d_trans`
--

CREATE TABLE `d_trans` (
  `nomor_nota` varchar(17) NOT NULL,
  `fk_barang` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `d_trans`
--

INSERT INTO `d_trans` (`nomor_nota`, `fk_barang`, `qty`, `harga`, `subtotal`) VALUES
('NOTA202110130001', 1, 2, 134000, 268),
('NOTA202111100001', 5, 1, 2899000, 2899000),
('NOTA202110210001', 2, 1, 625000, 625000),
('NOTA202110300001', 4, 1, 669000, 669000),
('NOTA202111090001', 2, 2, 625000, 1250000);

-- --------------------------------------------------------

--
-- Table structure for table `h_trans`
--

CREATE TABLE `h_trans` (
  `nomor_nota` varchar(17) NOT NULL,
  `tanggal` date NOT NULL,
  `grand_total` int(11) NOT NULL,
  `fk_diskon` int(11) DEFAULT NULL,
  `fk_karyawan` int(11) NOT NULL,
  `fk_customer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `h_trans`
--

INSERT INTO `h_trans` (`nomor_nota`, `tanggal`, `grand_total`, `fk_diskon`, `fk_karyawan`, `fk_customer`) VALUES
('NOTA202110130001', '2021-10-13', 268000, 5, 1, 4),
('NOTA202110210001', '2021-10-21', 625000, 3, 3, 2),
('NOTA202110300001', '2021-10-30', 669000, 3, 1, 1),
('NOTA202111090001', '2021-11-09', 1250000, 4, 3, 3),
('NOTA202111100001', '2021-11-10', 2899000, 2, 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `jabatan`
--

CREATE TABLE `jabatan` (
  `id` int(11) NOT NULL,
  `nama_jabatan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jabatan`
--

INSERT INTO `jabatan` (`id`, `nama_jabatan`) VALUES
(1, 'Manager'),
(2, 'Inventaris');

-- --------------------------------------------------------

--
-- Table structure for table `jenis_barang`
--

CREATE TABLE `jenis_barang` (
  `id` int(11) NOT NULL,
  `nama_jenis` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jenis_barang`
--

INSERT INTO `jenis_barang` (`id`, `nama_jenis`) VALUES
(1, 'Monitor'),
(2, 'CPU'),
(3, 'Mouse'),
(4, 'Keyboard'),
(5, 'Ram'),
(6, 'Hardisk'),
(7, 'Printer');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id` int(11) NOT NULL,
  `kode` varchar(8) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `alamat` varchar(100) NOT NULL,
  `status` int(1) NOT NULL,
  `fk_jabatan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id`, `kode`, `username`, `password`, `nama`, `gender`, `tanggal_lahir`, `alamat`, `status`, `fk_jabatan`) VALUES
(1, 'LUGE0001', 'LucianaG', 'LucianaG', 'Luciana Geraldine', 'P', '1983-01-19', 'Jalan Penampungan No. 9', 1, 2),
(2, 'KEPO0001', 'KevinP', 'KevinP', 'Kevin Poernomo', 'L', '1995-04-19', 'Jalan Ngagel Selatan No. 12', 1, 1),
(3, 'WAIV0001', 'WaelI', 'WaelI', 'Wael Ivanka', 'L', '1990-01-09', 'Jalan Sudirman Gang 2 No. 12', 1, 2),
(4, 'GLVL0001', 'GlendaV', 'GlendaV', 'Glenda Vlad', 'P', '1980-05-10', 'Jalan Roti Bakar No. 34a', 1, 2),
(5, 'WARO0001', 'WalterR', 'WalterR', 'Walter Roy', 'L', '1989-05-16', 'Jalan London Bridge No. 4', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode` (`kode`),
  ADD KEY `fk_jenis_barang` (`fk_jenis_barang`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diskon`
--
ALTER TABLE `diskon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `d_trans`
--
ALTER TABLE `d_trans`
  ADD KEY `fk_nomor_nota` (`nomor_nota`),
  ADD KEY `fk_barang` (`fk_barang`);

--
-- Indexes for table `h_trans`
--
ALTER TABLE `h_trans`
  ADD PRIMARY KEY (`nomor_nota`),
  ADD KEY `fk_customer` (`fk_customer`),
  ADD KEY `fk_karyawan` (`fk_karyawan`),
  ADD KEY `fk_diskon` (`fk_diskon`);

--
-- Indexes for table `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jenis_barang`
--
ALTER TABLE `jenis_barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode` (`kode`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `fk_jabatan` (`fk_jabatan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `diskon`
--
ALTER TABLE `diskon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `jabatan`
--
ALTER TABLE `jabatan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `jenis_barang`
--
ALTER TABLE `jenis_barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `fk_jenis_barang` FOREIGN KEY (`fk_jenis_barang`) REFERENCES `jenis_barang` (`id`);

--
-- Constraints for table `d_trans`
--
ALTER TABLE `d_trans`
  ADD CONSTRAINT `fk_barang` FOREIGN KEY (`fk_barang`) REFERENCES `barang` (`id`),
  ADD CONSTRAINT `fk_nomor_nota` FOREIGN KEY (`nomor_nota`) REFERENCES `h_trans` (`nomor_nota`);

--
-- Constraints for table `h_trans`
--
ALTER TABLE `h_trans`
  ADD CONSTRAINT `fk_customer` FOREIGN KEY (`fk_customer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `fk_diskon` FOREIGN KEY (`fk_diskon`) REFERENCES `diskon` (`id`),
  ADD CONSTRAINT `fk_karyawan` FOREIGN KEY (`fk_karyawan`) REFERENCES `karyawan` (`id`);

--
-- Constraints for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD CONSTRAINT `fk_jabatan` FOREIGN KEY (`fk_jabatan`) REFERENCES `jabatan` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
