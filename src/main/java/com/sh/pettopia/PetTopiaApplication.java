package com.sh.pettopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing // @CreatedDate, @LastModifiedDate 사용에 필요
public class PetTopiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetTopiaApplication.class, args);
    }

}
