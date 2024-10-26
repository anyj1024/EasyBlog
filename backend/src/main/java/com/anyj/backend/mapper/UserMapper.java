package com.anyj.backend.mapper;

import com.anyj.backend.dto.request.RegisterRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(RegisterRequest registerRequest);

    Integer countByPhoneNumber(String phoneNumber);

    Integer countByUserName(String username);

    String findPasswordByUserName(String username);
}
