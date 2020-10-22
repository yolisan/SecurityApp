CREATE TABLE Operator(
OperatorID int NOT NULL PRIMARY KEY,
Name varchar(255),
Surname varchar(255),
CompanyNumber varchar(255),
OperatorRole varchar(255),
DepartmentID int FOREIGN KEY REFERENCES Department(DepartmentID),
Active varchar(255)
)
