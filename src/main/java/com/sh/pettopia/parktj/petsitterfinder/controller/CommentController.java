package com.sh.pettopia.parktj.petsitterfinder.controller;


import com.sh.pettopia.Hojji.common.paging.PageCriteria;
import com.sh.pettopia.parktj.petsitterfinder.dto.CommentDTO;
import com.sh.pettopia.parktj.petsitterfinder.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    // @ModelAttribute 로도 값을 받아올 수 있다.
    private ResponseEntity save(@ModelAttribute CommentDTO commentDTO, @PageableDefault(page = 1, size = 5) Pageable pageable) {
        System.out.println("commentDTO = " + commentDTO); // view 단으로부터 값을 잘 받아 왔는지 학인
        Long saveResult = commentService.save(commentDTO);
        if (saveResult != null) {
            //작성 성공하면 댓글목록을 가져와서 리턴함
            //댓글 작성하면 기존 댓글에 새로운 댓글이 추가된 것을 보여줘야함
            // 다시 전체댓글을 가져와서 반복문 형태로 보여줘야함
            pageable = PageRequest.of(
                    pageable.getPageNumber() -1,
                    pageable.getPageSize());
            Page<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getPostId(),pageable);
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다", HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CommentDTO>> findAll(
            @RequestParam(value = "postId") Long postId,
            @PageableDefault(page = 1, size = 5) Pageable pageable,
            Model model) {
            log.info("GET/comment/list?page={}", pageable.getPageNumber());
            pageable = PageRequest.of(
                    pageable.getPageNumber() -1,
                    pageable.getPageSize());

        System.out.println("postId = " + postId);
        Page <CommentDTO> commentDTOPage = commentService.findAll(postId, pageable);

        int page = commentDTOPage.getNumber();
        int limit = commentDTOPage.getSize();
        int totalCount = (int) commentDTOPage.getTotalElements();
        String url ="/careregistrationdetails";
//        String url = "/comment/list?postId=" + postId + "&page=" + page;
        model.addAttribute("pageCriteria", new PageCriteria(page, limit, totalCount, url));

        return new ResponseEntity<>(commentDTOPage, HttpStatus.OK);


    }



}
/**
 * @ResponseBody 어노테이션에 대한 설명
 * - 컨트롤러의 메서드가 반환하는 데이터를 HTTP 응답 본문 (BODY) 에 직접 쓰도록 지시함.
 * - 주로 RESTful 웹 서비스나 AJAX 요청의 응답을 처리할 때 사용함
 * <p>
 * ### 동작 방식
 * 1. 컨트롤러 메서드의 반환 값 처리
 * - 일반적으로 Spring MVC 에서 컨트롤러 매서드가 반환하는 값은 뷰 이름으로 간주되어, 해당 이름에 해당하는 뷰 페이지를 렌더링함
 * - @ResponseBody 를 사용하면, 반환된 값이 뷰 이름이 아닌, HTTP 응답의 본문으로 직접 쓰이게 됨
 * <p>
 * 2. 데이터 변환
 * - Spring은 HttpMessageConverter 를 사용해 반환된 객체를 JSON, XML등 클라이언트가 요청한 데이터 형식으로 반환함
 * @ResponseBody와 AJAX 요청
 * AJAX는 웹 페이지를 새로 고침하지 않고 서버와 통신하기 위해 사용되는 기술임.
 * 클라이언트는 서버에 비동기 요청을 보내고, 서버는 데이터를 응답 본문으로 보내 클라이언트 측에서 처리할 수 있도록 함.
 * <p>
 * -> 비동기처리를 할 때, @ResponseBody를 사용하여 뷰를 반환하는 것이 아니라 JSON, XML, 텍스트 등의 데이터 타입을 반환한도록하여
 * Http 응답 본문에 데이터값이 들어갈 수 있기 때문에 동적으로 데이터를 처리할 수 있는 것이다.
 * <p>
 * <p>
 * PageRequest 는 pageable의 구현체로 특정 페이지의 데이터를 가져오기 위해 페이지 번호 (page)와 size를 설정해야함
 */

/**
 * PageRequest 는 pageable의 구현체로 특정 페이지의 데이터를 가져오기 위해 페이지 번호 (page)와 size를 설정해야함
 */