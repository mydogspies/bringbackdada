package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.ProjectCommand;
import com.bringbackdada.site.commands.converters.ProjectCmdToProject;
import com.bringbackdada.site.commands.converters.ProjectToProjectCommand;
import com.bringbackdada.site.model.Project;
import com.bringbackdada.site.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;
    private final ProjectCmdToProject projectCmdToProject;
    private final ProjectToProjectCommand projectToProjectCommand;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectCmdToProject projectCmdToProject, ProjectToProjectCommand projectToProjectCommand) {
        this.projectRepository = projectRepository;
        this.projectCmdToProject = projectCmdToProject;
        this.projectToProjectCommand = projectToProjectCommand;
    }

    @Override
    public ProjectCommand saveProjectCommand(ProjectCommand command) {
        Project detachedProject = projectCmdToProject.convert(command);
        Project savedProject = projectRepository.save(detachedProject);
        return projectToProjectCommand.convert(savedProject);
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().iterator().forEachRemaining(projects::add);
        return projects;
    }

    @Override
    public Project findById(Long aLong) {
        return null;
    }

    @Override
    public Project save(Project object) {
        projectRepository.save(object);
        logger.info("Project [" + object.getName() + "] has been saved.");
        return object;
    }

    @Override
    public void delete(Project object) {
        projectRepository.delete(object);
        logger.info("Project with id=" + object.getId() + " [" + object.getName() + "] has been permanently deleted!");
    }

    @Override
    public void deleteById(Long aLong) {
        projectRepository.deleteById(aLong);
        logger.info("Project with id=" + aLong + " has been permanently deleted!");
    }

    @Override
    public int count(List<Project> set) {
        return 0;
    }
}
