package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Dto.UserDto;
import com.koshijo.doanmobile_be.Entity.User;
import com.koshijo.doanmobile_be.Exception.UserRegistrationException;
import com.koshijo.doanmobile_be.Repository.UserRepository;
import com.koshijo.doanmobile_be.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(UserDto userDto) {
        try {
            Optional<User> existUser=userRepository.findUserByEmail(userDto.getEmail());
            if(existUser.isPresent()){
                throw new UserRegistrationException("Người dùng đã tồn tại.");
            }
            User user = new User();
            user.setUserName(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            return user;
        } catch (UserRegistrationException ex){
            throw ex;
        }catch (Exception ex) {
            throw new UserRegistrationException("Lỗi khi đăng ký người dùng.");
        }
    }

    @Override
    public Boolean login(UserDto userDto) {
        Optional<User> user = userRepository.findUserByEmailAndPassword(userDto.getEmail(),userDto.getPassword());
        return user.isPresent();
    }
}
