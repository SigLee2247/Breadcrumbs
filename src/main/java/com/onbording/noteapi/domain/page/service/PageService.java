package com.onbording.noteapi.domain.page.service;

import com.onbording.noteapi.domain.page.entity.Page;
import com.onbording.noteapi.domain.page.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageService {
    private final PageRepository repository;

    private static String getBreadcrumbs(Page page) {
        StringBuilder sb = new StringBuilder();
        while(page != null){
            sb.insert(0,">");
            sb.insert(1, page.getTitle());
            page = page.getParentPage();
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }

    public Page savePage(Page page) {
        return repository.save(page);
    }

    public Page patchPage(Page page) {
        Page findPage = validPage(page.getId());

        Optional.ofNullable(page.getContent()).ifPresent(page::setContent);
        Optional.ofNullable(page.getTitle()).ifPresent(page::setTitle);

        for (Page subPage : page.getSubPage()) {
            findPage.addSubPage(subPage);
        }

        return findPage;
    }

    public Page getPage(Long nextPageId) {
        Page findPage = validPage(nextPageId);
        Page page = new Page(findPage);
        String breadcrumbs = getBreadcrumbs(page);
        findPage.setBreadcrumbs(breadcrumbs);

        return findPage;
    }

    private Page validPage(Long pageId) {
        return repository.findById(pageId).orElseThrow(() -> new RuntimeException("조회 불가"));
    }

}
