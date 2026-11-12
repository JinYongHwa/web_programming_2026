package kr.ac.mjc.blog01.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog01.dto.UserDto;
import kr.ac.mjc.blog01.dto.UserResponse;
import kr.ac.mjc.blog01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user/join")
    public ResponseEntity<UserResponse> join(@RequestBody UserDto userDto){
        UserResponse response=userService.join(userDto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/api/user/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserDto userDto, HttpSession session){

        UserResponse response= userService.login(userDto);
        if(response.isSuccess()){       //로그인 성공시
            //로그인된 사용자 이메일 세션에 저장
            session.setAttribute("userId",response.getUser().getEmail());
        }

        return ResponseEntity.ok(response);
    }
}
