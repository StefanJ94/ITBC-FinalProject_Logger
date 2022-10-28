
CREATE TABLE Users(
[id] uniqueidentifier PRIMARY KEY,
[username] varchar(100),
[password] varchar(100),
[email] varchar(100),

)

INSERT INTO Users ([id],[username],[password],[email]) VALUES
('232f393a-53bb-11ed-bdc3-0242ac120002','stefanJ','StefanJ123','jovanovic.s94@yahoo.com')


CREATE TABLE Logs (
 [logId] int PRIMARY KEY,
 [message] varchar(100),
 [logType] int,
 [dateTime] date,
 [id] uniqueidentifier REFERENCES Users (id)

 INSERT INTO Logs ([logId], [message], [logType], [dateTime], [id]) VALUES
 (1,'Dobro je, radi',0, '2008-11-11','232F393A-53BB-11ED-BDC3-0242AC120002')
)
         ADMIN DATA:
        "id": "15ff0553-a697-4c42-947e-dcd4ba6d2879",
        "username": "CvijanPeranovic",
        "password": "CPeranovic123#",
        "email": "cvijanP997@gmail.com"
