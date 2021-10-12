package com.backpacker.homework.controller;

import com.backpacker.homework.controller.dto.ErrorMessages;
import com.backpacker.homework.controller.dto.MemberResponseDto;
import com.backpacker.homework.controller.dto.MemberSaveDto;
import com.backpacker.homework.domain.Member;
import com.backpacker.homework.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원 가입
     */
    @PostMapping("/member/join")
    @ResponseBody
    public ResponseEntity join(@RequestBody @Valid MemberSaveDto memberSaveDto) {
        try {
            Long result = memberService.join(memberSaveDto);
            return ResponseEntity.ok().body(result);
        } catch (IllegalStateException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("BAD_STATE", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorMessages.builder().errors(errors).build());
        }
    }

    /**
     * 로그인
     */
    @PostMapping("/member/login")
    @ResponseBody
    public ResponseEntity login(@ApiIgnore HttpSession session, @RequestParam String email, @RequestParam String password) {
        try {
            Member member = memberService.login(email, password);
            session.setAttribute("loginMember", member);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("BAD_REQUEST", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorMessages.builder().errors(errors).build());
        }
    }

    /**
     * 로그아웃
     */
    @PostMapping("/member/logout")
    @ResponseBody
    public ResponseEntity logout(@ApiIgnore HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    /**
     * 단일 회원 상세 정보 조회
     */
    @GetMapping("/member/{uid}")
    @ResponseBody
    public ResponseEntity<Member> member(@PathVariable Long uid) {
        Member member = memberService.findMember(uid);
        return ResponseEntity.ok().body(member);
    }

    /**
     * 여러 회원 목록 조회
     */
    @GetMapping("/members")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filterName", value = "검색조건(email, name)"),
            @ApiImplicitParam(name = "filterValue", value = "검색내용"),
            @ApiImplicitParam(name = "page", value = "현재 페이지")
    })
    public ResponseEntity<Page<MemberResponseDto>> members(@RequestParam(defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "") String filterName, @RequestParam(required = false, defaultValue = "") String filterValue) {

        Pageable pageable = PageRequest.of(page - 1, 5);

        return ResponseEntity
                .ok()
                .body(memberService.findMembers(pageable, filterName, filterValue));
    }
}
