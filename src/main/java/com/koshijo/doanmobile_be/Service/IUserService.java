package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.UserDto;
import com.koshijo.doanmobile_be.Entity.User;

public interface IUserService {
    User createUser(UserDto userDto);
    Boolean login(UserDto userDto);

}
