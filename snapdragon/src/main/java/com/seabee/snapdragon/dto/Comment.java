/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dto;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Comment {
    private int commentId;
    private int blogEntryId;
    @NotEmpty(message="You must supply a name.")
    @Length(max=30, message="the name must be no more than 30 characters in length.") 
    private String commentorName;
    @NotEmpty(message="You must supply a body.")
    private String commentBody;
    private LocalDateTime datePosted;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getBlogEntryId() {
        return blogEntryId;
    }

    public void setBlogEntryId(int blogEntryId) {
        this.blogEntryId = blogEntryId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getCommentorName() {
        return commentorName;
    }

    public void setCommentorName(String commentorName) {
        this.commentorName = commentorName;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

}
