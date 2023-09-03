package com.onbording.noteapi.domain.page.service;


import com.onbording.noteapi.domain.page.entity.Page;
import com.onbording.noteapi.domain.page.repository.PageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class PageServiceTest {
    @Autowired
    PageService service;
    @Autowired
    PageRepository pageRepository;

    @Test
    @DisplayName("브로드 스크럼 조회")
    void add_broad_test() throws Exception{
        Page page1 = new Page("타이틀1", "본문1");
        Page page2 = new Page("타이틀2", "본문2");
        Page page3 = new Page("타이틀3", "본문3");
        Page page4 = new Page("타이틀4", "본문4");
        page3.addSubPage(page4);
        page2.addSubPage(page3);
        page1.addSubPage(page2);

        service.savePage(page1);

        Page page = service.getPage(4L);
        System.out.println(page.getBreadcrumbs());
        Assertions.assertThat(page.getBreadcrumbs()).isNotNull();
        Assertions.assertThat(page.getBreadcrumbs()).isEqualTo("타이틀1>타이틀2>타이틀3>타이틀4");

    }

}