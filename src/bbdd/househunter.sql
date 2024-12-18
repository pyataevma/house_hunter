-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-10-2024 a las 03:12:34
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

--
-- Creación de la base de datos: `househunter`
--

DROP DATABASE IF EXISTS househunter;
CREATE DATABASE househunter;
USE househunter;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--
-- (`nombre`, `apellido`, `dni`, `email`, `telefono`, `nivel`) 

CREATE TABLE `househunter`.`cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` int(15) DEFAULT NULL,
  `nivel` int(11) DEFAULT NULL,
   PRIMARY KEY (`id_cliente`)
) ENGINE = InnoDB;
-- --------------------------------------------------------
INSERT INTO `cliente` ( `nombre`, `apellido`, `dni`, `email`, `telefono`,`nivel`) 
VALUES 
('María', 'Jose', '11111222', 'm@m', 987654321,0),
('Juan', 'Perez', '22111222', 'j@j', 987654123,0),
('Paloma', 'Martinez', '2323222', 'p@p', 987653343,0);
--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `househunter`.`empleado` (
  `id_empleado` int(20) NOT NULL AUTO_INCREMENT,
  `user` varchar(26) NOT NULL,
  `pass` varchar(26) NOT NULL,
  `rol` varchar(26) NOT NULL,
   PRIMARY KEY (`id_empleado`)
) ENGINE = InnoDB;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `househunter`.`hotel` (
  `id_hotel` int(11) NOT NULL AUTO_INCREMENT, 
  `localidad` varchar(45) NOT NULL,
   PRIMARY KEY (`id_hotel`)
) ENGINE = InnoDB;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `househunter`.`persona` (
    `id_persona` INT(11) NOT NULL AUTO_INCREMENT, 
    `nombre` VARCHAR(45) NOT NULL, 
    `apellido` VARCHAR(45) NOT NULL, 
    `dni` VARCHAR(45) NOT NULL, 
    PRIMARY KEY (`id_persona`)
) ENGINE = InnoDB;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `househunter`.`habitacion` (
  `id_habitacion` int(11) NOT NULL AUTO_INCREMENT,
  `id_hotel` int(11) DEFAULT NULL,
  `nro` INT(11),
  `piso` INT(11),
  `precio` DOUBLE,
  `tipo` ENUM('PAREJA','FAMILIAR','VIP'),
   PRIMARY KEY (`id_habitacion`)
) ENGINE = InnoDB;

-- --------------------------------------------------------
INSERT INTO `habitacion` (`id_hotel`, `nro`, `piso`, `precio`, `tipo`) 
VALUES ('1', '12', '2', '10000', 'PAREJA'),
('1', '15', '2', '50000', 'VIP'),
('1', '11', '2', '10000', 'PAREJA'),
('1', '13', '2', '30000', 'FAMILIAR'),
('1', '16', '2', '30000', 'FAMILIAR');
--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `househunter`.`reserva` (
  `id_reserva` int(11) NOT NULL AUTO_INCREMENT,
  `id_habitacion` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `fecha_entrada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
   PRIMARY KEY (`id_reserva`)
) ENGINE = InnoDB;

INSERT INTO `reserva` ( `id_habitacion`, `id_cliente`, `fecha_entrada`, `fecha_salida`) 
VALUES ('1', '1', '2024-11-30', '2024-12-10'),
('2', '1', '2024-11-30', '2024-12-10'),
('3', '2', '2024-11-30', '2024-12-20');


--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `reserva`
--
-- ALTER TABLE `househunter`.`reserva`
--  ADD KEY `id_hotel` (`id_hotel`);
--
-- Restricciones para tablas volcadas
--
-- Filtros para la tabla `reserva`
--
-- ALTER TABLE `househunter`.`reserva`
--  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_hotel`);
