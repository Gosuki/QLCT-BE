package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.ChangePasswordRequest;
import com.koshijo.doanmobile_be.Dto.UserDto;
import com.koshijo.doanmobile_be.Entity.User;
import com.koshijo.doanmobile_be.Service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService iUserService;
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> Login(@RequestBody UserDto userDto) {
        UserDto dto = iUserService.login(userDto);
        if (dto != null){
            return ResponseEntity.ok(new BaseResponse(
                    HttpStatus.OK.value(),dto, "Login completed successfully "));
        }else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), new Object(),"Login Fail"));
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> getUser(@PathVariable(value = "userId") Long userId) {
        UserDto dto = iUserService.getUser(userId);
        if (dto != null){
            return ResponseEntity.ok(new BaseResponse(
                    HttpStatus.OK.value(),dto, "Get User completed "));
        }else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), new Object(),"Get User Fail"));
        }
    }
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody UserDto userDto) {
        UserDto user = iUserService.createUser(userDto);
        if (user != null){
            return ResponseEntity.ok(
                    new BaseResponse(HttpStatus.OK.value(), user,
                            "Register completed successfully"));
        }else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), new UserDto(),"Register Fail"));
        }
    }
    @PutMapping("/changePassword/{userId}")
    public ResponseEntity<BaseResponse> changePassword(@PathVariable(value = "userId") Long userId, @RequestBody ChangePasswordRequest passwordRequest){
        UserDto user = iUserService.changePassword(userId,passwordRequest);
        if (user != null){
            return ResponseEntity.ok(
                    new BaseResponse(HttpStatus.OK.value(), user,
                            "Change password complete "));
        }else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), new UserDto(),"Change password Fail"));
        }
    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<BaseResponse> forgotPassword(@RequestParam String email){
       UserDto userDto = iUserService.forgotPassword(email);
        if (userDto != null){
            return ResponseEntity.ok(
                    new BaseResponse(HttpStatus.OK.value(),userDto,
                            "Password had send to email " + email));
        }else {
            return ResponseEntity.badRequest().body(new BaseResponse(HttpStatus.BAD_REQUEST.value(), new UserDto(),"Email not found"));
        }
    }
}
