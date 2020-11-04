package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

/**
 * This object together with the ProjectViewItem are used only internally in the ProjectController controller class.
 * @since 1.0.1
 * @see ProjectViewItem
 * @see com.bringbackdada.site.controllers.ProjectsController
 */
@Getter
@Setter
public class ProjectView {
    private List<ProjectViewItem> projectItem;
}
