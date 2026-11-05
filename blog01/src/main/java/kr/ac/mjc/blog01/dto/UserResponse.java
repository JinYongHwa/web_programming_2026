package kr.ac.mjc.blog01.dto;

import kr.ac.mjc.blog01.model.User;

public class UserResponse {
    private boolean success;    //성공여부
    private String message;     //성공 or 실패시 메세지
    private User user;          //회원가입시 회원정보

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
