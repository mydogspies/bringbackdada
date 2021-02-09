package com.bringbackdada.site.commands;

import com.bringbackdada.site.model.Content;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GalleryItemCommand {

    private Long id;
    private ContentCommand content;
    private Integer itemOrder;
    private Boolean visible;
}
