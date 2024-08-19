package com.sh.pettopia.Hojji.community.posts.dto;


import com.sh.pettopia.Hojji.community.posts.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequestDto {
    private Long postId;
    private Category category;
    private String title;
    private String content;
}
