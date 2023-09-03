package com.onbording.noteapi.domain.page.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "PAGE_ID")
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "SUB_PAGE_ID")
    private List<Page> subPage = new ArrayList<>();
    @Setter
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Page parentPage;

    @Setter
    @Transient
    private String breadcrumbs;

    public Page(String title, String content, List<Page> subPage) {
        this.title = title;
        this.content = content;
        this.subPage = subPage;
    }

    public Page(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Page(Page page) {
        this.id = page.getId();
        this.title = page.getTitle();
        this.content = page.getContent();
        this.subPage = page.getSubPage();
        this.parentPage = page.parentPage;
        this.breadcrumbs = page.getBreadcrumbs();
    }

    public void addSubPage(Page page) {
        this.subPage.add(page);
        page.setParentPage(this);
    }
}
