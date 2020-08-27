package com.bringbackdada.site.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
public class Blog {

    /**
     * Note that default blogImageId is id=1, that is the
     * index that is by default interpreted as "none".
     */
    public Blog() {
        this.blogImageId = 1L;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant milliseconds;
    private String entryName;

    @Lob
    private String entryContent;

    @Lob
    private String contentSnippet;

    @OneToOne
    private Creator creator;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private Set<ContentCategory> category;

    private Long blogImageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(Instant milliseconds) {
        this.milliseconds = milliseconds;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getEntryContent() {
        return entryContent;
    }

    public String getContentSnippet() {
        return contentSnippet;
    }

    public void setContentSnippet(String contentSnippet) {
        this.contentSnippet = contentSnippet;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Set<ContentCategory> getCategory() {
        return category;
    }

    public void setCategory(Set<ContentCategory> category) {
        this.category = category;
    }

    public Long getBlogImageId() {
        return blogImageId;
    }

    public void setBlogImageId(Long blogImageId) {
        this.blogImageId = blogImageId;
    }
}
