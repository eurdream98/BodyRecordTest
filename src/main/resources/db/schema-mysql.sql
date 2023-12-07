CREATE TABLE IF NOT EXISTS `challenge_category` (
                                                    `challengecategory_code`   int   AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                                    `challengecategory_name`   varchar(45)   NOT NULL,
                                                    `created_at`   datetime(6)   NULL,
                                                    `modified_at`   datetime(6)   NULL,
                                                    `state`   enum('DELETED','USEABLE')   NOT NULL
)engine = InnoDB;

CREATE TABLE IF NOT EXISTS `report_category` (
                                                 `reportcategory_code`   int AUTO_INCREMENT PRIMARY KEY   NOT NULL,
                                                 `reportcategory_name`   varchar(45)   NULL,
                                                 `created_at`   datetime(6)   NULL,
                                                 `modified_at`   datetime(6)   NULL,
                                                 `state`   enum('USEABLE','DELETED')   NOT NULL
)engine = InnoDB;

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



CREATE TABLE IF NOT EXISTS `challenge` (
                                           challenge_code   int   AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                           challenge_title   varchar(45)   NOT NULL,
                                           challenge_content   varchar(500)   NULL,
                                           challenge_startdate   date   NOT NULL,
                                           challenge_enddate   date   NOT NULL,
                                           member_code   bigint   NOT NULL   ,
                                           challengecategory_code   int   NOT NULL,
                                           created_at   datetime(6)   NULL,
                                           modified_at   datetime(6)   NULL,
                                           state   enum('USEABLE','DELETED','ENDED')   NOT NULL,
                                           FOREIGN KEY (`member_code`)REFERENCES `member` (`member_code`),
                                           FOREIGN KEY (`challengecategory_code`)REFERENCES `challenge_category` (`challengecategory_code`)
)engine=InnoDB;


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





CREATE TABLE IF NOT EXISTS `token` (
                                       `member_code` bigint NOT NULL,
                                       `refresh_token` varchar(255) NOT NULL,
                                       PRIMARY KEY (`member_code`),
                                       CONSTRAINT `FK_member_TO_token_1` FOREIGN KEY (`member_code`) REFERENCES `member` (`member_code`)
)engine = InnoDB;













