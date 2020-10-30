package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is the Gallery object that contains a list of GalleryItem objects plus fields specific to it.
 * NOTE! The old pre-1.0.1 Gallery pojo has been refactored into GalleryOld.
 * @since 1.0.1
 */
@Setter
@Getter
@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String galleryName;

    @JoinTable
    @ManyToMany
    private List<GalleryItem> galleryItem;

    @Lob
    private String description;

    private Integer galleryOrder;
    private Boolean frontPageFeatured;
    private Boolean visible;
}
