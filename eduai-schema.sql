CREATE DATABASE EduAi;

USE EduAi;
Go

CREATE TABLE Roles (
    RoleId INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(50) NOT NULL
);

CREATE TABLE Users (
    UserId INT PRIMARY KEY IDENTITY,
    FullName NVARCHAR(100),
    Email NVARCHAR(100) UNIQUE,
    PasswordHash NVARCHAR(200),
    RoleId INT FOREIGN KEY REFERENCES Roles(RoleId)
);

CREATE TABLE Subjects (
    SubjectId INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100)
);

CREATE TABLE Syllabi (
    SyllabusId INT PRIMARY KEY IDENTITY,
    Title NVARCHAR(100),
    SubjectId INT FOREIGN KEY REFERENCES Subjects(SubjectId),
    GradeLevel NVARCHAR(50),
    DurationMonths INT,
    CreatedBy INT FOREIGN KEY REFERENCES Users(UserId)
);

CREATE TABLE Lessons (
    LessonId INT PRIMARY KEY IDENTITY,
    SyllabusId INT FOREIGN KEY REFERENCES Syllabi(SyllabusId),
    LessonTitle NVARCHAR(100),
    LessonContent TEXT,
    WeekNumber INT
);

CREATE TABLE Quizzes (
    QuizId INT PRIMARY KEY IDENTITY,
    LessonId INT FOREIGN KEY REFERENCES Lessons(LessonId),
    QuizTitle NVARCHAR(100)
);

CREATE TABLE Results (
    ResultId INT PRIMARY KEY IDENTITY,
    QuizId INT FOREIGN KEY REFERENCES Quizzes(QuizId),
    StudentId INT FOREIGN KEY REFERENCES Users(UserId),
    Score DECIMAL(5,2),
    TakenAt DATETIME DEFAULT GETDATE()
);

SELECT * FROM INFORMATION_SCHEMA.TABLES;