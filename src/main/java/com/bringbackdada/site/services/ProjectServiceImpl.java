package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Project;
import com.bringbackdada.site.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Set<Project> findAll() {
        Set<Project> projects = new HashSet<>();
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
        return object;
    }

    @Override
    public void delete(Project object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public int count(Set<Project> set) {
        return 0;
    }
}
