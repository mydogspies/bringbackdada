package com.bringbackdada.site.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String entryName;
    private String entryContent;

    @OneToOne
    private Creator creator;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private Set<ContentCategory> category;

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
