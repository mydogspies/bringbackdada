package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.ProjectCommand;
import com.bringbackdada.site.model.Project;

import java.util.List;

public interface ProjectService extends CrudService<Project, Long>{
    ProjectCommand saveProjectCommand(ProjectCommand command);
    List<Project> sortProjectByProjectOrder(List<Project> unsortedProjectList);
}
