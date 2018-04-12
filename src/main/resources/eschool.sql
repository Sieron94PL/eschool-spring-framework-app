/*Dodanie klas*/
INSERT INTO CLAZZ(NAME) VALUES ('1A');
INSERT INTO CLAZZ(NAME) VALUES ('1B');
INSERT INTO CLAZZ(NAME) VALUES ('1C');
INSERT INTO CLAZZ(NAME) VALUES ('1D');

/*Dodanie przedmiotów*/
INSERT INTO SUBJECT(NAME) VALUES ('Matematyka');
INSERT INTO SUBJECT(NAME) VALUES ('Jezyk polski');
INSERT INTO SUBJECT(NAME) VALUES ('Jezyk angielski');
INSERT INTO SUBJECT(NAME) VALUES ('Fizyka');

/*Dodanie u¿ytkowników*/
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik', 'nowy_uzytkownik@gmail.com', 'Nowy', 'Uzytkownik', 'haslo1');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik2', 'nowy_uzytkownik2@gmail.com', 'Nowy2', 'Uzytkownik2', 'haslo2');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik3', 'nowy_uzytkownik3@gmail.com', 'Nowy3', 'Uzytkownik3', 'haslo3');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik4', 'nowy_uzytkownik4@gmail.com', 'Nowy4', 'Uzytkownik4', 'haslo4');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik5', 'nowy_uzytkownik5@gmail.com', 'Nowy5', 'Uzytkownik5', 'haslo5');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik6', 'nowy_uzytkownik6@gmail.com', 'Nowy6', 'Uzytkownik6', 'haslo6');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik7', 'nowy_uzytkownik7@gmail.com', 'Nowy7', 'Uzytkownik7', 'haslo7');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik8', 'nowy_uzytkownik8@gmail.com', 'Nowy8', 'Uzytkownik8', 'haslo8');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik9', 'nowy_uzytkownik9@gmail.com', 'Nowy9', 'Uzytkownik9', 'haslo9');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik10', 'nowy_uzytkownik10@gmail.com', 'Nowy10', 'Uzytkownik10', 'haslo10');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik11', 'nowy_uzytkownik11@gmail.com', 'Nowy11', 'Uzytkownik11', 'haslo11');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik12', 'nowy_uzytkownik12@gmail.com', 'Nowy12', 'Uzytkownik12', 'haslo12');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik13', 'nowy_uzytkownik13@gmail.com', 'Nowy13', 'Uzytkownik13', 'haslo13');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik14', 'nowy_uzytkownik14@gmail.com', 'Nowy14', 'Uzytkownik14', 'haslo14');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik15', 'nowy_uzytkownik15@gmail.com', 'Nowy15', 'Uzytkownik15', 'haslo15');
INSERT INTO USER(USERNAME, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('nowy_uzytkownik16', 'nowy_uzytkownik16@gmail.com', 'Nowy16', 'Uzytkownik16', 'haslo16');
/*Dodanie nauczyciela*/
INSERT INTO TEACHER(USER_USERNAME) VALUES ('nowy_uzytkownik');
INSERT INTO TEACHER(USER_USERNAME) VALUES ('nowy_uzytkownik2');
INSERT INTO TEACHER(USER_USERNAME) VALUES ('nowy_uzytkownik3');
INSERT INTO TEACHER(USER_USERNAME) VALUES ('nowy_uzytkownik4');

/*Dodanie ucznia*/
INSERT INTO STUDENT(USER_USERNAME) VALUES ('nowy_uzytkownik5');
INSERT INTO STUDENT(USER_USERNAME) VALUES ('nowy_uzytkownik6');
INSERT INTO STUDENT(USER_USERNAME) VALUES ('nowy_uzytkownik7');
INSERT INTO STUDENT(USER_USERNAME) VALUES ('nowy_uzytkownik8');

/*Dodanie ucznia do klasy*/
UPDATE STUDENT SET CLAZZ_ID = 1 WHERE USER_USERNAME = 'nowy_uzytkownik5';
UPDATE STUDENT SET CLAZZ_ID = 1 WHERE USER_USERNAME = 'nowy_uzytkownik6';
UPDATE STUDENT SET CLAZZ_ID = 2 WHERE USER_USERNAME = 'nowy_uzytkownik7';
UPDATE STUDENT SET CLAZZ_ID = 2 WHERE USER_USERNAME = 'nowy_uzytkownik8';

/*Przypisanie nauczycielowi przedmiotu*/
UPDATE TEACHER SET SUBJECT_ID = 1 WHERE USER_USERNAME = 'nowy_uzytkownik';
UPDATE TEACHER SET SUBJECT_ID = 1 WHERE USER_USERNAME = 'nowy_uzytkownik2';
UPDATE TEACHER SET SUBJECT_ID = 2 WHERE USER_USERNAME = 'nowy_uzytkownik3';
UPDATE TEACHER SET SUBJECT_ID = 2 WHERE USER_USERNAME = 'nowy_uzytkownik4';