package com.sh.pettopia.petsitter;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "petsitter")
@Table(name = "tbl_petsitter")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitterPost {
    // 펫시터가 홍보글을 올렸을 때

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// db가 auto_increment해줌
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Column(name = "post_url") // String이 저장될 컬럼명 // 홍보글은 단 한개만 등록할 수 있기 때문에 이렇게 썼다
    private String  url;

    // 본인회원 Id; = 그래야 홍보글을 올릴 때 본인의
}
