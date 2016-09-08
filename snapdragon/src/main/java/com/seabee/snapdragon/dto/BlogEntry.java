/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dto;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class BlogEntry {

    private int entryId;
    @NotEmpty(message="You must supply a Title.")
    @Length(max=30, message="the Title must be no more than 30 characters in length.") 
    private String entryName;
    @NotEmpty(message="You must supply a Body.")
    private String entryBody;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;
    private String authorName;
    // List of tags or list of Strings?
    private String tagString;
    private List<String> tags;
    
//    public void convertTagString(String tagString){
//        this.tags = Arrays.asList(tagString.split(", "));
//    }

    public String getAuthorName() {
        return authorName;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public String getEntryBody() {
        return entryBody;
    }

    public int getEntryId() {
        return entryId;
    }

    public String getEntryName() {
        return entryName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public void setEntryBody(String entryBody) {
        this.entryBody = entryBody;
    }

    public void setEntryId(int EntryId) {
        this.entryId = EntryId;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
