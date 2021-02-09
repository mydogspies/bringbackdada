package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

/**
 *
 */
@Setter
@Getter
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant milliseconds;
    private String entryName;

    @Lob
    private String entryContent;

    @Lob
    private String contentSnippet;

    @ManyToOne
    private Creator creator;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private Set<ContentCategory> category;

    private Long contentId;
    private Boolean visible;
}
