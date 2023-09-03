package com.onbording.noteapi.domain.page.repository;

import com.onbording.noteapi.domain.page.entity.Page;

import java.util.Optional;

public interface PageRepository {
    Optional<Page> findById(Long id);
    Page save(Page page);
}
