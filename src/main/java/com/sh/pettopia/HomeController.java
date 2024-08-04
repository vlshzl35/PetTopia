package com.sh.pettopia;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class HomeController {


//    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
//    public ResponseEntity<?> home(){
//        return ResponseEntity.ok(("<h1>⚽️Hello PetTopia! App's profile is <mark>%s</mark> \uD83C\uDFB1</h1>" +
//                "<h1>펫토피아입니다</h1>\n" +
//                "<div>\n" +
//                "    <ul>서버에서 되는지 확인\n" +
//                "        <li>http://223.130.146.203:8080/menus/1</li>\n" +
//                "        <li>http://223.130.146.203:8080/menus/all</li>\n" +
//                "    </ul>\n" +
//                "\n" +
//                "    <ul>로컬(인텔리제이)에서 되는지 확인\n" +
//                "        <li>http://localhost:8080/menus/20</li>\n" +
//                "        <li>http://localhost:8080/menus/all</li>\n" +
//                "    </ul>\n" +
//                "</div>").formatted(value));// 이 값은 application.yml에서 #profile관리에 있는 내용이다
//        // 관리할 때는 test로 하고 cicd로 돌아갈때는 prod = 서버 db로 돌아간다
//    }
    @GetMapping("/contact")
    public void contact(){
    }
    @GetMapping("/about")
    public void about(){
    }
    @GetMapping("/blog")
    public void blog(){
    }
    @GetMapping("/blogsingle")
    public void blogSingle(){
    }
    @GetMapping("/gallery")
    public void gallery(){
    }
    @GetMapping("/main")
    public void main(){
    }
    @GetMapping("/pricing")
    public void pricing(){
    }
    @GetMapping("/services")
    public void services(){
    }
    @GetMapping("/vet")
    public void vet(){
    }


//    @GetMapping("/petsitterfinder")
//    public void petsitterFinder(){
//
//    }

}
