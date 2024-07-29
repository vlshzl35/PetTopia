package com.sh.pettopia.menu.service;



import com.sh.pettopia.menu.entity.Menu;
import com.sh.pettopia.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepository menuRepository;

    public ResponseEntity<?> findById(Long menuCode) {
        Menu menu = menuRepository.findById(menuCode).orElse(null);
        if(menu == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(menu);
    }

    public ResponseEntity<List<Menu>> findByAll() {
        List<Menu> menuList=menuRepository.findAll();
        return ResponseEntity.ok(menuList);
    }
}
