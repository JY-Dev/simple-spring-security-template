CREATE TABLE `USER`
(
    ID     BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    NAME   VARCHAR(255) NOT NULL COMMENT '이름',
    CT_UTC TIMESTAMP    NOT NULL COMMENT '생성 시간',
    UT_UTC TIMESTAMP    NOT NULL COMMENT '업데이트 시간'
);