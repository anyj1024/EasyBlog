# 设置 Docker 主机为远程 Docker 主机
export DOCKER_HOST="tcp://1.94.98.78:2375"

# 1. 构建后端
cd backend
printf "\n[INFO] Cleaning and packaging backend...\n"
mvn clean package -DskipTests

# 2. 构建前端
cd ../frontend
printf "\n[INFO] Installing dependencies and building frontend...\n"
npm install
npm run build

# 3. 删除旧的 Docker 镜像（包括后端和前端）

# 打印远程 Docker 主机上的所有镜像（删除前）
printf "\n[INFO] Listing all Docker images on remote Docker host before removal...\n"
docker images

printf "\n[INFO] Removing old Docker images on remote Docker host...\n"
old_backend_images=$(docker images -q --filter "reference=easyblog-backend:latest" || true)
old_frontend_images=$(docker images -q --filter "reference=easyblog-frontend:latest" || true)

if [ -n "$old_backend_images" ]; then
    docker rmi -f $old_backend_images
    printf "[INFO] Old backend Docker images removed successfully.\n"
else
    printf "[INFO] No old backend Docker images found.\n"
fi

if [ -n "$old_frontend_images" ]; then
    docker rmi -f $old_frontend_images
    printf "[INFO] Old frontend Docker images removed successfully.\n"
else
    printf "[INFO] No old frontend Docker images found.\n"
fi

# 打印远程 Docker 主机上的所有镜像（删除前）
printf "\n[INFO] Listing all Docker images on remote Docker host before removal...\n"
docker images

# 4. 运行 Docker Compose
cd ..
printf "\n[INFO] Running Docker Compose on remote Docker host...\n"
if [ -f "docker-compose.yml" ]; then
    docker-compose up -d
else
    echo "[ERROR] docker-compose.yml file not found in the current directory."
    exit 1
fi

printf "\n[INFO] All tasks completed successfully.\n"
