-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 16, 2023 lúc 03:23 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `student`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `certificates`
--

CREATE TABLE `certificates` (
  `certificate_id` int(11) NOT NULL,
  `student_id` varchar(50) DEFAULT NULL,
  `certificate_name` varchar(255) DEFAULT NULL,
  `issue_date` date DEFAULT NULL,
  `issuing_authority` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `certificates`
--

INSERT INTO `certificates` (`certificate_id`, `student_id`, `certificate_name`, `issue_date`, `issuing_authority`) VALUES
(1, '520H0592', 'IT', '2002-09-08', 'TDT'),
(2, '520H0595', 'MOS', '2002-09-09', 'TDT'),
(3, '520H0592', 'FINANCE', '2002-09-10', 'TDT'),
(4, '520H0593', 'A', '2002-09-11', 'TDT'),
(5, '520H0594', 'B', '2002-09-12', 'TDT'),
(6, '520H0595', 'C', '2002-09-13', 'TDT'),
(7, '520H0596', 'D', '2002-09-14', 'TDT');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `login_history`
--

CREATE TABLE `login_history` (
  `teacher_id_by_Phone_number` varchar(255) NOT NULL,
  `login_history` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students`
--

CREATE TABLE `students` (
  `name` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) NOT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `falcuty` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `students`
--

INSERT INTO `students` (`name`, `student_id`, `grade`, `date_of_birth`, `gender`, `contact`, `email`, `falcuty`, `major`, `class`) VALUES
('Tran Ngoc Tu', '520H0590', '10', '2002-08-09', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09098'),
('Tran Ngoc Tu', '520H0592', '10', '2002-08-10', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09099'),
('Tran Ngoc Tu', '520H0593', '10', '2002-08-11', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09100'),
('Tran Ngoc Tu', '520H0594', '10', '2002-08-12', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09101'),
('Tran Ngoc Tu', '520H0595', '10', '2002-08-13', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09102'),
('Tran Ngoc Tu', '520H0596', '10', '2002-08-14', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09103'),
('Tran Ngoc Tu', '520H0597', '10', '2002-08-15', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09104'),
('Tran Ngoc Tu', '520H0598', '10', '2002-08-16', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09105'),
('Tran Ngoc Tu', '520H0599', '10', '2002-08-17', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09106'),
('Tran Ngoc Tu', '520H0600', '10', '2002-08-18', 'Maie', '0', 'ranngoctu2089@gmail.com', 'IT', 'ITAI', '20h09107');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `teacherstudents`
--

CREATE TABLE `teacherstudents` (
  `teacher_id_by_Phone_number` varchar(255) NOT NULL,
  `student_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `certificates`
--
ALTER TABLE `certificates`
  ADD PRIMARY KEY (`certificate_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Chỉ mục cho bảng `login_history`
--
ALTER TABLE `login_history`
  ADD KEY `teacher_id_by_Phone_number` (`teacher_id_by_Phone_number`);

--
-- Chỉ mục cho bảng `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`);

--
-- Chỉ mục cho bảng `teacherstudents`
--
ALTER TABLE `teacherstudents`
  ADD PRIMARY KEY (`teacher_id_by_Phone_number`,`student_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`phone_number`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `certificates`
--
ALTER TABLE `certificates`
  MODIFY `certificate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `certificates`
--
ALTER TABLE `certificates`
  ADD CONSTRAINT `certificates_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`);

--
-- Các ràng buộc cho bảng `login_history`
--
ALTER TABLE `login_history`
  ADD CONSTRAINT `login_history_ibfk_1` FOREIGN KEY (`teacher_id_by_Phone_number`) REFERENCES `users` (`phone_number`);

--
-- Các ràng buộc cho bảng `teacherstudents`
--
ALTER TABLE `teacherstudents`
  ADD CONSTRAINT `teacherstudents_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  ADD CONSTRAINT `teacherstudents_ibfk_2` FOREIGN KEY (`teacher_id_by_Phone_number`) REFERENCES `users` (`phone_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
