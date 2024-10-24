<template>
  <div id="app">
    <!-- 顶部状态栏 -->
    <el-header class="header">
      <div class="logo">EasyBlog</div>
      <el-menu mode="horizontal" default-active="1" class="nav-menu">
        <el-menu-item index="1">首页</el-menu-item>
        <el-menu-item index="2">分类</el-menu-item>
        <el-menu-item index="3">标签</el-menu-item>
        <el-menu-item index="4">归档</el-menu-item>
        <el-menu-item index="5">留言</el-menu-item>
      </el-menu>
    </el-header>

    <!-- 轮播图 -->
    <el-carousel :interval="5000" arrow="always" height="70vh" class="carousel">
      <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
        <div class="carousel-item-container">
          <img :src="item" :alt="'Image ' + index" class="carousel-image" />
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      carouselItems: [],
    };
  },
  created() {
    this.loadImages();
  },
  methods: {
    loadImages() {
      // 动态加载 src/assets/images 目录下的所有 .png 文件
      const imagesContext = require.context('@/assets/images', false, /\.png$/);
      this.carouselItems = imagesContext.keys().map(imagesContext);
    },
  },
};
</script>

<style scoped>
/* 全局样式调整 */
html, body, #app {
  margin: 0;
  padding: 0;
  height: 100%;
}

/* 顶部状态栏样式 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f0f0f0; /* 浅色背景 */
  padding: 0 20px;
  color: #333; /* 浅色字体 */
  height: 60px;
}

.logo {
  font-size: 24px;
  font-weight: bold;
  color: #000; /* 字体颜色设置为黑色 */
}

.nav-menu {
  display: flex;
  justify-content: center; /* 标签居中 */
  flex-grow: 1; /* 标签和 logo 之间平分空间 */
}

.nav-menu .el-menu-item {
  color: #333; /* 浅色字体 */
}

/* 轮播图图片样式 */
.carousel-item-container {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center;     /* 垂直居中 */
  height: 100%;            /* 确保容器占据整个轮播图的高度 */
}

.carousel-image {
  width: 95%; /* 设置图片宽度为 95% */
  height: 85%; /* 设置图片高度为 85% */
  object-fit: cover;
  object-position: top; /* 显示图片上半部分 */
  border-radius: 20px; /* 设置圆角 */
  margin-top: -60px; /* 移除可能的上边距 */
}

/* 确保轮播图紧贴状态栏，没有额外的间距 */
.carousel {
  margin-top: 0;
}
</style>
