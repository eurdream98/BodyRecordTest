CREATE TABLE IF NOT EXISTS member (
    id BIGINT NOT NULL AUTO_INCREMENT,
    social_login_id VARCHAR(30) NOT NULL,
    nickname VARCHAR(20) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    last_login_date DATETIME(6) NOT NULL,
    created_at DATETIME(6),
    modified_at DATETIME(6),
    status ENUM ('ACTIVE','DELETED','DORMANT'),
    PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS exercise_log (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    member_id BIGINT,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,
    status ENUM ('DELETED','USABLE') NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES member (id)
) engine=InnoDB;
