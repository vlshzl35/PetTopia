package com.sh.pettopia.enterprise.repository;

import com.sh.pettopia.enterprise.entity.Enterprise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

}
