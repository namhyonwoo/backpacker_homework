package com.backpacker.homework.controller.dto;

import com.backpacker.homework.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class MemberSaveDto {

    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "(^[가-힣]{2,10}$)|(^[a-zA-Z]{1,10}\\s?[a-zA-Z]{1,10})$", message = "이름은 한글,영문 대소문자만 가능합니다")
    private String name;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "^[a-z]+$", message = "닉네임은 영문 소문자만 가능합니다")
    private String nickname;

    @NotNull
    @Length(min = 10, message = "최소길이는 10입니다")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@$%^&(){}\\[\\]:;<>,.?\\/~_+-=|])[0-9a-zA-Z!@$%^&(){}\\[\\]:;<>,.?\\/~_+-=|]{10,}$", message = "비밀번호는 영문대문자,소문자 특수문자,숫자가 각각 1개 이상씩 포함되어야합니다")
    private String password;

    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "^\\d{5,}$",message = "휴대전화는 -를 제외한 숫자야여야합니다")
    private String phone;

    @NotNull
    @Length(max = 100)
    @Email(message = "이메일 형식이어야 합니다")
    private String email;

    private String gender;

    public Member toEntity(){
        return Member.builder().email(email)
                .name(name)
                .nickname(nickname)
                .phone(phone)
                .password(password)
                .gender(gender).build();
    }

    @Builder
    public MemberSaveDto(String email, String name, String nickname, String phone, String password, String gender) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
    }
}
