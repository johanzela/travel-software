-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2023 a las 18:00:32
-- Versión del servidor: 10.3.15-MariaDB
-- Versión de PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdsistviaje`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_pasajero` (`pNroViaje` CHAR(6), `pNombres` VARCHAR(100), `pNroAsiento` INT, `pTipo` VARCHAR(100), `pPago` DECIMAL(8,2))  begin
     declare pCode varchar(8);
     select CONCAT(LPAD(ifnull(MAX(SUBSTR(nroBoleto,2,8)) , 0) + 1 , 8, '0' )) INTO pCode
     from Pasajero;     
          
     insert into Pasajero(nroBoleto,nroViaje,nombres,nroAsiento,tipo,pago) values(pCode,pNroViaje , pNombres , pNroAsiento,pTipo,pPago);   

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_ruta` (`pNomRuta` VARCHAR(60), `pImagenRuta` TEXT)  begin
     declare pCode varchar(6);
     select CONCAT('R', LPAD(ifnull(MAX(SUBSTR(codRuta,2,7)) , 0) + 1 , 5 , '0' )) INTO pCode
     from Ruta;     
          
     insert into Ruta(codRuta,nomRuta,imagenRuta) values(pCode , pNomRuta , pImagenRuta);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_viaje` (`pCodRuta` VARCHAR(6), `pFecha` DATE, `pHora` TIME, `pCosto` DECIMAL(8,2))  begin
     declare pCode varchar(6);
     select CONCAT('V', LPAD(ifnull(MAX(SUBSTR(nroViaje,2,7)) , 0) + 1 , 5 , '0' )) INTO pCode
     from Viaje;     
          
     insert into Viaje(nroViaje,codRuta,fecha,hora,costo) values(pCode , pCodRuta , pFecha,pHora,pCosto);
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

CREATE TABLE `pasajero` (
  `nroBoleto` char(8) NOT NULL,
  `nroViaje` char(6) DEFAULT NULL,
  `nombres` varchar(70) DEFAULT NULL,
  `nroAsiento` int(11) DEFAULT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `pago` decimal(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pasajero`
--

INSERT INTO `pasajero` (`nroBoleto`, `nroViaje`, `nombres`, `nroAsiento`, `tipo`, `pago`) VALUES
('00000001', 'V00001', 'Juan Campos', 15, 'Ejecutivo', '623.70'),
('00000003', 'V00002', 'Maria Luciana Fernandez Cardenas', 1, 'Ejecutivo', '165.00'),
('00000004', 'V00001', 'Jorge Bacilio Calderon', 16, 'Ejecutivo', '623.70'),
('00000005', 'V00001', 'Mario Alarcon Campos', 18, 'Premium', '850.50');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `codRuta` varchar(6) NOT NULL,
  `nomRuta` varchar(60) DEFAULT NULL,
  `imagenRuta` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ruta`
--

INSERT INTO `ruta` (`codRuta`, `nomRuta`, `imagenRuta`) VALUES
('R00001', 'Arequipa', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOopJE9y9K51BoXAg-kNGtsvx9AKWZZMWraA&usqp=CAU'),
('R00002', 'Uyaquil', 'https://bananomeridiano.com/wp-content/uploads/2019/06/que-ver-en-arequipa.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `codUsuario` int(11) NOT NULL,
  `nombres` varchar(70) DEFAULT NULL,
  `apellidos` varchar(60) DEFAULT NULL,
  `correo` varchar(78) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`codUsuario`, `nombres`, `apellidos`, `correo`, `password`) VALUES
(1, 'Juan', 'Palacios', 'admin@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `nroViaje` char(6) NOT NULL,
  `codRuta` varchar(8) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `costo` decimal(8,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `viaje`
--

INSERT INTO `viaje` (`nroViaje`, `codRuta`, `fecha`, `hora`, `costo`) VALUES
('V00001', 'R00001', '2023-06-17', '11:19:00', '567'),
('V00002', 'R00002', '2023-06-15', '09:20:00', '150'),
('V00003', 'R00002', '2023-06-15', '09:20:00', '150'),
('V00004', 'R00002', '2023-06-16', '06:00:00', '110'),
('V00005', 'R00001', '2023-06-26', '10:00:00', '200'),
('V00006', 'R00001', '2023-06-04', '12:00:00', '600');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD PRIMARY KEY (`nroBoleto`),
  ADD KEY `nroViaje` (`nroViaje`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`codRuta`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codUsuario`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`nroViaje`),
  ADD KEY `codRuta` (`codRuta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `codUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD CONSTRAINT `pasajero_ibfk_1` FOREIGN KEY (`nroViaje`) REFERENCES `viaje` (`nroViaje`);

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`codRuta`) REFERENCES `ruta` (`codRuta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
