<template>
  <div id="app">
    <!-- 顶部状态栏 -->
    <el-header class="header">
      <div class="header-left">
        <img src="@/assets/logo.png" alt="EasyBlog Logo" class="logo"/>
      </div>

      <el-menu mode="horizontal" default-active="1" class="nav-menu">
        <el-menu-item index="1" @click="testData">首页</el-menu-item>
        <el-menu-item index="2">分类</el-menu-item>
        <el-menu-item index="3">标签</el-menu-item>
        <el-menu-item index="4">归档</el-menu-item>
        <el-menu-item index="5">留言</el-menu-item>
      </el-menu>

      <div class="header-right">
        <!-- 搜索框 -->
        <el-input placeholder="搜索" class="search-input" v-model="searchQuery" @keyup.enter="handleSearch"/>

        <!-- 登录按钮或用户名 -->
        <div v-if="!loggedInUser">
          <el-button type="primary" @click="showLoginDialog">登录/注册</el-button>
        </div>
        <div v-else>
          <el-dropdown>
            <span class="el-dropdown-link">
              {{ loggedInUser }} <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu>
              <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </el-header>

    <!-- 轮播图 -->
    <el-carousel :interval="5000" arrow="always" height="70vh" class="carousel">
      <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
        <div class="carousel-item-container">
          <img :src="item" :alt="'Image ' + index" class="carousel-image"/>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>

  <!-- 登录弹框 -->
  <el-dialog v-model="loginDialogVisible" width="25%" :center="true">
    <el-form :model="loginForm" class="login-form">
      <el-form-item label="账号" class="form-item">
        <el-input v-model="loginForm.username" class="form-input"></el-input>
      </el-form-item>
      <el-form-item label="密码" class="form-item">
        <el-input type="password" v-model="loginForm.password" class="form-input"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="handleLogin">登录</el-button>
        <el-button type="text" class="register-button" @click="showRegisterDialog">注册</el-button>
      </div>
    </template>
  </el-dialog>

  <!-- 注册弹框 -->
  <el-dialog v-model="registerDialogVisible" width="25%" :center="true">
    <el-form :model="registerForm" class="register-form">
      <el-form-item label="用户名" class="form-item">
        <el-input v-model="registerForm.username" class="form-input"></el-input>
      </el-form-item>
      <el-form-item label="密码" class="form-item">
        <el-input type="password" v-model="registerForm.password" class="form-input"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" class="form-item">
        <el-input type="password" v-model="registerForm.confirmPassword" class="form-input"></el-input>
      </el-form-item>
      <el-form-item label="手机号码" class="form-item">
        <el-input v-model="registerForm.phone" class="form-input"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="handleRegister">注册</el-button>
        <el-button type="text" @click="showLoginDialog">返回登录</el-button>
      </div>
    </template>
  </el-dialog>

  <div class="home-data">
    <p>{{ homeData }}</p>
  </div>
</template>

<script>
import axios from 'axios';
import { getConfig } from '@/config';

export default {
  name: 'App',
  data() {
    return {
      carouselItems: [],
      homeData: '',
      searchQuery: '', // 搜索框的输入值
      loginDialogVisible: false, // 控制登录弹框显示
      registerDialogVisible: false, // 控制注册弹框显示
      loginForm: {
        username: '',
        password: '',
      },
      registerForm: {
        username: '',
        password: '',
        confirmPassword: '',
        phoneNumber: '',  // 手机号码
      },
      captchaUrl: 'path/to/captcha', // 验证码图片URL
      loggedInUser: null, // 用于存储已登录的用户信息
    };
  },
  created() {
    this.loadImages();
  },
  methods: {
    loadImages() {
      const imagesContext = require.context('@/assets/images', false, /\.png$/);
      this.carouselItems = imagesContext.keys().map(imagesContext);
    },
    async testData() {
      try {
        const params = new URLSearchParams();
        params.append('s', 'Hello World!');
        const response = await axios.post(`${getConfig().baseURL}/test/get`, params);  // 调用后端接口
        console.log('接口返回的数据是： ', response.data);
        this.homeData = response.data;
      } catch (error) {
        console.error('Error fetching home data:', error);
      }
    },
    handleSearch() {
      console.log('搜索内容:', this.searchQuery);
    },
    showLoginDialog() {
      this.loginDialogVisible = true;
      this.registerDialogVisible = false;  // 隐藏注册弹框
    },
    showRegisterDialog() {
      this.registerDialogVisible = true;
      this.loginDialogVisible = false;  // 隐藏登录弹框
    },
    async handleLogin() {
      try {
        const response = await axios.post(`${getConfig().baseURL}/auth/login`, {
          username: this.loginForm.username,
          password: this.loginForm.password
        });

        if (response.data.code === 200) {
          this.$message.success("登录成功");
          this.loginDialogVisible = false;

          // 保存已登录的用户名
          this.loggedInUser = this.loginForm.username;

          // 清空表单
          this.loginForm.username = '';
          this.loginForm.password = '';
        } else {
          this.$message.error(response.data.message || "登录失败");
        }
      } catch (error) {
        console.error('登录失败:', error);
        this.$message.error("登录失败，请检查用户名和密码");
      }
    },
    async handleRegister() {
      // 检查密码是否一致
      if (this.registerForm.password !== this.registerForm.confirmPassword) {
        this.$message.error("两次输入的密码不一致");
        return;
      }

      try {
        const response = await axios.post(`${getConfig().baseURL}/auth/register`, {
          username: this.registerForm.username,
          password: this.registerForm.password,
          phoneNumber: this.registerForm.phoneNumber,  // 手机号码传递给后端
        });

        if (response.data.code === 200) {
          this.$message.success("注册成功，请登录");
          this.registerDialogVisible = false;
          this.loginDialogVisible = true;  // 注册成功后显示登录弹框

          // 清空注册表单
          this.registerForm.username = '';
          this.registerForm.password = '';
          this.registerForm.confirmPassword = '';
          this.registerForm.phoneNumber = '';
        } else {
          this.$message.error(response.data.message || "注册失败");
        }
      } catch (error) {
        console.error('注册失败:', error);
        this.$message.error("注册失败，请重试");
      }
    },
    handleLogout() {
      this.loggedInUser = null;
      this.$message.success("已退出登录");
    },
    refreshCaptcha() {
      this.captchaUrl = 'path/to/new/captcha'; // 更新验证码图片的URL
    }
  },
};
</script>

<style scoped>
/* 顶部状态栏样式 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  color: #333;
  height: 60px;
  border-bottom: 1px solid #eaeaea;
  box-sizing: border-box;
}

.header-left {
  display: flex;
  align-items: center;
  flex-grow: 1;
}

.header-right {
  display: flex;
  align-items: center;
  flex-grow: 1;
  justify-content: flex-end;
}

.logo {
  height: 30px;
  object-fit: contain;
}

.nav-menu {
  display: flex;
  justify-content: center;
  flex-grow: 2;  /* 让菜单在中间 */
}

.search-input {
  width: 200px;
  margin-right: 20px;
}

/* 弹框样式 */
.el-dialog {
  border-radius: 10px;
}

/* 表单居中样式 */
.login-form, .register-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-item {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.form-item label {
  flex-basis: 20%;
  text-align: right;
}

.form-input {
  flex-basis: 75%;
}

/* 底部按钮布局 */
.dialog-footer {
  display: flex;
  justify-content: center;
  gap: 20px;
  padding: 10px;
  width: 100%;
}

/* 注册按钮样式 */
.register-button {
  color: #409EFF;
}

/* 轮播图图片样式 */
.carousel-item-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.carousel-image {
  width: 95%;
  height: 85%;
  object-fit: cover;
  object-position: top;
  border-radius: 20px;
  margin-top: -60px;
}

.carousel {
  margin-top: 0;
}
</style>
