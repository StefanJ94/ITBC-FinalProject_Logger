
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
 [dateTime] datetime,
 [id] uniqueidentifier REFERENCES Users (id)
)

