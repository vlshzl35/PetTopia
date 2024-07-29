package com.sh.pettopia.menu.repository;

import com.sh.pettopia.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
