-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-07-2024 a las 07:21:45
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `catastro_simple`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `construccion`
--

CREATE TABLE `construccion` (
  `id_construccion` int(11) NOT NULL,
  `id_propiedad` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `area` decimal(10,2) NOT NULL,
  `pisos` int(11) NOT NULL,
  `material` varchar(50) NOT NULL,
  `anio_construccion` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `construccion`
--

INSERT INTO `construccion` (`id_construccion`, `id_propiedad`, `id_tipo`, `area`, `pisos`, `material`, `anio_construccion`) VALUES
(1, 1, 1, 120.50, 2, 'Hormigón', '2005'),
(2, 2, 2, 300.00, 5, 'Acero', '2010'),
(3, 3, 1, 85.00, 1, 'Madera', '2015'),
(4, 4, 1, 95.00, 2, 'Hormigón', '2008'),
(5, 5, 2, 350.00, 6, 'Acero', '2012'),
(6, 6, 1, 110.00, 3, 'Madera', '2016'),
(7, 7, 1, 150.00, 4, 'Hormigón', '2009'),
(8, 8, 2, 400.00, 7, 'Acero', '2014');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuesto`
--

CREATE TABLE `impuesto` (
  `id_impuesto` int(11) NOT NULL,
  `id_propiedad` int(11) NOT NULL,
  `anio` year(4) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `pagado` tinyint(1) DEFAULT 0,
  `fecha_pago` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `impuesto`
--

INSERT INTO `impuesto` (`id_impuesto`, `id_propiedad`, `anio`, `monto`, `pagado`, `fecha_pago`) VALUES
(1, 1, '2021', 500.00, 1, '2021-01-15'),
(2, 1, '2022', 550.00, 1, '2022-01-20'),
(3, 2, '2021', 750.00, 0, NULL),
(4, 3, '2021', 300.00, 1, '2021-02-10'),
(5, 3, '2022', 350.00, 0, NULL),
(6, 4, '2021', 480.00, 1, '2021-03-05'),
(7, 4, '2022', 500.00, 1, '2022-03-10'),
(8, 5, '2021', 800.00, 1, '2021-04-15'),
(9, 5, '2022', 850.00, 0, NULL),
(10, 6, '2021', 400.00, 1, '2021-05-20'),
(11, 6, '2022', 420.00, 0, NULL),
(12, 7, '2021', 520.00, 1, '2021-06-25'),
(13, 7, '2022', 550.00, 0, NULL),
(14, 8, '2021', 900.00, 1, '2021-07-30'),
(15, 8, '2022', 950.00, 0, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propiedad`
--

CREATE TABLE `propiedad` (
  `id_propiedad` int(11) NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `ciudad` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `pais` varchar(50) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `propietario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `propiedad`
--

INSERT INTO `propiedad` (`id_propiedad`, `codigo`, `direccion`, `ciudad`, `provincia`, `pais`, `valor`, `propietario_id`) VALUES
(1, 'P001', 'Calle Falsa 123', 'Quito', 'Pichincha', 'Ecuador', 150000.00, NULL),
(2, 'P002', 'Avenida Siempre Viva 742', 'Guayaquil', 'Guayas', 'Ecuador', 200000.00, NULL),
(3, 'P003', 'Calle Principal 456', 'Cuenca', 'Azuay', 'Ecuador', 120000.00, NULL),
(4, 'P004', 'Calle Secundaria 789', 'Quito', 'Pichincha', 'Ecuador', 180000.00, NULL),
(5, 'P005', 'Avenida Central 101', 'Guayaquil', 'Guayas', 'Ecuador', 250000.00, NULL),
(6, 'P006', 'Calle del Sol 202', 'Cuenca', 'Azuay', 'Ecuador', 130000.00, NULL),
(7, 'P007', 'Calle de la Luna 303', 'Quito', 'Pichincha', 'Ecuador', 210000.00, NULL),
(8, 'P008', 'Avenida de las Estrellas 404', 'Guayaquil', 'Guayas', 'Ecuador', 300000.00, NULL),
(9, 'P009', 'Avenida Libertad 505', 'Quito', 'Pichincha', 'Ecuador', 280000.00, NULL),
(11, 'P011', 'Avenida del Río 707', 'Cuenca', 'Azuay', 'Ecuador', 180000.00, NULL),
(13, 'P013', 'Avenida Moderna 909', 'Guayaquil', 'Guayas', 'Ecuador', 400000.00, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_construccion`
--

CREATE TABLE `tipo_construccion` (
  `id_tipo` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_construccion`
--

INSERT INTO `tipo_construccion` (`id_tipo`, `nombre`) VALUES
(1, 'residencial'),
(2, 'comercial'),
(3, 'industrial');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id_tipo` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_tipo`, `nombre`) VALUES
(1, 'admin'),
(2, 'user');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_user` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `comentario` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_user`, `nombre`, `usuario`, `password`, `id_tipo`, `comentario`) VALUES
(11, 'Geral KCHERO', 'ppppppp', '123456', 1, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `construccion`
--
ALTER TABLE `construccion`
  ADD PRIMARY KEY (`id_construccion`),
  ADD KEY `id_propiedad` (`id_propiedad`),
  ADD KEY `id_tipo` (`id_tipo`);

--
-- Indices de la tabla `impuesto`
--
ALTER TABLE `impuesto`
  ADD PRIMARY KEY (`id_impuesto`),
  ADD KEY `id_propiedad` (`id_propiedad`);

--
-- Indices de la tabla `propiedad`
--
ALTER TABLE `propiedad`
  ADD PRIMARY KEY (`id_propiedad`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD KEY `propietario_id` (`propietario_id`);

--
-- Indices de la tabla `tipo_construccion`
--
ALTER TABLE `tipo_construccion`
  ADD PRIMARY KEY (`id_tipo`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id_tipo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD KEY `id_tipo` (`id_tipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `construccion`
--
ALTER TABLE `construccion`
  MODIFY `id_construccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `impuesto`
--
ALTER TABLE `impuesto`
  MODIFY `id_impuesto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `propiedad`
--
ALTER TABLE `propiedad`
  MODIFY `id_propiedad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `tipo_construccion`
--
ALTER TABLE `tipo_construccion`
  MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `construccion`
--
ALTER TABLE `construccion`
  ADD CONSTRAINT `construccion_ibfk_1` FOREIGN KEY (`id_propiedad`) REFERENCES `propiedad` (`id_propiedad`),
  ADD CONSTRAINT `construccion_ibfk_2` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_construccion` (`id_tipo`);

--
-- Filtros para la tabla `impuesto`
--
ALTER TABLE `impuesto`
  ADD CONSTRAINT `impuesto_ibfk_1` FOREIGN KEY (`id_propiedad`) REFERENCES `propiedad` (`id_propiedad`);

--
-- Filtros para la tabla `propiedad`
--
ALTER TABLE `propiedad`
  ADD CONSTRAINT `propiedad_ibfk_1` FOREIGN KEY (`propietario_id`) REFERENCES `usuario` (`id_user`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_usuario` (`id_tipo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
