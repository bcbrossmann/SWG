/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.Comment;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface CommentDao {
    public List<Comment> getCommentsByBlogId(int blogEntryId);
    public void addComment(Comment comment);
    public void deleteCommentById(int commentId);
    public List<Comment> getAllCommentsReverseChronOrder();
}
