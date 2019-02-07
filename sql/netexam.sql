DROP DATABASE IF EXISTS netexam;
CREATE DATABASE `netexam`; 
USE `netexam`;
 
CREATE TABLE `user`(
id INT(11) NOT NULL AUTO_INCREMENT,
firstname VARCHAR(50) NOT NULL,
lastname  VARCHAR(50) NOT NULL,
patronymic VARCHAR(50) ,
login  VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
user_type VARCHAR(50) NOT NULL,
UNIQUE KEY(login),
PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE teacher (
id INT(11) NOT NULL AUTO_INCREMENT,
department VARCHAR(50) NOT NULL,
position VARCHAR(50) NOT NULL,
user_id INT(11) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES `user` (id) on delete cascade

) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE student (
id INT(11) NOT NULL AUTO_INCREMENT,
`group` VARCHAR(50) NOT NULL,
semester INT(12) NOT NULL,
user_id INT(11) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES `user` (id) on delete cascade

) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE exam (
id INT(11) NOT NULL AUTO_INCREMENT   ,
teacher_id INT(11) NOT NULL,
name VARCHAR(50) NOT NULL,
timeExam INT(15) NOT NULL,
semester INT(12) NOT NULL,
status VARCHAR(50) NOT NULL,
questionCount INT(15) ,
showDetails VARCHAR(50),
PRIMARY KEY (id),
UNIQUE(name,semester),
FOREIGN KEY (teacher_id) REFERENCES teacher (id) on delete cascade
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE question (
id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
content VARCHAR(50) NOT NULL,
number INT(4)  NOT NULL,
rightAnswer INT(4)  NOT NULL,
exam_id INT(11) NOT NULL,
FOREIGN KEY (exam_id ) REFERENCES exam (id) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE answer (
id INT(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
content VARCHAR(50) NOT NULL,
number INT(4)  NOT NULL,
question_id  INT(11) NOT NULL,
FOREIGN KEY (question_id) REFERENCES question (id) on delete cascade

)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE protocol (
id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
student_id INT(11) NOT NULL,
exam_id INT(11) NOT NULL,
rightAnswerCount INT(11) ,
wrongAnswerCount INT(11) ,
noAnswerCount INT(11) ,
startTime datetime ,
finishTime datetime ,
FOREIGN KEY (student_id) REFERENCES student (id) on delete cascade,
FOREIGN KEY (exam_id) REFERENCES exam (id) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;



CREATE TABLE answers_student (
id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
answer_id  INT(11) ,
protocol_id  INT(11) ,
question_id INT(11) ,
status VARCHAR(50) ,
FOREIGN KEY (answer_id ) REFERENCES answer (id)  ,
FOREIGN KEY (protocol_id) REFERENCES protocol (id) on delete cascade,
FOREIGN KEY (question_id) REFERENCES question (id) 
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `session` (
id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
cookies VARCHAR(50) NOT NULL,
user_id INT(11) NOT NULL ,
unique key(user_id),
FOREIGN KEY (user_id) REFERENCES `user` (id) on delete cascade

)ENGINE=INNODB DEFAULT CHARSET=utf8;

