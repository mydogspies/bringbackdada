package com.bringbackdada.site.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private ProjectCategory projectCategory;

    @OneToMany
    private Set<Gallery> gallery;

    @OneToMany
    private Set<Creator> creator;

    @OneToMany
    private Set<Blog> blog;

    @Lob
    private String description;

    @ManyToMany
    private Set<Tag> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    public Set<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(Set<Gallery> gallery) {
        this.gallery = gallery;
    }

    public Set<Creator> getCreator() {
        return creator;
    }

    public void setCreator(Set<Creator> creator) {
        this.creator = creator;
    }

    public Set<Blog> getBlog() {
        return blog;
    }

    public void setBlog(Set<Blog> blog) {
        this.blog = blog;
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
}
