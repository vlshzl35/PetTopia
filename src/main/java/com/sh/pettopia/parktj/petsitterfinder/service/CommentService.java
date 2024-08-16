package com.sh.pettopia.parktj.petsitterfinder.service;

import com.sh.pettopia.parktj.petsitterfinder.dto.CommentDTO;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.CommentEntity;
import com.sh.pettopia.parktj.petsitterfinder.repository.CareRegistrationRepository;
import com.sh.pettopia.parktj.petsitterfinder.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CareRegistrationRepository careRegistrationRepository;


    public Long save(CommentDTO commentDTO) {
        Optional<CareRegistration> optionalCareRegistration  = careRegistrationRepository.findById(commentDTO.getPostId());
        if (optionalCareRegistration.isPresent()) {
            CareRegistration careRegistration = optionalCareRegistration.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, careRegistration);
           return commentRepository.save(commentEntity).getId();


        }else {
            return null;
        }

    }

    public List<CommentDTO> findAll(Long postId) {
        // select * from comment_table where board_id =? order by id desc;
        // 최근에 작성한 댓글이 맨 위에 올라오도록 하기 위함임
        CareRegistration careRegistration = careRegistrationRepository.findById(postId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByCareRegistrationOrderByIdDesc(careRegistration);
//       EntityList -> DTOList
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, postId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;

    }
}
