# 1. 使用官方的 OpenJDK 作为基础镜像
FROM openjdk:24-jdk-bullseye

# 2. 设置工作目录
WORKDIR /app

# 3. 复制构建产物到镜像中
COPY target/backend.jar app.jar

# 4. 暴露应用端口
EXPOSE 8089

# 5. 运行应用
ENTRYPOINT ["java", "-jar", "app.jar"]
