package com.sh.pettopia.parktj.petsitterfinder.dto;


import com.sh.pettopia.parktj.petsitterfinder.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long postId;
    private LocalDateTime commentCreatedTime;
    private Long memberId;


    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long postId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
//        commentDTO.setPostId(commentEntity.getCareRegistration().getPostId());
        commentDTO.setPostId(postId);

        return commentDTO;
    }
}
