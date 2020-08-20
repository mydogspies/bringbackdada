package com.bringbackdada.site.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "gallery_content",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "gallery_id"))
    private Set<Content> content;

    @ManyToMany
    private Set<Project> project;
    
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Content> getContents() {
        return content;
    }

    public void setContents(Set<Content> contents) {
        this.content = contents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
