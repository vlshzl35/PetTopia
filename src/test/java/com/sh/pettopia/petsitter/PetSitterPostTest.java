package com.sh.pettopia.petsitter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PetSitterPostTest {
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
    @DisplayName("ddl-auto=create 확인")
    void test() {
    }


    @Test
    @DisplayName("펫시터 게시글 등록")
    void insertPost() {
        PetSitterPost petSitterPost = PetSitterPost.builder()
                .createdAt(LocalDateTime.now()).url("naver.com")
                .build();

        this.entityManager.persist(petSitterPost);

        Reservation reservation = Reservation.builder()
                .startDate(LocalDate.of(2024, 8, 5))
                .endDate(LocalDate.of(2024, 8, 6))
                .petSitterPost(petSitterPost)
                .reservationStatus(ReservationStatus.요청대기).build();

        Reservation reservation2 = Reservation.builder()
                .startDate(LocalDate.of(2024, 9, 5))
                .endDate(LocalDate.of(2024, 9, 6))
                .petSitterPost(petSitterPost)
                .reservationStatus(ReservationStatus.요청대기).build();

        Reservation reservation3 = Reservation.builder()
                .startDate(LocalDate.of(2024, 8, 10))
                .endDate(LocalDate.of(2024, 8, 10))
                .petSitterPost(petSitterPost)
                .reservationStatus(ReservationStatus.요청대기).build();


        this.entityManager.persist(reservation);
        this.entityManager.persist(reservation2);
        this.entityManager.persist(reservation3);
        this.entityManager.flush();
        System.out.println("petSitter = " + petSitterPost);
        System.out.println("reservation = " + reservation);
        System.out.println("reservation2 = " + reservation2);
        System.out.println("reservation3 = " + reservation3);


    }
}
