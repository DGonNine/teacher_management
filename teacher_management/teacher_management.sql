CREATE DATABASE IF NOT EXISTS teacher_management;
USE teacher_management;
CREATE TABLE teacher_type (
    id INT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

CREATE TABLE education_level (
    id INT PRIMARY KEY,
    level_name VARCHAR(30) NOT NULL
);

CREATE TABLE teacher (
    teacher_id VARCHAR(20) PRIMARY KEY,
    last_name VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    teacher_type INT,
    education_level INT,
    image VARCHAR(100),
    base_salary INT NOT NULL,
    start_date DATE NOT NULL,
    FOREIGN KEY (teacher_type) REFERENCES teacher_type(id),
    FOREIGN KEY (education_level) REFERENCES education_level(id)
);
INSERT INTO teacher_type (id, type_name)
VALUES
(1, 'FULL TIME'),
(2, 'PART TIME');

INSERT INTO education_level (id, level_name)
VALUES
(1, 'CU NHAN CAO DANG'),
(2, 'CU NHAN DAI HOC'),
(3, 'THAC SY'),
(4, 'TIEN SY');
INSERT INTO teacher (teacher_id, last_name, first_name, teacher_type, education_level, image, base_salary, start_date)
VALUES
    ('anv123', 'Nguyen Van', 'Thanh', 1, 4, 'img1.jpg', 15000000, '2020-01-15'),
    ('blt123', 'Le Thanh ', 'Ha', 2, 3, 'img2.jpg', 12000000, '2021-03-10'),
    ('ctt123', 'Hoang Thi', 'Bi', 1, 2, 'img3.jpg', 18000000, '2019-07-25'),
    ('dhv123', 'Hoang Van', 'Danh', 2, 1, 'img4.jpg', 11000000, '2022-05-20');


