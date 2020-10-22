USE [SecurityTransport]
GO

INSERT INTO [dbo].[Operator]
           ([OperatorID]
           ,[Name]
           ,[Surname]
           ,[CompanyNumber]
           ,[OperatorRole]
           ,[DepartmentID]
           ,[Active])
     VALUES
           (2
		   ,'Nomsa'
		   ,'Sibeko'
           ,'234567'
           ,'Employee'
           ,null
           ,'Active')
GO


