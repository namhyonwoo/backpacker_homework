package com.backpacker.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long uid;
    @Column
    String email;
    @Column
    String name;
    @Column
    String nickname;
    @Column
    String phone;
    @Column
    String password;
    @Column
    String gender;
    @Column
    LocalDateTime createdAt;

    @Builder
    public Member(String email, String name, String nickname, String phone, String password, String gender) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.createdAt = LocalDateTime.now();
    }
}
