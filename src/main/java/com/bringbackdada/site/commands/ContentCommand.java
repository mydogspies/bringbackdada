package com.bringbackdada.site.commands;

import com.bringbackdada.site.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ContentCommand {

    private Long id;
    private String contentTitle;
    private Set<CreatorCommand> creators = new HashSet<>();
    private Set<ModelCommand> models = new HashSet<>();
    private ContentCategory category;
    private byte[] imageFile = null;
    private License license;
    private String description;
    private String altText;
    private Set<TagCommand> tags = new HashSet<>();
    private Boolean onFrontPage;
    private Integer contentOrder;
    private Boolean visible;
}
