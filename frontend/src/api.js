import axios from 'axios';

// 创建 axios 实例
const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api', // 后端 Spring Boot 的 API 地址
    headers: {
        'Content-Type': 'application/json',
    },
});

export default {
    // 获取所有博客文章
    getPosts() {
        return apiClient.get('/posts');
    },

    // 创建新的博客文章
    createPost(post) {
        return apiClient.post('/posts', post);
    }
};
