package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
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

    @ManyToOne
    private License license;

    @Lob
    private String description;

    @Lob
    private String altText;

    @ManyToMany
    private Set<Tag> tags;

    @Lob
    private byte[] imageFile;

    private Integer contentOrder;
    private Boolean onFrontPage;
    private Boolean visible;
}

