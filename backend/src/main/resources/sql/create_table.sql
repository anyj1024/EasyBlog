CREATE TABLE user_info
(
    id           INT AUTO_INCREMENT PRIMARY KEY,     -- 主键，自增ID
    username     VARCHAR(50)  NOT NULL,              -- 用户名
    password     VARCHAR(255) NOT NULL,              -- 密码，建议使用加密存储
    phone_number VARCHAR(15)  NOT NULL UNIQUE,       -- 唯一手机号
    ip_address   VARCHAR(45),                        -- 客户端IP地址，IPv6最多45个字符
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 注册时间，自动设置
);

ALTER TABLE user_info
    ADD UNIQUE INDEX unique_username (username),
    ADD UNIQUE INDEX unique_phone_number (phone_number);

