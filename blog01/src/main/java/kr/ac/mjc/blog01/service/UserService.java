package kr.ac.mjc.blog01.service;

import kr.ac.mjc.blog01.dto.UserDto;
import kr.ac.mjc.blog01.dto.UserResponse;
import kr.ac.mjc.blog01.model.User;
import kr.ac.mjc.blog01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //회원가입
    public UserResponse join(UserDto userDto){
        String email=userDto.getEmail();
        UserResponse response=new UserResponse();
        if(email==null|| email.equals("")){    //이메일이 null 이거나 빈값이면 가입불가
            response.setSuccess(false);
            response.setMessage("이메일이 올바르지 않습니다");
            return response;
        }
        Optional<User> optional=userRepository.findOneByEmail(email);
        if( !optional.isEmpty() ){    //이미 가입된 이메일
            response.setSuccess(false);
            response.setMessage("이미 가입된 이메일 입니다");
            return response;
        }

        //여기까지왔으면 가입할수있는상태
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);                  //회원정보 데이터베이스에 저장
        response.setSuccess(true);
        return response;
    }


    //로그인
    public UserResponse login(UserDto userDto){
        UserResponse response=new UserResponse();
        String email=userDto.getEmail();
        String password=userDto.getPassword();
        Optional<User> optional=userRepository.findOneByEmailAndPassword(email,password);
        if(optional.isEmpty()){     //이메일 또는 패스워드가 틀림
            response.setSuccess(false);
            response.setMessage("이메일 또는 비밀번호가 틀렸습니다");
            return response;
        }
        response.setSuccess(true);
        response.setUser(optional.get());
        return response;
    }

    public User getUserInfo(String email){
        Optional<User> optional=userRepository.findOneByEmail(email);
        if(optional.isEmpty()){
            return null;
        }
        else{
            return optional.get();
        }
    }
}
