-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 19-06-2020 a las 21:38:29
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_videojuegos`
--
--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ide_cliente` int(11) NOT NULL,
  `nombre` varchar(90)  NOT NULL,
  `apellido` varchar(90)  NOT NULL,
  `celular` varchar(45)  NOT NULL,
  `identificacion` varchar(45)  NOT NULL,
  `fechaNacimiento` date  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Insertando datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ide_cliente`, `nombre`, `apellido`, `celular`, `identificacion`, `fechaNacimiento`) VALUES
(1, 'Oscar', 'Mora Mesa', '3113926352', '10203040', '1990-06-20'),
(2, 'Pedro', 'Angulo', '3205632563', '11223344', '2000-09-16'),
(3, 'Camilo Andres', 'Parra', '3005211505', '11213141', '2011-12-30');


--
--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ide_producto` int(11) NOT NULL,
  `titulo` varchar(90)  NOT NULL,
  `director` varchar(90)  NOT NULL,
  `protagonista` varchar(90)  NOT NULL,
  `productor` varchar(90)  NOT NULL,
  `marca` varchar(45)  NOT NULL,
  `anio` varchar(10)  NOT NULL,
  `tecnologia` varchar(20)  NOT NULL,
  `precio` varchar(10)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Insertando datos para la tabla `productos`
--

INSERT INTO `productos` (`ide_producto`, `titulo`, `director`, `protagonista`,`productor`, `marca`, `anio`, `tecnologia`, `precio`) VALUES
(1, 'Unsolicited', 'Lucas Pope', 'protagonista1', 'Lucas Pope', 'Nexon', '2015', 'PC', '8000'),
(2, 'Undertale', 'Toby Fox', 'Frisk', 'Eugene Jarvis', 'Nintendo', '2015', 'PLAY', '6000'),
(3, 'Resistance 2', 'Ted Price', 'Nathan Hale', 'Jason Rubin', 'Sony', '2011', 'XBOX', '7800');
-- --------------------------------------------------------

CREATE TABLE `ventas` (
  `ide_alquiler` int(11) NOT NULL,
  `ide_producto` int(11) NOT NULL,
  `ide_cliente` int(11) NOT NULL,
  `fecha_alquiler` date NOT NULL,
  `fecha_vence` date NOT NULL,
  `val_alquiler` varchar(45)  NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Insertando datos para la tabla `ventas`
--

INSERT INTO `ventas` (`ide_alquiler`, `ide_producto`, `ide_cliente`, `fecha_alquiler`,`fecha_vence`, `val_alquiler`) VALUES
(1, 3, 1, '2020-06-20', '2020-06-30', '7800'),
(2, 1, 3, '2020-06-19', '2020-06-28', '8000'),
(3, 2, 2, '2020-06-20', '2020-07-01', '6000');
-- --------------------------------------------------------
--


--
-- Índices para tablas 
--
--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ide_cliente`);
  
 --
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ide_producto`);
  
   --
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`ide_alquiler`),
  ADD KEY `fk_ventas_cliente1_idx` (`ide_cliente`),
  ADD KEY `fk_ventas_producto1_idx` (`ide_producto`);

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ide_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `productos`
  MODIFY `ide_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
  
  
--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `ide_alquiler` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
  
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;