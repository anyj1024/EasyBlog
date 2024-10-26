package com.anyj.backend.service.impl;

import com.anyj.backend.dto.request.RegisterRequest;
import com.anyj.backend.dto.response.CommonResponse;
import com.anyj.backend.mapper.UserMapper;
import com.anyj.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public CommonResponse register(RegisterRequest request) {
        int count = Math.max(userMapper.countByUserName(request.getUsername()), userMapper.countByPhoneNumber(request.getPhoneNumber()));
        if (count > 0) {
            return CommonResponse.fail(400, "用户名或手机号已存在");
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encryptedPassword);

        System.out.println(request);
        userMapper.insertUser(request);
        return CommonResponse.success("注册成功");
    }

    @Override
    public CommonResponse login(RegisterRequest request) {
        String encryptedPassword = userMapper.findPasswordByUserName(request.getUsername());
        if (encryptedPassword == null) {
            return CommonResponse.fail(404, "用户名不存在");
        }
        if (!passwordEncoder.matches(request.getPassword(), encryptedPassword)) {
            return CommonResponse.fail(400, "密码错误");
        }

        return CommonResponse.success("登录成功");
    }
}
