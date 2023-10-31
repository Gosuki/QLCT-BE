package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.UserDto;
import com.koshijo.doanmobile_be.Entity.User;
import com.koshijo.doanmobile_be.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/v1")
public class UserController {
    private final IUserService iUserService;
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> Login(@RequestBody UserDto userDto) {
        if (iUserService.login(userDto)){
            return ResponseEntity.ok(new BaseResponse(
                    HttpStatus.OK.value(),null,
                    "Login completed successfully "));
        }else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Login Fail"));
        }

    }
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> Register(@RequestBody UserDto userDto) {
        User user = iUserService.createUser(userDto);
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), user,"Register completed successfully"));
    }
}
