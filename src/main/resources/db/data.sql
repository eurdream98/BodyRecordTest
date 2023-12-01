CREATE TABLE IF NOT EXISTS `exercise` (
                                          `exercise_code`   int   AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                          `exercise_name`   varchar(45)   NOT NULL,
                                          `exercise_weight`   float   NULL,
                                          `exercise_count`   int   NULL,
                                          `exercise_time`   time   NULL,
                                          `exercise_image_path`  varchar(1000)   NULL   ,
                                          `exercise_image_name`  varchar(1000)   NULL,
                                          `exercise_share`   boolean   NOT NULL   ,
                                          `exercise_date`   date   NOT NULL,
                                          `member_code`   bigint   NOT NULL   ,
                                          `created_at`   datetime(6)   NULL,
                                          `modified_at`   datetime(6)   NULL,
                                          `state`   enum('DELETED','USEABLE')   NOT NULL,
                                          FOREIGN KEY (`member_code`)REFERENCES `member` (`member_code`)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS `member` (
                                        `member_code` bigint PRIMARY KEY AUTO_INCREMENT,
                                        `member_socialid` VARCHAR(30) NOT NULL,
                                        `member_name` VARCHAR(20) NOT NULL,
                                        `member_nickname` VARCHAR(255) NOT NULL,
                                        `role` VARCHAR(20),
                                        `state` VARCHAR(20),
                                        `created_at` DATETIME,
                                        `modified_at` DATETIME,
                                        CONSTRAINT unique_member_name UNIQUE (member_name)

)engine=InnoDB;

CREATE TABLE IF NOT EXISTS `challenge` (
                                           `challenge_code`   int   AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                           `challenge_title`   varchar(45)   NOT NULL,
                                           `challenge_content`   varchar(500)   NULL,
                                           `challenge_startdate`   date   NOT NULL,
                                           `challenge_enddate`   date   NOT NULL,
                                           `report_id`   int   NULL,
                                           `member_code`   bigint   NOT NULL   ,
                                           `challengecategory_code`   int   NOT NULL,
                                           `created_at`   datetime(6)   NULL,
                                           `modified_at`   datetime(6)   NULL,
                                           `state`   enum('USEABLE','DELETED','ENDED')   NOT NULL,
                                           FOREIGN KEY (`member_code`)REFERENCES `member` (`member_code`),
                                           FOREIGN KEY (`challengecategory_code`)REFERENCES `challenge_category` (`challengecategory_code`)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS `challenge_category` (
                                                    `challengecategory_code`   int   AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                                    `challengecategory_name`   varchar(45)   NOT NULL,
                                                    `created_at`   datetime(6)   NULL,
                                                    `modified_at`   datetime(6)   NULL,
                                                    `state`   enum('DELETED','USEABLE')   NOT NULL
)engine = InnoDB;

CREATE TABLE IF NOT EXISTS `body` (
                                      `body_code`   int AUTO_INCREMENT PRIMARY KEY   NOT NULL,
                                      `weight`   float   NOT NULL   ,
                                      `fat`   float   NOT NULL   ,
                                      `muscle`   float   NOT NULL   ,
                                      `member_code`   bigint   NOT NULL   ,
                                      `created_at`   datetime(6)   NULL,
                                      `modified_at`   datetime(6)   NULL,
                                      `state`   enum('DELETED','USEABLE')   NOT NULL,
                                      FOREIGN KEY (`member_code`) REFERENCES `member` (`member_code`)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS `report` (
                                        `report_code`   iNT   AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                        `report_date`   date   NULL,
                                        `report_content`   varchar(500)   NULL,
                                        `challenge_code`   int   NULL,
                                        `member_code`   bigint   NOT NULL,
                                        `reportcategory_code`   int   NOT NULL,
                                        `created_at`   datetime(6)   NULL,
                                        `modified_at`   datetime(6)   NULL,
                                        `state`   enum('UNPROCESSED','PROCESSED','RETURNED')   NOT NULL,
                                        `challengecer_code`   int   NULL,
                                        FOREIGN KEY (`challenge_code` )REFERENCES `challenge` ( `challenge_code` ),
                                        FOREIGN KEY (`member_code`)REFERENCES `member` (`member_code`),
                                        FOREIGN KEY (`reportcategory_code`)REFERENCES `report_category` (`reportcategory_code`),
                                        FOREIGN KEY (`challengecer_code`)REFERENCES `challenge_certification` (`challengecer_code`)
)engine = InnoDB;

CREATE TABLE IF NOT EXISTS `report_category` (
                                                 `reportcategory_code`   int AUTO_INCREMENT PRIMARY KEY   NOT NULL,
                                                 `reportcategory_name`   varchar(45)   NULL,
                                                 `created_at`   datetime(6)   NULL,
                                                 `modified_at`   datetime(6)   NULL,
                                                 `state`   enum('USEABLE','DELETED')   NOT NULL
)engine = InnoDB;

CREATE TABLE IF NOT EXISTS `challenge_participate` (
                                                       `challengeparticipate_code`   int AUTO_INCREMENT PRIMARY KEY   NOT NULL,
                                                       `member_code`   bigint   NOT NULL   ,
                                                       `challenge_code`   int   NOT NULL,
                                                       `created_at`   datetime(6)   NULL,
                                                       `modified_at`   datetime(6)   NULL,
                                                       `state`   enum('JOIN','LEAVE')   NOT NULL,
                                                       FOREIGN KEY ( `member_code`)REFERENCES `member` (`member_code`),
                                                       FOREIGN KEY (`challenge_code` )REFERENCES `challenge` (`challenge_code`)

)engine = InnoDB;

CREATE TABLE IF NOT EXISTS `challenge_certification` (
                                                         `challengecer_code`   int   AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                                         `challenge_image`   varchar(200)   NULL,
                                                         `challenge_code`  int   NOT NULL,
                                                         `created_at`   datetime(6)   NULL,
                                                         `modified_at`   datetime(6)   NULL,
                                                         `state`   enum('USEABLE','DELETED')   NOT NULL,
                                                         `member_code` bigint   NOT NULL,
                                                         FOREIGN KEY (`challenge_code`)REFERENCES `challenge` (`challenge_code`),
                                                         FOREIGN KEY (`member_code`) REFERENCES `member` (`member_code`)
)engine = InnoDB;

CREATE TABLE IF NOT EXISTS `token` (
                                       `member_code` bigint NOT NULL,
                                       `refresh_token` varchar(255) NOT NULL,
                                       PRIMARY KEY (`member_code`),
                                       CONSTRAINT `FK_member_TO_token_1` FOREIGN KEY (`member_code`) REFERENCES `member` (`member_code`)
)engine = InnoDB;



DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert $$
CREATE PROCEDURE loopInsert()

BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    WHILE i <= 4 DO
            INSERT INTO challenge_category (`challengecategory_code`,`challengecategory_name`,`created_at`,`modified_at`, `state`)
            VALUES (i,concat('챌린지카테고리',i),now(),null,'USEABLE');
            INSERT INTO report_category (`reportcategory_code`, `reportcategory_name`, `created_at`, `modified_at`, `state`)
            VALUES (i,concat('신고카테고리',i),now(),null,'USEABLE');
            SET i = i + 1;
        END WHILE;
    WHILE j <= 20 DO
            INSERT INTO member(`member_socialid`,`member_name`,`member_nickname`, `role`,`state`)
            VALUES (concat('사용자',j,'@gmail.com'),concat('사용자',j),concat('닉네임',j),'ROLE_MEMBER','ACTIVE');
            INSERT INTO body(`weight`,`fat`,`muscle`,`member_code`,`created_at`,`modified_at`,`state`)
            VALUES (j*5, j, j, 1, now(), null, 'USEABLE');
            INSERT INTO exercise(`exercise_name`,`exercise_weight`,`exercise_count`,`exercise_time`,`exercise_share`,`exercise_date`,`member_code`,`created_at`,`modified_at`,`state`)
            VALUES(concat('제목',j), j, j, '2:00:00', 1, now(), 1, now(), null, 'USEABLE');
            INSERT INTO challenge(`challenge_title`, `challenge_content`, `challenge_startdate`, `challenge_enddate`, `report_id`, `member_code`, `challengecategory_code`, `created_at`, `modified_at`, `state`)
            VALUES (concat('챌린지',j), concat('내용',j),'2023-12-01','2023-12-20',1,j,1,now(),null,'USEABLE');
            INSERT INTO challenge_certification(`challenge_image`, `challenge_code`, `created_at`, `modified_at`, `state`, `member_code`)
            VALUES(null, 1, now(), null, 'USEABLE', j);
            INSERT INTO challenge_participate(`member_code`, `challenge_code`, `created_at`, `modified_at`, `state`)
            VALUES(j,j,now(),null,'JOIN');
            INSERT INTO report(`report_date`, `report_content`, `challenge_code`, `member_code`, `reportcategory_code`, `created_at`, `modified_at`, `state`, `challengecer_code`)
            VALUES (now(),concat('내용',j),1,j,1,now(),null,'UNPROCESSED',1);
            SET j = j + 1;
        END WHILE;

END$$
DELIMITER $$

CALL loopInsert;
