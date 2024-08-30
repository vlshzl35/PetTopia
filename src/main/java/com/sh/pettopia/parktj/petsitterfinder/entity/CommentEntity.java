package com.sh.pettopia.parktj.petsitterfinder.entity;


import com.sh.pettopia.parktj.petsitterfinder.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column
    private String commentContents;

//   Board:Comment =  1 대 N
//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Long postId;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedTime;

    @Column(name = "member_id")
    private Long memberId;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, CareRegistration careRegistration) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCreatedTime(LocalDate.now());
        commentEntity.setUpdatedTime(LocalDateTime.now());
        commentEntity.setPostId(careRegistration.getPostId());
        commentEntity.setMemberId(commentDTO.getMemberId());

        return commentEntity;
    }
}

