package com.sh.pettopia.Hojji.community.posts.entity;

import lombok.Getter;

@Getter
public enum Category {
    SHARE("나눠드려요"),
    FRIENDS("강아지 친구 찾기"),
    FREE("자유"),
    NOTICE("공지");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
