package kr.ac.mjc.blog01.repository;

import kr.ac.mjc.blog01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    public User save(User user);        //데이터베이스에 사용자정보저장
    public Optional<User> findOneByEmail(String email);     //가입된 이메일인지 체크

    //로그인시 아이디와 비밀번호가 동시에 만족할때
    public Optional<User> findOneByEmailAndPassword(String email,String password);

}
