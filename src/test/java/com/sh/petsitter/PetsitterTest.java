package com.sh.petsitter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

public class PetsitterTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    static void beforeAll() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pettopia");
    }

    @BeforeEach
    void setUp() {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityManager.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    @AfterAll
    static void afterAll() {
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("auto-create")
    void test1() {
        Set<AvailablePetSize>petSizes=new HashSet<>();
        petSizes.add(AvailablePetSize.valueOf("대형견"));

        Set<AvailableService> services=new HashSet<>();
        services.add(AvailableService.valueOf("매일산책"));
        services.add(AvailableService.valueOf("응급처치"));
        PetSitterAddress petSitterAddress=new PetSitterAddress("05234","서울 강동구 고덕로61길 37","102동 1006","(고덕동, 현대아파트)");

        PetSitter.builder().availablePetSize(petSizes).petSitterId("cstangga@naver.com")
                .availableService(services)
                .images_url_list(null)
                .petSitterAddress(petSitterAddress)
                .introduce("안녕하세요 최창욱입니다")
                .url("www.naver.com").build();

    }

}
