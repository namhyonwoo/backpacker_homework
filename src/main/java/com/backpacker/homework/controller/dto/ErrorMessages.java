package com.backpacker.homework.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ErrorMessages {
    public Map<String, String> errors;

    @Builder
    public ErrorMessages(Map<String, String> errors) {
        this.errors = errors;
    }
}
