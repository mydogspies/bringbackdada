package com.bringbackdada.site.commands;

import com.bringbackdada.site.model.ContentCategory;
import com.bringbackdada.site.model.Creator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BlogCommand {

    private Long id;
    private Instant milliseconds;
    private String entryName;
    private String entryContent;
    private String contentSnippet;
    private Creator creator;
    private Set<ContentCategory> category;
    private Long contentId;
}
