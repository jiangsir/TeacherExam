ALTER TABLE  `exams` CHANGE  `applybegin`  `applybegin` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE  `exams` CHANGE  `applyend`  `applyend` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE  `exams` CHANGE  `firsttest`  `firsttest` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE  `exams` CHANGE  `secondtest`  `secondtest` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE  `exams` CHANGE  `scoreboardbegin`  `scoreboardbegin` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE  `exams` CHANGE  `note`  `note` TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;
ALTER TABLE  `exams` ADD  `formnote` TEXT NOT NULL AFTER  `note`;
-- ALTER TABLE `applications` DROP COLUMN `subject`;
ALTER TABLE `exams`	CHANGE COLUMN `deadline` `deadline` DATE NOT NULL DEFAULT '2000-01-01' AFTER `applyend`;
ALTER TABLE `exams` ADD COLUMN `startline` DATE NOT NULL DEFAULT '2000-01-01' AFTER `deadline`;
ALTER TABLE `exams` ADD COLUMN `step2scoreboardbegin` TIMESTAMP NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() AFTER `scoreboardbegin`;
ALTER TABLE `applications` ADD COLUMN `step2totalscore` DOUBLE NOT NULL DEFAULT '0' AFTER `result`;
ALTER TABLE `applications` ADD COLUMN `step2result` VARCHAR(100) NOT NULL COLLATE 'utf8_general_ci' AFTER `step2totalscore`;
ALTER TABLE `exams`	CHANGE COLUMN `startline` `startline` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() AFTER `deadline`;
ALTER TABLE `exams`	ADD COLUMN `uploadform` TEXT NOT NULL COLLATE 'utf8_general_ci' AFTER `examroom`;
UPDATE `users` SET passwd=md5(passwd) WHERE LENGTH(passwd)!=32;
ALTER TABLE `exams`	ADD COLUMN `visible` INT NOT NULL DEFAULT '1' AFTER `isapplicationalwayseditable`;

