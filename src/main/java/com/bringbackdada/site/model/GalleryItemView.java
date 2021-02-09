package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GalleryItemView {

    private Long id;
    private Long galleryItemId;
    private Long contentId;
    private Integer itemOrder;
    private Boolean visible;
}
