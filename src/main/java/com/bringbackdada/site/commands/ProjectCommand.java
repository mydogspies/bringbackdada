package com.bringbackdada.site.commands;

import com.bringbackdada.site.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectCommand {

    private Long id;
    private String name;
    private ProjectCategory projectCategory;
    private List<GalleryCommand> gallery;
    private List<CreatorCommand> creator;
    private List<BlogCommand> blog;
    private String description;
    private List<TagCommand> tags;
}
