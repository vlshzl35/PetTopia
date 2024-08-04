package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity(name="reply")
@Table(name = "tbl_reply")

public class Reply {
    // 회원리뷰에 대한 답글
    // 필요한 속성, 아딴 게시글에. 회원의 아이디, 펫시터의 아이디, 펫시터가 등록한 댓글
    @Id
    private Long id;

    @Column(name = "reply_text")
    private String replyText; // 답글 내용

    // 회원 ID 알아야 하고
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "멤버 테이블 명이 와야 한다",
            joinColumns = @JoinColumn(name = "question_id") // fk컬럼명
    )
    @Column(name = "text") // String이 저장될 컬럼명
    @OrderColumn(name = "idx") // List의 인덱스를 저장할 컬럼명
    private List<String> choices;

    // 멤버 ID 알아야 하고 -
    @ManyToOne
    // 현재 테이블에 petsitter_id이고, PetSitter의 petsitter_id를 외래키로 잡는다
    @JoinColumn(name = "petsitter_id")
    private PetSitter petSitter;


    public void changeReplyText(String replyText) {
        this.replyText=replyText;
    }
    public void registerReplyText(String replyText) {
        this.replyText=replyText;
    }





}
