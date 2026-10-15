package kr.ac.mjc.first;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FirstController {

    @GetMapping("/")
    public String hello(){
        return "진용화";
    }

    @GetMapping("/student/list")
    public ResponseEntity<ArrayList<Student>> studentList(
            @PathParam("name") String name,
            @PathParam("count") int count){
        ArrayList<Student> list=new ArrayList<Student>();
        for(int i=0;i<count;i++){
            Student student=new Student();  //학생 생성
            student.setName(name+i);
            student.setNumber("202626100"+i);
            student.setDepartment("정보통신공학과");
            list.add(student);      //배열에 학생 추가
        }   //배열안에 학생이 10명 들어감

        ResponseEntity< ArrayList<Student> > response= ResponseEntity.ok(list);

        return response;
    }
}
