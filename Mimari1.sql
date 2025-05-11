/*
create table Users(
	id int Primary Key IDENTITY(1,1),
	name varchar(50),
	surname varchar(50),
	phone varchar(50) Unique,
	address varchar(80),
	password varchar(50),
	role varchar(20) 
	);
*/

/*
INSERT INTO Users (name, surname, phone, address, password, role)
values ('Emir' , 'Yürekli' , '05333333333', 'konaklar', '1234' , 'customer');

INSERT INTO Users (name, surname, phone, address, password, role)
values ('Yeliz' , 'Yürekli' , '05555555555', 'konaklar', '1234' , 'chef');

insert into Users(name , surname , phone , address , password , role) 
values ('Yusuf', 'Pire' , '05111111111' , 'Konaklar' , '1234' , 'messenger');

insert into Users (name, surname, phone, address, password, role) 
values ('Yusuf', 'Yürekli', '05444444444' , 'konaklar' , '1234', 'admin');
*/

/*
Create table MenuItems(
	id int Primary key identity(1,1),
	name varchar(80),
	description varchar(300),
	price decimal(10,2)
);
*/

/*
INSERT INTO MenuItems (name,description,price)
values ('Pizza', 'Mantar-Salam-Sosis', '250');

INSERT INTO MenuItems (name,description,price)
values ('Hamburger', '120Gr Köfte', '280');

insert into MenuItems (name, description, price)
values ('Makarna', 'Fetüçini :)' , '400');
*/

/*
CREATE TABLE Orders (
    id INT PRIMARY KEY IDENTITY(1,1),
    userId INT,
    status VARCHAR(50), 
    createdAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (userId) REFERENCES Users(id) --Usersdan gelicek id önemli olan
);
*/

/*
CREATE TABLE OrderItems (
    id INT PRIMARY KEY IDENTITY(1,1),
    orderId INT,
    menuItemId INT,
    quantity INT,
    FOREIGN KEY (orderId) REFERENCES Orders(id),
    FOREIGN KEY (menuItemId) REFERENCES MenuItems(id)
);
*/

select * from Users
select * from orders
select * from orderItems
select * from MenuItems









