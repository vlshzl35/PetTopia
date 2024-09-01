package com.sh.pettopia.Hojji.user.member.entity;


import lombok.Getter;

@Getter
public enum Authority {
    ROLE_ADMIN("관리자"),
    ROLE_MEMBER("회원"),
    ROLE_SITTER("시터");

    private final String authorityKor;

    Authority(String authorityKor) {
        this.authorityKor = authorityKor;
    }
}
