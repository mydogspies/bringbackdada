package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private ProjectCategory projectCategory;

    @ManyToMany
    private List<Gallery> gallery;

    @ManyToMany
    private List<Creator> creator;

    @ManyToMany
    private List<Blog> blog;

    @Lob
    private String description;

    @ManyToMany
    private List<Tag> tags;

    private Boolean rollVisible;
    private Integer projectOrder;
}
