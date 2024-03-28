package com.buildoc.buildocDemo.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String typeToken="Bearer ";

    public AuthResponseDto(String accessToken){
        this.accessToken=accessToken;
    }
}
