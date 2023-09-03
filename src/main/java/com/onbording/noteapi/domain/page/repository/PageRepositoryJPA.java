package com.onbording.noteapi.domain.page.repository;

import com.onbording.noteapi.domain.page.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepositoryJPA extends JpaRepository<Page,Long> , PageRepository {
}
