DROP DATABASE IF EXISTS snapdragon;
CREATE DATABASE snapdragon;

USE snapdragon;

CREATE TABLE Permissions (
	`PermissionsId` int NOT NULL AUTO_INCREMENT,
	`EditBlogEntries` BOOL NOT NULL,
	`EditUsers` BOOL NOT NULL,
	`EditStaticContent` BOOL NOT NULL,
	`EditComments` BOOL NOT NULL,
	`AddBlogEntries` BOOL NOT NULL,
	PRIMARY KEY (`PermissionsId`)
);

CREATE TABLE User (
	`UserId` int NOT NULL AUTO_INCREMENT,
	`UserName` varchar(30) NOT NULL,
	`PenName` varchar(30) NOT NULL,
	`Password` varchar(30) NOT NULL,
	`Email` varchar(50) NOT NULL,
	`PermissionsId` int NOT NULL,
	PRIMARY KEY (`UserId`),
	FOREIGN KEY (`PermissionsId`) REFERENCES Permissions(`PermissionsId`) 
);

CREATE TABLE BlogEntry (
	`EntryId` int NOT NULL AUTO_INCREMENT,
	`EntryName` varchar(30) NOT NULL,
	`EntryBody` blob NOT NULL,
	`DateAdded` timestamp NOT NULL,
	`DateModified` timestamp NOT NULL,
	`AuthorId` int(30) NOT NULL,
	PRIMARY KEY (`EntryId`)
-- FOREIGN KEY (`AuthorId`) REFERENCES User(`UserId`) 
);

CREATE TABLE Tag (
	`TagId` int NOT NULL AUTO_INCREMENT,
	`TagName` varchar(30) NOT NULL ,
	PRIMARY KEY (`TagId`),
	UNIQUE (TagName)
);

CREATE TABLE BlogEntryTag (
	`TagId` int NOT NULL,
	`EntryId` int NOT NULL,
	FOREIGN KEY (`TagId`) REFERENCES Tag(`TagId`),
	FOREIGN KEY (`EntryId`) REFERENCES BlogEntry(`EntryId`) 
);

CREATE TABLE Comments (
	`CommentId` int NOT NULL AUTO_INCREMENT,
	`CommentorName` varchar(30) NOT NULL,
	`CommentBody` text NOT NULL,
	`CommentDate` timestamp NOT NULL,
	`BlogEntryId` int NOT NULL,
	PRIMARY KEY (`CommentId`),
	FOREIGN KEY (`BlogEntryId`) REFERENCES BlogEntry(`EntryId`) 
);

CREATE TABLE Picture(
	`PictureId` int NOT NULL AUTO_INCREMENT,
	`PictureURL` varchar(200),
	`PictureWidth` int,
	`PictureHeight` int,
	`AltText`varchar(200),
	PRIMARY KEY (`PictureId`)
);

CREATE TABLE StaticPage (
	`StaticPageId` int NOT NULL AUTO_INCREMENT,
	`StaticPagePath` varchar(30) NOT NULL,
	`StaticPageTitle` varchar(30) NOT NULL,
	`StaticPageBody` blob NOT NULL,
	`PictureId` int,
	PRIMARY KEY (`StaticPageId`),
	FOREIGN KEY (`PictureId`) REFERENCES Picture(`PictureId`)
);

DELIMITER $$
USE `snapdragon`$$
CREATE PROCEDURE `InsertTag` (IN inTagName VARCHAR(30))
BEGIN
	declare tagCount int;
	select count(TagId) into tagCount from Tag where TagName = inTagName;
	if tagCount = 0 then
		insert into Tag (TagName) values (inTagName);
	end if;
END
$$
DELIMITER ;

DELIMITER $$
USE `snapdragon`$$
CREATE PROCEDURE `InsertBlogEntryTag` (IN inBlogId int, inTagId int)
BEGIN
	declare tagCount int;
	select count(TagId) into tagCount from BlogEntryTag where EntryId = inBlogId and TagId = inTagId;
	if tagCount = 0 then
		insert into BlogEntryTag (EntryId, TagId) values (inBlogId, inTagId);
	end if;
END
$$
DELIMITER ;

DELIMITER $$
USE `snapdragon`$$
CREATE PROCEDURE `updateStaticPath` (IN inPathName VARCHAR(30), inSPID int)
BEGIN
	declare pathCount int;
	select count(StaticPageId) into pathCount from StaticPage where StaticPagePath LIKE CONCAT(inPathName, '_') OR StaticPagePath LIKE inPathName;
	if pathCount = 1 then
		update StaticPage set StaticPagePath = inPathName where StaticPageId = inSPID;
	else
		update StaticPage set StaticPagePath = (CONCAT(inPathName, pathCount)) where StaticPageId = inSPID;
	end if;
END
$$
DELIMITER ;