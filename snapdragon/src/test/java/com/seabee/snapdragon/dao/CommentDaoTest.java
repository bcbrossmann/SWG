/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.BlogEntry;
import com.seabee.snapdragon.dto.Comment;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 *
 * @author apprentice
 */
public class CommentDaoTest {

    int beId;
    Comment c1;
    Comment c2;
    Comment c3;
    JdbcTemplate jdbcTemplate;
    CommentDao dao;

    @Before
    @Transactional
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        dao = ctx.getBean("CommentDao", CommentDao.class);

        jdbcTemplate.update("INSERT INTO BlogEntry(EntryName, EntryBody, DateAdded, DateModified, AuthorId) VALUES('Test again','This is a test', NOW(), NOW(), 1234)");
        beId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        c1 = new Comment();
        c1.setCommentorName("Shosh");
        c1.setCommentBody("Hello!");
        c1.setBlogEntryId(beId);

        c2 = new Comment();
        c2.setCommentorName("Scott");
        c2.setCommentBody("What's up?");
        c2.setBlogEntryId(beId);

        c3 = new Comment();
        c3.setCommentorName("Brian");
        c3.setCommentBody("Thank you for stopping by my bakery!");
        c3.setBlogEntryId(beId);
    }

    @After
    public void tearDown() {
        jdbcTemplate.update("DELETE FROM Comments WHERE BlogEntryId = ?", beId);
        jdbcTemplate.update("DELETE FROM BlogEntry WHERE EntryId = ?", beId);
    }

    public CommentDaoTest() {
    }

    /**
     * Test of getCommentsByBlogId method, of class CommentDao.
     */
    @Test
    public void testAddGetAllComments() {
        dao.addComment(c1);
        dao.addComment(c2);
        dao.addComment(c3);

        List<Comment> commentList = dao.getAllCommentsReverseChronOrder();
        boolean actual = (commentList.size() >= 3);
        Assert.assertEquals(true, actual);
    }

    /**
     * Test of addComment method, of class CommentDao.
     */
    @Test
    public void testAddGetCommentsByBlogId() {
        dao.addComment(c1);
        dao.addComment(c2);
        dao.addComment(c3);

        List<Comment> commentList = dao.getCommentsByBlogId(beId);
        Assert.assertEquals(3, commentList.size());
    }

    /**
     * Test of deleteCommentById method, of class CommentDao. //
     */
    @Test
    public void testAddDeleteGetByBlogId() {
        dao.addComment(c1);
        dao.addComment(c2);
        dao.addComment(c3);
        
        Assert.assertEquals(3, dao.getCommentsByBlogId(beId).size());
        
        dao.deleteCommentById(c1.getCommentId());
        
        Assert.assertEquals(2, dao.getCommentsByBlogId(beId).size());
    }

}
