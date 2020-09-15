package com.bringbackdada.site.commands;

import com.bringbackdada.site.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProjectCommand {

    private Long id;
    private String name;
    private ProjectCategory projectCategory;
    private Set<GalleryCommand> gallery;
    private Set<CreatorCommand> creator;
    private Set<BlogCommand> blog;
    private String description;
    private Set<TagCommand> tags;
}
