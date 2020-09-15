package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.*;
import com.bringbackdada.site.model.*;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProjectCmdToProject implements Converter<ProjectCommand, Project> {

    private final GalleryCmdToGallery galleryCmdToGallery;
    private final CreatorCmdToCreator creatorCmdToCreator;
    private final BlogCmdToBlog blogCmdToBlog;
    private final TagCmdToTag tagCmdToTag;

    public ProjectCmdToProject(GalleryCmdToGallery galleryCmdToGallery,
                               CreatorCmdToCreator creatorCmdToCreator,
                               BlogCmdToBlog blogCmdToBlog,
                               TagCmdToTag tagCmdToTag) {
        this.galleryCmdToGallery = galleryCmdToGallery;
        this.creatorCmdToCreator = creatorCmdToCreator;
        this.blogCmdToBlog = blogCmdToBlog;
        this.tagCmdToTag = tagCmdToTag;
    }

    @Synchronized
    @Nullable
    @Override
    public Project convert(ProjectCommand projectCommand) {

        if (projectCommand == null) { return null; }

        Project project = new Project();
        project.setId(projectCommand.getId());
        project.setName(projectCommand.getName());
        project.setProjectCategory(projectCommand.getProjectCategory());
        project.setDescription(projectCommand.getDescription());

        Set<Gallery> gallerySet = new HashSet<>();
        for (GalleryCommand galleryCmd : projectCommand.getGallery()) {
            gallerySet.add(galleryCmdToGallery.convert(galleryCmd));
        }
        project.setGallery(gallerySet);

        Set<Creator> creatorSet = new HashSet<>();
        for (CreatorCommand creatorCmd : projectCommand.getCreator()) {
            creatorSet.add(creatorCmdToCreator.convert(creatorCmd));
        }
        project.setCreator(creatorSet);

        Set<Blog> blogSet = new HashSet<>();
        for (BlogCommand blogCmd : projectCommand.getBlog()) {
            blogSet.add(blogCmdToBlog.convert(blogCmd));
        }
        project.setBlog(blogSet);

        Set<Tag> tagSet = new HashSet<>();
        for (TagCommand tagCmd : projectCommand.getTags()) {
            tagSet.add(tagCmdToTag.convert(tagCmd));
        }
        project.setTags(tagSet);

        return project;
    }
}
