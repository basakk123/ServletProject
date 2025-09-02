package com.multicampus.biz.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// TO (Transfer Object) - 사용자 정보를 전달하는 클래스
@Getter
@Setter
@ToString
public class UserVO {
    private String id;
    private String password;
    private String name;
    private String role;
}
