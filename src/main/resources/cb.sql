-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Авг 29 2018 г., 22:38
-- Версия сервера: 5.6.38
-- Версия PHP: 7.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `cb`
--

-- --------------------------------------------------------

--
-- Структура таблицы `peoples`
--

CREATE TABLE `peoples` (
  `id` int(11) NOT NULL,
  `fname` varchar(25) DEFAULT NULL,
  `lname` varchar(25) DEFAULT NULL,
  `name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `peoples`
--

INSERT INTO `peoples` (`id`, `fname`, `lname`, `name`) VALUES
(1, 'Денис', 'Кокорин', '');

-- --------------------------------------------------------

--
-- Структура таблицы `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `id` varchar(8) NOT NULL,
  `name` varchar(50) NOT NULL,
  `volume` decimal(10,2) NOT NULL,
  `price_base_rasch` float NOT NULL,
  `price_base_vozn` float NOT NULL,
  `price_sv` float NOT NULL,
  `price_qp` float NOT NULL,
  `price_sc` float NOT NULL,
  `price_c` float NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `products`
--

INSERT INTO `products` (`id`, `name`, `volume`, `price_base_rasch`, `price_base_vozn`, `price_sv`, `price_qp`, `price_sc`, `price_c`, `price`) VALUES
('0050', 'Термо Комплит', '30.95', 1986.8, 1856.82, 1314.54, 1489.83, 1643.19, 1862.29, 2410.16),
('0141', 'Коктель Формула 1 Ваниль', '23.95', 1683.52, 1573.38, 1113.89, 1262.41, 1392.38, 1578.03, 2042.25),
('3151', 'Напиток Лифтофф Апельсин (10 шт)', '15.95', 1146.33, 1071.33, 758.46, 859.6, 948.09, 1074.51, 1390.59);

-- --------------------------------------------------------

--
-- Структура таблицы `projects`
--

CREATE TABLE `projects` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(2048) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `s_price_qp`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `s_price_qp` (
`id` varchar(8)
,`name` varchar(50)
,`volume` decimal(10,2)
,`price_qp` float
);

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `s_price_sv`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `s_price_sv` (
`id` varchar(8)
,`name` varchar(50)
,`volume` decimal(10,2)
,`price_sv` float
);

-- --------------------------------------------------------

--
-- Структура таблицы `tele_users`
--

CREATE TABLE `tele_users` (
  `tid` int(11) DEFAULT NULL,
  `tfname` varchar(50) DEFAULT NULL,
  `tlname` varchar(50) DEFAULT NULL,
  `tuname` varchar(50) DEFAULT NULL,
  `lang` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура для представления `s_price_qp`
--
DROP TABLE IF EXISTS `s_price_qp`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `s_price_qp`  AS  select `products`.`id` AS `id`,`products`.`name` AS `name`,`products`.`volume` AS `volume`,`products`.`price_qp` AS `price_qp` from `products` ;

-- --------------------------------------------------------

--
-- Структура для представления `s_price_sv`
--
DROP TABLE IF EXISTS `s_price_sv`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `s_price_sv`  AS  select `products`.`id` AS `id`,`products`.`name` AS `name`,`products`.`volume` AS `volume`,`products`.`price_sv` AS `price_sv` from `products` ;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `peoples`
--
ALTER TABLE `peoples`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `peoples`
--
ALTER TABLE `peoples`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
