package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
public class ProjectViewItem {

    Integer itemId;
    Project project;
    List<Content> content;
}
