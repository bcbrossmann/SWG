/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.controller;

import com.seabee.snapdragon.dao.CommentDao;
import com.seabee.snapdragon.dto.Comment;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author wagnercolodette
 */
@Controller
@RequestMapping(value="/comment")
public class CommentsController {
    
    CommentDao dao;
    
    @Inject
    public CommentsController(CommentDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value="/new", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewComment(@RequestBody @Valid Comment comment) {
        dao.addComment(comment);
    }
    
    @RequestMapping(value="/{commentId}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("commentId") int id) {
        dao.deleteCommentById(id);
    }
    
    @RequestMapping(value="/all", method=RequestMethod.GET)
    @ResponseBody
    public List<Comment> getAllComments() {
        return dao.getAllCommentsReverseChronOrder();
    }
    
    @RequestMapping(value="blogcomments/{blogId}", method=RequestMethod.GET)
    @ResponseBody
    public List<Comment> getCommentsByBlogId(@PathVariable("blogId") int id) {
        return dao.getCommentsByBlogId(id);
    }
}
