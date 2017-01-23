--数据库名称 edusb


CREATE DATABASE IF NOT EXISTS edusb;
use edusb;
CREATE TABLE users(
	userId INT NOT NULL AUTO_INCREMENT,
	userName VARCHAR(20) PRIMARY KEY,
	passwords VARCHAR(20) NOT NULL,
	nickName VARCHAR(20) ,
	email VARCHAR(20) NOT NULL,
	mobilePhone VARCHAR(15) NOT NULL,
	display VARCHAR(50) NOT NULL,
	userIntroduction VARCHAR(100) NOT NULL,
    KEY(userId)
) default charset=utf8;

CREATE TABLE school(
       schoolId INT PRIMARY KEY AUTO_INCREMENT,
       schoolName VARCHAR(50) NOT NULL,
       cityName VARCHAR(50) NOT NULL
) default charset=utf8;

CREATE TABLE trade(
      tradeId INT PRIMARY KEY AUTO_INCREMENT,
      tradeNmae VARCHAR(50) NOT NULL
) default charset=utf8; 

CREATE TABLE management(
	managementId INT PRIMARY KEY AUTO_INCREMENT,
	managementName VARCHAR(20) NOT NULL,
	managementTel VARCHAR(15) NOT NULL,
	managementAdd VARCHAR(20) NOT NULL,
	schoolId INT NOT NULL,
	rank VARCHAR(10) NOT NULL,
    FOREIGN KEY(schoolId) REFERENCES school(schoolId)
) default charset=utf8;

CREATE TABLE mentor(
	mentorId INT PRIMARY KEY AUTO_INCREMENT, 
	mentorName VARCHAR(20) NOT NULL,
	companyName VARCHAR(50) NOT NULL,
	position VARCHAR(50) NOT NULL,
	mentorIntroduction VARCHAR(100) NOT NULL,
    tradeId INT NOT NULL,
schoolId INT NOT NULL,
	FOREIGN KEY(tradeId) REFERENCES trade(tradeId),
	FOREIGN KEY(schoolId) REFERENCES school(schoolId)
) default charset=utf8;

CREATE TABLE item(
	itemId INT PRIMARY KEY AUTO_INCREMENT,
	itemName VARCHAR(50) NOT NULL,
	itemBrief VARCHAR(100) NOT NULL,
	itemIntroduction VARCHAR(100) NOT NULL,
	schoolId INT NOT NULL,
	tradeId INT NOT NULL,
    FOREIGN KEY(schoolId) REFERENCES school(schoolId),
    FOREIGN KEY(tradeId) REFERENCES trade(tradeId)
) default charset=utf8;

CREATE TABLE copartner(
	copartnerId INT PRIMARY KEY AUTO_INCREMENT,
	copartnerName VARCHAR(20) NOT NULL,
	copartnerSkills VARCHAR(100) NOT NULL,
	copartnerIntroduction VARCHAR(100) NOT NULL,
	tradeId INT NOT NULL,
    FOREIGN KEY(tradeId) REFERENCES trade(tradeId)
) default charset=utf8;

CREATE TABLE userinfo(
	userId INT NOT NULL,
	mentorId INT NOT NULL DEFAULT -1,
	itemId INT NOT NULL DEFAULT -1,
	copartnerId INT NOT NULL DEFAULT -1,
FOREIGN KEY(userId) REFERENCES users(userId),
FOREIGN KEY(mentorId) REFERENCES mentor(mentorId),
FOREIGN KEY(itemId) REFERENCES item(itemId),
FOREIGN KEY(copartnerId) REFERENCES copartner(copartnerId)
) default charset=utf8;
--从这里开始有问题
--
--注意
--
--
CREATE TABLE school_mentor(
        mentorId INT NOT NULL,
        schoolId INT NOT NULL，
        certificationTime DATETIME NOT NULL,
        FOREIGN KEY(schoolId) REFERENCES school(schoolId),
        FOREIGN KEY(mentorId) REFERENCES mentor(mentorId)
) default charset=utf8;


CREATE TABLE item_mentor(
        itemId INT NOT NULL,
        mentorId INT NOT NULL,
        invitationContent VARCHAR(100) NOT NULL,
        isTrue VARCHAR(5) NOT NULL DEFAULT ‘否’,
        invitationTime DATETIME NOT NULL,
        FOREIGN KEY(itemId) REFERENCES item(itemId),
        FOREIGN KEY(mentorId) REFERENCES mentor(mentorId)
) default charset=utf8;


CREATE TABLE mentor_item(
       itemId  INT NOT NULL,
       mentorId INT NOT NULL,
       invitationContent VARCHAR(100) NOT NULL,
       isTrue VARCHAR(5) NOT NULL DEFAULT ‘否’,
       invitationTime DATETIME NOT NULL,
       FOREIGN KEY(itemId) REFERENCES item(itemId),
       FOREIGN KEY(mentorId) REFERENCES mentor(mentorId)
) default charset=utf8; 


CREATE TABLE mentor_mentor(
       pmentorId  INT NOT NULL,
       mentorId INT NOT NULL,
       invitationContent VARCHAR(100) NOT NULL,
       isTrue VARCHAR(5) NOT NULL DEFAULT ‘否’,
       invitationTime DATETIME NOT NULL,
       FOREIGN KEY(pmentorId) REFERENCES mentor(mentorId),
       FOREIGN KEY(mentorId) REFERENCES mentor(mentorId)
   ) default charset=utf8; 
   
   CREATE TABLE item_item(
       pitemId INT NOT NULL,
       itemId INT NOT NULL,
       invitationContent VARCHAR(100) NOT NULL,
       isTrue VARCHAR(5) NOT NULL DEFAULT ‘否’,
       invitationTime DATETIME NOT NULL,
       FOREIGN KEY(pitemId) REFERENCES item(itemId),
       FOREIGN KEY(itemId) REFERENCES item(itemId)
) default charset=utf8; 