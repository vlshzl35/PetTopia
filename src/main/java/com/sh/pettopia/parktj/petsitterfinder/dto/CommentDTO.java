package com.sh.pettopia.parktj.petsitterfinder.dto;


import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.CommentEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private LocalDate commentCreatedTime;
    private Long postId;
    private Long memberId;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long postId) {
        return new CommentDTO(
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setId(commentEntity.getId()),
//        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
//        commentDTO.setCommentContents(commentEntity.getCommentContents());
//        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
////        commentDTO.setPostId(commentEntity.getCareRegistration().getPostId());
//        commentDTO.setPostId(postId);
//        commentDTO.setMemberId(commentEntity.getMemberId());
                commentEntity.getId(),
                commentEntity.getCommentWriter(),
                commentEntity.getCommentContents(),
                commentEntity.getCreatedTime(),
                commentEntity.getCareRegistration().getPostId(),
                commentEntity.getMemberId());


//        return commentDTO;
    }
}
