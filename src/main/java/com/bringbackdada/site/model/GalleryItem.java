package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The individual item that is part of the list of items in the GalleryOld object.
 * @since 1.0.1
 */
@Setter
@Getter
@Entity
public class GalleryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Content content;

    private Integer itemOrder;
    private Boolean visible;
}
