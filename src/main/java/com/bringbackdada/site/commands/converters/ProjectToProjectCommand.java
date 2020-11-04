package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.*;
import com.bringbackdada.site.model.*;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectToProjectCommand implements Converter<Project, ProjectCommand> {

    private final GalleryToGalleryCmd galleryToGalleryCmd;
    private final CreatorToCreatorCmd creatorToCreatorCmd;
    private final BlogToBlogCmd blogToBlogCmd;
    private final TagToTagCmd tagToTagCmd;

    public ProjectToProjectCommand(GalleryToGalleryCmd galleryToGalleryCmd,
                                   CreatorToCreatorCmd creatorToCreatorCmd,
                                   BlogToBlogCmd blogToBlogCmd,
                                   TagToTagCmd tagToTagCmd) {
        this.galleryToGalleryCmd = galleryToGalleryCmd;
        this.creatorToCreatorCmd = creatorToCreatorCmd;
        this.blogToBlogCmd = blogToBlogCmd;
        this.tagToTagCmd = tagToTagCmd;
    }

    @Synchronized
    @Nullable
    @Override
    public ProjectCommand convert(Project project) {

        if (project == null) { return null;}

        ProjectCommand command = new ProjectCommand();
        command.setId(project.getId());
        command.setProjectCategory(project.getProjectCategory());
        command.setDescription(project.getDescription());
        command.setRollVisible(project.getRollVisible());
        command.setProjectOrder(project.getProjectOrder());
        command.setName(project.getName());

        List<GalleryCommand> gallerySet = new ArrayList<>();
        for (Gallery gallery : project.getGallery()) {
            gallerySet.add(galleryToGalleryCmd.convert(gallery));
        }
        command.setGallery(gallerySet);

        List<CreatorCommand> creatorSet = new ArrayList<>();
        for (Creator creator : project.getCreator()) {
            creatorSet.add(creatorToCreatorCmd.convert(creator));
        }
        command.setCreator(creatorSet);

        List<BlogCommand> blogSet = new ArrayList<>();
        for (Blog blog : project.getBlog()) {
            blogSet.add(blogToBlogCmd.convert(blog));
        }
        command.setBlog(blogSet);

        List<TagCommand> tagSet = new ArrayList<>();
        for (Tag tag : project.getTags()) {
            tagSet.add(tagToTagCmd.convert(tag));
        }
        command.setTags(tagSet);

        return command;
    }
}
