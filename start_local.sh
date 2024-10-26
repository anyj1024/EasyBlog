#!/bin/bash

# 启动脚本，确保前后端在本地快速启动

# Step 1: 安装并打包前端
echo "Step 1: Installing frontend dependencies..."
cd frontend
npm install

echo "Step 2: Building frontend..."
npm run build

# Step 3: 返回项目根目录
cd ..

# Step 4: 启动后端 BackendApplication
echo "Step 3: Starting backend application..."
cd backend
mvn clean install  # 如果需要编译后端项目
java -jar target/*.jar &  # 启动编译后的 BackendApplication

# Step 5: 提示用户访问
echo "Frontend and Backend are up and running."
echo "You can now access your frontend at http://localhost:<your-frontend-port>"
echo "And your backend at http://localhost:<your-backend-port>"
