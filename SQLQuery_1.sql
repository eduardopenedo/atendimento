CREATE DATABASE AtendimentoIFBA
GO


CREATE TABLE [Usuario](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [cpf] BIGINT NOT NULL,
    [matricula] BIGINT NOT NULL,
    [isPsicologo] BIT NOT NULL,
    [isFromCampus] BIT NOT NULL
);


CREATE TABLE [UsuarioTurma](
    [turmaId] INT NOT NULL ,
    [usuarioId] INT NOT NULL
);


CREATE TABLE [Turma](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [nome] VARCHAR(150) NOT NULL
);

CREATE TABLE [RegistroAtendimentos](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [atendidoUsuarioId] INT NOT NULL,
    [descricao] TEXT NOT NULL,
    [tipoAtendimento] VARCHAR(50) NOT NULL,
    [feedback] TEXT NULL,
    [feedbackUsuarioId] INT  NULL,
);


--INSERTS

INSERT INTO [Usuario]([cpf], [matricula], [isPsicologo], [isFromCampus])
VALUES(17618935726, 120043223, 0,0);

INSERT INTO [Usuario]([cpf], [matricula], [isPsicologo], [isFromCampus])
VALUES(1, 1, 1,0);

INSERT INTO [Usuario]([cpf], [matricula], [isPsicologo], [isFromCampus])
VALUES(2, 2, 0,1);


INSERT INTO [Usuario]([cpf], [matricula], [isPsicologo], [isFromCampus])
VALUES(4, 4, 0,0);

INSERT INTO Turma(nome)
VALUES
    ('LP1'),
    ('Português'),
    ('Matemática'),
    ('Filosofia'),
    ('Quimica'),
    ('LP2'),
    ('LP3'),
    ('Algebra Linear');

INSERT UsuarioTurma(turmaId, usuarioId)
VALUES(3,1), (7,1);


INSERT UsuarioTurma(turmaId, usuarioId)
VALUES(5,4), (8,4);


INSERT [RegistroAtendimentos]
(atendidoUsuarioId, 
tipoAtendimento, 
descricao,
feedback, 
feedbackUsuarioId) 
VALUES 
(1,'matematica','balaclava',NULL,NULL)




-- SELECTS 

SELECT * FROM RegistroAtendimentos;

SELECT  TOP 1 *
FROM Usuario
WHERE cpf=17618935726


SELECT Turma.id, Turma.nome FROM Usuario
INNER JOIN UsuarioTurma ON Usuario.id = UsuarioTurma.usuarioId
INNER JOIN Turma ON Turma.id = UsuarioTurma.TurmaId
WHERE Usuario.cpf = 17618935726
;

select * from UsuarioTurma;

SELECT [id], [atendidoUsuarioId] , [descricao] , [tipoAtendimento] ,[feedback],[feedbackUsuarioId] FROM [RegistroAtendimentos] WHERE [tipoAtendimento]='PSICOLOGO'



-- Drop the database 'AtendimentoIFBA'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Uncomment the ALTER DATABASE statement below to set the database to SINGLE_USER mode if the drop database command fails because the database is in use.
-- ALTER DATABASE AtendimentoIFBA SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
-- Drop the database if it exists
IF EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'AtendimentoIFBA'
)
DROP DATABASE AtendimentoIFBA
GO



-- Drop a table called 'Usuario' in schema 'dbo'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[Usuario]', 'U') IS NOT NULL
DROP TABLE [dbo].[Usuario];

-- Drop a table called 'UsuarioTurma' in schema 'dbo'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[UsuarioTurma]', 'U') IS NOT NULL
DROP TABLE [dbo].[UsuarioTurma];

-- Drop a table called 'RegistroAtendimentos' in schema 'dbo'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[RegistroAtendimentos]', 'U') IS NOT NULL
DROP TABLE [dbo].[RegistroAtendimentos]
GO