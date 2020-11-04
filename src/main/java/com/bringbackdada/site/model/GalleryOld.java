package com.bringbackdada.site.model;

import javax.persistence.*;
import java.util.List;

@Deprecated
@Entity
public class GalleryOld {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String galleryTitle;

    @JoinTable
    @ManyToMany
    private List<Content> content;

    private Integer galleryOrder;

    @Lob
    private String description;

    private Boolean isFeatured;

    public GalleryOld() {
        this.isFeatured = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGalleryTitle() {
        return galleryTitle;
    }

    public void setGalleryTitle(String galleryTitle) {
        this.galleryTitle = galleryTitle;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public Integer getGalleryOrder() {
        return galleryOrder;
    }

    public void setGalleryOrder(Integer galleryOrder) {
        this.galleryOrder = galleryOrder;
    }
}
