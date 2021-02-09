package com.bringbackdada.site.commands;

import com.bringbackdada.site.model.GalleryItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GalleryCommand {

    private Long id;
    private String galleryName;
    private List<GalleryItemCommand> galleryItem;
    private String description;
    private Integer galleryOrder;
    private Boolean frontPageFeatured;
    private Boolean visible;
}
