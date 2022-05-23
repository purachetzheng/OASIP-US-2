-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Schema events

-- Schema events

CREATE SCHEMA IF NOT EXISTS oasip DEFAULT CHARACTER SET utf8 ;
USE oasip;
SET NAMES UTF8;
-- Table events.EventCategories

CREATE TABLE IF NOT EXISTS eventcategories (
eventCategoryId INT NOT NULL AUTO_INCREMENT,
eventCategoryName VARCHAR(100) NOT NULL,
eventCategoryDescription VARCHAR(500),
eventDuration INT NOT NULL, CHECK (eventDuration between 1 and 480),
PRIMARY KEY (eventCategoryId))
ENGINE = InnoDB;

-- Table events.Events

CREATE TABLE IF NOT EXISTS events (
bookingID INT NOT NULL AUTO_INCREMENT,
bookingName VARCHAR(100) NOT NULL,
bookingEmail VARCHAR(45) NOT NULL,
eventCategoryId INT NOT NULL,
eventDuration INT NOT NULL, CHECK (eventDuration between 1 and 480),
eventStartTime DATETIME NOT NULL,
eventNotes VARCHAR(500),
PRIMARY KEY (bookingID),
INDEX fk_event_eventCategoryId_idx (eventCategoryId ASC) VISIBLE,
CONSTRAINT fk_event_eventCategoryId
FOREIGN KEY (eventCategoryId)
REFERENCES eventcategories (eventCategoryId)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO eventcategories
values (1,'Project Management Clinic', 'ตารางนัดหมายนี้ใช้สำหรับนัดหมาย project management clinic ในวิชา INT221 integrated project I ให้นักศึกษาเตรียมเอกสารที่เกี่ยวข้องเพื่อแสดง ระหว่างขอคำปรึกษา', 30),
(2,'DevOps/Infra Clinic', 'Use this event category for DevOps/Infra clinic.', 20),
(3,'Database Clinic', 'ตารางนัดหมายนี้ใช้สำหรับนัดหมาย database clinic ในวิชา INT221 integrated project I', 15),
(4,'Client-side Clinic', 'ตารางนัดหมายนี้ใช้สำหรับนัดหมาย client-side clinic ในวิชา INT221 integrated project I', 30),
(5,'Server-side Clinic', null, 30);

-- BookingEvent
INSERT INTO events values
(1, 'Somchai Jaidee (OR-7)', 'somchai.jai@mail.kmutt.ac.th', 2, 30, '2022-05-23 6:30:00', null),
(2, 'Somsri Rakdee (SJ-3)', 'somsri.rak@mail.kmutt.ac.th', 1, 30, '2022-04-27 2:30:00', 'ขอปรึกษาปัญหาเพื่อนไม่ช่วยงาน'),
(3, 'สมเกียรติ ขยันเรียน กลุ่ม TT-4', 'somkiat.kay@kmutt.ac.th', 3, 15, '2022-05-23 9:30:00', null);
(4, 'INFRA-26-11:15', 'INFRA-26-11:15@kmutt.ac.th', 2, 20, '2022-05-26 4:15:00', null);
(5, 'INFRA-26-09:20', 'INFRA-26-09:20@kmutt.ac.th', 2, 30, '2022-05-26 2:20:00', null);
(6, 'INFRA-26-10:40', 'INFRA-26-10:40@kmutt.ac.th', 2, 20, '2022-05-26 3:40:00', null);
(7, 'INFRA-26-12:00', 'INFRA-26-12:00@kmutt.ac.th', 2, 30, '2022-05-26 5:00:00', null);
(8, 'DB-26-10:20', 'DB-26-10:20@kmutt.ac.th', 3, 15, '2022-05-26 3:20:00', null);
(9, 'INFRA-27-10:20', 'INFRA-27-10:20@kmutt.ac.th', 2, 20, '2022-05-27 3:20:00', null);
(10, 'INFRA-26-10:00', 'INFRA-26-10:00@kmutt.ac.th', 2, 20, '2022-05-26 3:00:00', null);
(11, 'INFRA-26-11:45', 'INFRA-26-11:45@kmutt.ac.th', 2, 10, '2022-05-26 4:45:00', null);
-- Create USER for specific use
CREATE USER 'OASIPBE'@'%' IDENTIFIED BY 'BEBE';
GRANT ALL PRIVILEGES ON oasip.* TO 'OASIPBE'@'%';
FLUSH PRIVILEGES;
