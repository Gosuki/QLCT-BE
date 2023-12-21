package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.ChangePasswordRequest;
import com.koshijo.doanmobile_be.Dto.UserDto;
import com.koshijo.doanmobile_be.Entity.User;

public interface IUserService {
    UserDto createUser(UserDto userDto);
    UserDto login(UserDto userDto);
    UserDto getUser(Long userId);
    UserDto changePassword(Long userId, ChangePasswordRequest changePasswordRequest);

    UserDto forgotPassword(String email);
}
