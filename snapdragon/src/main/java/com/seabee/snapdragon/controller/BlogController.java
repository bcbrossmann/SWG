/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.controller;

import com.seabee.snapdragon.dao.BlogEntryDao;
import com.seabee.snapdragon.dto.BlogEntry;
import com.seabee.snapdragon.dto.Comment;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    BlogEntryDao dao;

    @Inject
    public BlogController(BlogEntryDao dao) {
        this.dao = dao;
    }

    /* ---------- VISIT THE BLOG ---------- */
    // site-title/blog GET
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToBlog(Model model) {
        // GET BLOG POSTS
        // ADD BLOG POSTS TO MODEL
        // RETURN blog.jsp
        model.addAttribute("blogEntries", dao.getAllBlogEntrys());
        return "blog";
    }

    /* ---------- VISIT A BLOG ENTRY ---------- */
    // site-title/blog/blogEntryId GET
    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.GET)
    public String goToBlogEntry(@PathVariable("blogEntryId") int blogEntryId, Model model) {
        // get blog entry associated with that id
        // add to model
        // return blog-entry.jsp
        model.addAttribute("blogEntry", dao.getBlogEntryById(blogEntryId));
        return "blog-entry";
    }

    /* ---------- ADD BLOG ENTRTY ---------- */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addBlogEntry(@Valid @RequestBody BlogEntry entry) {
        dao.addBlogEntry(entry);
    }

    /* ---------- EDIT BLOG ENTRTY ---------- */
    @RequestMapping(value = "/{entryId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editBlogEntry(@Valid @RequestBody BlogEntry entry, @PathVariable("entryId") int id) {
        entry.setEntryId(id);
        dao.updateBlogEntry(entry);
    }

    /* ---------- DELETE BLOG ENTRTY ---------- */
    @RequestMapping(value = "/{entryId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogEntry(@PathVariable("entryId") int id) {
        dao.removeBlogEntry(id);
    }

    /* ---------- GET ALL BLOG ENTRTIES ---------- */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<BlogEntry> getAllBlogEntries() {
        return dao.getAllBlogEntrys();
    }

// I think this is redundant
/*    @RequestMapping(value = "/{blogEntryId}/comments", method = RequestMethod.GET)
    @ResponseBody
    // make a comment DTO
    public List<Comment> getBlogComments(@PathVariable @Valid int blogEntryId) {
        throw new UnsupportedOperationException("get comments for this blog entry");
<<<<<<< HEAD
    }*/
=======
    }
    
    @RequestMapping(value = "/search/{tagName}", method = RequestMethod.GET)
    @ResponseBody
    // make a comment DTO
    public List<BlogEntry> getBlogByTagName(@PathVariable @Valid String tagName) {
        return dao.getBlogEntrysByTag(tagName);
    }
>>>>>>> 2d47f24ff6071fc2dd8fd5dc40f8650fe1d26df0
}
