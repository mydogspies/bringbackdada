package com.bringbackdada.site.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GalleryCommand {

    private Long id;
    private List<ContentCommand> content;
    private String description;
    private Boolean isFeatured;
}
