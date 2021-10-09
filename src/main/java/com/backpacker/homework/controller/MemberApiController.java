package com.backpacker.homework.controller;

import com.backpacker.homework.controller.dto.ErrorMessages;
import com.backpacker.homework.controller.dto.MemberResponseDto;
import com.backpacker.homework.controller.dto.MemberSaveDto;
import com.backpacker.homework.domain.Member;
import com.backpacker.homework.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;

    @Autowired
    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

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
            errors.put("BAD_REQUEST", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorMessages.builder().errors(errors).build());
        }
    }

    /**
     * 단일 회원 상세 정보 조회
     */
    @GetMapping("/member/{uid}")
    @ResponseBody
    public ResponseEntity<Object> member(@PathVariable Long uid) {
        try {
            Member member = memberService.findMember(uid);
            return ResponseEntity
                    .ok()
                    .body(member);
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("NOT_FOUND", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ErrorMessages.builder().errors(errors).build());
        }

    }

    /**
     * 여러 회원 목록 조회
     */
    @GetMapping("/members")
    @ResponseBody
    public ResponseEntity<Page<MemberResponseDto>> members(@RequestParam(defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "") String filterName, @RequestParam(required = false, defaultValue = "") String filterValue) {

        Pageable pageable = PageRequest.of(page - 1, 5);

        //회원의 마지막 주문을 가져와야한다.
        return ResponseEntity
                .ok()
                .body(memberService.findMembers(pageable, filterName, filterValue));
    }
}
