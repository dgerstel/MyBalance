create database balance4;

CREATE TABLE IF NOT EXISTS user_role (
id INT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(50) ,
    description VARCHAR(50)
);

insert into user_role (role, description) values ('ROLE_USER', "default role for user");

CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    role INT, 
    gender VARCHAR (20),
    basic_expense INT,
    password VARCHAR(200) NOT NULL,
    height INT,
	age INT,
	weight INT,
	job_activity_level INT,
    FOREIGN KEY (role) REFERENCES user_role(id)
);

CREATE TABLE IF NOT EXISTS meal_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS activity_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS  meal(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(65) NOT NULL,
    kcalories INTEGER,
    description VARCHAR(500),
	category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES meal_category(id)
);

CREATE TABLE IF NOT EXISTS  activity(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(65) NOT NULL,
    kcalories INTEGER,
    description VARCHAR(500),
	category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES activity_category(id)
);

CREATE TABLE IF NOT EXISTS activity_diary (
	id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    activity_id INT NOT NULL,
    user_id INT NOT NULL,
    act_size FLOAT,
    FOREIGN KEY (activity_id) REFERENCES activity(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS food_diary (
	id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    meal_id INT NOT NULL,
    user_id INT NOT NULL,
    meal_size FLOAT,
    FOREIGN KEY (meal_id) REFERENCES meal(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into meal_category (name, description) values ('sniadanie', 'poranny posilek, drugie śniadanie');
insert into meal_category (name, description) values ('obiad', 'posilek głowny w ciągu dnia');
insert into meal_category (name, description) values ('kolacja', 'posiłek popołudniowy');
insert into meal_category (name, description) values ('dania i posiłki', 'dania obiadowe składające się z wielu produktów');
insert into meal_category (name, description) values ('przekąski', 'drobne posiłki między daniami głównymi');
insert into meal_category (name, description) values ('dodatki', 'produkty dodatkowe niebędące samodzielnymi daniami');
insert into meal_category (name, description) values ('desery', 'słodkie posiłki, ciasta');
insert into meal_category (name, description) values ('napoje', 'napoje i alkohole');
insert into meal_category (name, description) values ('zupy', 'wodne dania');
insert into meal_category (name, description) values ('owoce i warzywa', 'zdrowe przekąski');
insert into meal_category (name, description) values ('inne', 'inne posiłki lub przekąski');

insert into activity_category (name, description) values ('aktywności aerobowe (cardio)', 'ćwiczenia poprawiające wydolność i kondycję');
insert into activity_category (name, description) values ('trening siłowy', 'ćwiczenia z obciążeniem');
insert into activity_category (name, description) values ('wybrane ćwiczenia', 'serie ćwiczeń jednego rodzaju');
insert into activity_category (name, description) values ('gimnastyka, rozciąganie', 'ćwiczenia gimnastyczne o niskiej intensywności');
insert into activity_category (name, description) values ('praca fizyczna', 'różnego rodzaju prace domowe i dodatkowe');
insert into activity_category (name, description) values ('zajęcia sportowe', 'zajęcia, gry zespołowe');
insert into activity_category (name, description) values ('inne aktywności', 'aktywności pozostałe');



