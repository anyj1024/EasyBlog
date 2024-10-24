package com.anyj.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        logger.info("正在启动 BackendApplication...");
        SpringApplication.run(BackendApplication.class, args);
        logger.info("BackendApplication 启动成功！");
    }
}
