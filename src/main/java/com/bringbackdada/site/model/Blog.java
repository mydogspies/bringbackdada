package com.bringbackdada.site.model;

import java.util.Date;

public class Blog {

    private int id;
    private Date date;
    private String entryName;
    private String entryContent;

    public Blog(int id, Date date, String entryName, String entryContent) {
        this.id = id;
        this.date = date;
        this.entryName = entryName;
        this.entryContent = entryContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getEntryContent() {
        return entryContent;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }
}
