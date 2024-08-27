package com.sh.pettopia.naver_clova_ocr_api;

import com.sh.pettopia.naver_clova_ocr_api.ocr.service.OcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OcrController {
    private final OcrService ocrService;
    @Value("${spring.servlet.multipart.location}")
    private String MULTIPART_LOCATION;

    @PostMapping("/ocrUpload")
    public ResponseEntity<?> ocrUpload(
            @RequestParam String type,
            @RequestParam MultipartFile file,
            Model model) throws IOException {
        log.debug("type = {}", type);
        log.debug("file = {}", file);

        // 파일 저장
//        String originalFilename = file.getOriginalFilename();
//        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String savedFilename = UUID.randomUUID().toString() + ext;
//        Path path = Paths.get(MULTIPART_LOCATION, savedFilename);
//        Files.copy(file.getInputStream(), path);
//        log.debug("path = {}", path);

//        // OCR API 호출
//        String response = ocrService.processOcr(type, path.toString());
//        log.debug("response = {}", response);

        // 파일을 저장하지 않고 바로 OCR API에 데이터 전달
        byte[] fileBytes = file.getBytes();  // 파일의 바이트 배열을 얻음
        String response = ocrService.processOcr(type, fileBytes);  // OCR API 호출

        log.debug("response = {}", response);

        model.addAttribute("message", file.getOriginalFilename() + " 영수증 인증 성공");
        model.addAttribute("data", response);
        return ResponseEntity.ok(model);
    }
}
