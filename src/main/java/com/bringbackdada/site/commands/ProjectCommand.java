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
    private Set<Gallery> gallery;
    private Set<Creator> creator;
    private Set<Blog> blog;
    private String description;
    private Set<Tag> tags;
}
