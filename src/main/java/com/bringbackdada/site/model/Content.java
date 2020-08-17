package com.bringbackdada.site.model;

import java.util.Set;

public class Content {

    private int id;
    private int projectId;
    private Set<Integer> creatorId;
    private Set<Integer> modelId;
    private ContentCategory category;
    private String name;
    private String description;
    private Set<String> tags;
}
