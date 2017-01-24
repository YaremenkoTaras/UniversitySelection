INSERT INTO user_role (role) VALUES ('ADMIN'),('USER'),('GUEST');

INSERT INTO application_status (status) VALUES ('ACCEPT'),('PROCESS'),('DECLINE');

INSERT INTO faculty (name, number_of_student, short_name) VALUES ('Aerospace System Faculty',20,'ASSF');
INSERT INTO faculty (name, number_of_student, short_name) VALUES ('Information Technology Faculty',20,'ITF');
INSERT INTO faculty (name, number_of_student, short_name) VALUES ('Applied System Analysis',20,'ASA');

INSERT INTO user (name,sex,birth,phone,address,email,password,user_role_id) VALUES ('Yarema Bohdan','M','1992-12-12','0983332244','Kyiv','user1@email.com',1111,2);
INSERT INTO user (name,sex,birth,phone,address,email,password,user_role_id) VALUES ('Zdolbunov Vitaliy','M','1997-12-12','0983332244','Odessa','user2@email.com',1111,2);
INSERT INTO user (name,sex,birth,phone,address,email,password,user_role_id) VALUES ('Opanasenko Evgeniy','M','1996-4-4','0983332244','Kyiv','user3@email.com',1111,2);
INSERT INTO user (name,sex,birth,phone,address,email,password,user_role_id) VALUES ('Teslya Yaroslav','M','1996-6-17','0983332244','Lviv','user4@email.com',1111,2);
INSERT INTO user (name,sex,birth,phone,address,email,password,user_role_id) VALUES ('Yaremenko Taras','M','1992-12-12','0934326424','Kyiv','yaremenko.taras@gmail.com',1111,1);

INSERT INTO subject (name) VALUES ('Certifycate'),('Mathematic'), ('Physics'), ('Language'), ('History');

INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (1,1,140);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (1,2,140);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (1,3,140);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (1,4,140);

INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (2,1,150);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (2,2,150);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (2,3,150);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (2,4,150);

INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (3,1,160);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (3,2,160);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (3,3,160);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (3,4,160);
INSERT INTO faculty_subject (faculty_id,subject_id,min_mark)VALUES (3,5,140);

INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (170,1,1);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (169,1,2);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (175,1,3);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (160,1,4);

INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (175,2,1);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (177,2,2);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (180,2,3);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (164,2,4);

INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (172,3,1);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (180,3,2);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (179,3,3);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (181,3,4);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (187,3,5);

INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (186,4,1);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (188,4,2);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (182,4,3);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (190,4,4);
INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (186,4,5);

INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',674,1,1,2);
INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',674,2,1,2);

INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',696,1,2,2);
INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',696,2,2,2);

INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',712,1,3,2);
INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',712,2,3,2);
INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',899,3,3,2);

INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',746,1,4,2);
INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',746,2,4,2);
INSERT INTO application (date, description, overall, faculty_id, user_id, application_status_id) VALUES   ('2017-01-01','KPI',932,3,4,2);