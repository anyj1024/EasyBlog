# 使用 Nginx 作为生产环境的基础镜像
FROM nginx:latest

# 设置工作目录
WORKDIR /usr/share/nginx/html

# 复制本地构建好的 dist 文件夹到 Nginx 默认的静态资源目录
COPY dist .

# 暴露端口 8088
EXPOSE 8088

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]
