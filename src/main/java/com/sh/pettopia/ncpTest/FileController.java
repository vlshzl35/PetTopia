package com.sh.pettopia.ncpTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
//@RequestMapping("/fileUpload")
public class FileController {
    private final FileService fileService;

    // 파일 업로드
    @GetMapping("/upload")
    public String getUpload(){
        return "upload";
    }

    // 파일 업로드
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFilesSample(
            @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles) {
        if (multipartFiles == null || multipartFiles.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No files were uploaded");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileService.uploadFilesSample(multipartFiles));
    }

    // 파일 불러오기
    @GetMapping("/files")
    public String listFiles(Model model) {
        List<FileDto> files = fileService.listFiles("sample-folder");
        model.addAttribute("files", files);
        return "list";
    }
}
