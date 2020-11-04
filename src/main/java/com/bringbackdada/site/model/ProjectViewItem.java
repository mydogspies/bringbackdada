package com.bringbackdada.site.model;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.ProjectCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This object together with the ProjectView are used only internally in the ProjectController controller class.
 * @since 1.0.1
 * @see ProjectViewItem
 * @see com.bringbackdada.site.controllers.ProjectsController
 */
@Getter
@Setter
public class ProjectViewItem {
    private Integer itemId;
    private ProjectCommand projectCmd;
    private List<ContentCommand> featuredContent;
}
