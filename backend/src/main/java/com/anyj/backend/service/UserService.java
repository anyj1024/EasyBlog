package com.anyj.backend.service;

import com.anyj.backend.dto.request.RegisterRequest;
import com.anyj.backend.dto.response.CommonResponse;

public interface UserService {
    CommonResponse register(RegisterRequest request);

    CommonResponse login(RegisterRequest request);
}
