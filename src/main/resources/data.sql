CREATE TABLE data(
pollId INT,
title VARCHAR(30) UNIQUE,
question VARCHAR(50),
answer1 VARCHAR(30),
answer2 VARCHAR(30),
answer3 VARCHAR(30)
);
INSERT INTO data VALUES(1,'Candy Poll','What is your favourite candy','Smarties ?','Sal Water Taffy ?','Candy Canes ?');
INSERT INTO data VALUES(2,'Game Poll','What is the name of your favourite game ?','Watch dogs 2 ?','overwatch ?','GTA vice City ?');
INSERT INTO data VALUES(3,'Food','What is your favourite food','Paneer Tikka ?','Shahi Paneer ?','Paneer Butter Masala ?');
INSERT INTO data VALUES(4,'Tech','What is your favourite piece of tech ?','Smart Phone ?','Smart Watch ?','Laptop ?');