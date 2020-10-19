package com.bringbackdada.site.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentTitle;

    @JoinTable
    @ManyToMany
    private Set<Creator> creator;

    @JoinTable
    @ManyToMany
    private Set<Model> model;

    @Enumerated(value = EnumType.STRING)
    private ContentCategory category;

    // TODO should be depricated and refactored out
    private String contentFile;
    private String contentUrl;

    @ManyToOne
    private License license;

    @Lob
    private String description;

    @ManyToMany
    private Set<Tag> tags;

    private Boolean onFrontPage;

    @Lob
    private byte[] imageFile;

    private Integer contentOrder;

    private Boolean visible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public Set<Creator> getCreator() {
        return creator;
    }

    public void setCreator(Set<Creator> creator) {
        this.creator = creator;
    }

    public Set<Model> getModel() {
        return model;
    }

    public void setModel(Set<Model> model) {
        this.model = model;
    }

    public ContentCategory getCategory() {
        return category;
    }

    public void setCategory(ContentCategory category) {
        this.category = category;
    }

    public String getContentFile() {
        return contentFile;
    }

    public void setContentFile(String contentFile) {
        this.contentFile = contentFile;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Boolean getOnFrontPage() {
        return onFrontPage;
    }

    public void setOnFrontPage(Boolean onFrontPage) {
        this.onFrontPage = onFrontPage;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public Integer getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(Integer contentOrder) {
        this.contentOrder = contentOrder;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}

