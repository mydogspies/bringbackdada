package com.bringbackdada.site.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;

    @ManyToMany(mappedBy = "content")
    private Set<Creator> creatorId;

    @ManyToMany(mappedBy = "content")
    private Set<Model> modelId;

    @ManyToMany(mappedBy = "content")
    private Set<Gallery> gallery;

    private ContentCategory category;
    private String contentFile;
    private String contentUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    private License license;

    @Lob
    private String description;

    @ManyToMany
    private Set<Tags> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Set<Creator> getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Set<Creator> creatorId) {
        this.creatorId = creatorId;
    }

    public Set<Model> getModelId() {
        return modelId;
    }

    public void setModelId(Set<Model> modelId) {
        this.modelId = modelId;
    }

    public Set<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(Set<Gallery> gallery) {
        this.gallery = gallery;
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

    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }
}
