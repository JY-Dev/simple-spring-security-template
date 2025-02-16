CREATE TABLE ACCOUNT
(
    ID       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    USER_ID  BIGINT       NOT NULL COMMENT '유저 ID',
    LOGIN_ID VARCHAR(255) NOT NULL COMMENT '로그인 ID',
    PASSWORD VARCHAR(255) NOT NULL COMMENT '비밀번호',
    CT_UTC   TIMESTAMP    NOT NULL COMMENT '생성 시간',
    UT_UTC   TIMESTAMP    NOT NULL COMMENT '업데이트 시간',
    UNIQUE (LOGIN_ID),
    UNIQUE (USER_ID)
);