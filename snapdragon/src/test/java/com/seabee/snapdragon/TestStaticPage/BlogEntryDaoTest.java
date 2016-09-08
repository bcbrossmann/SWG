/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.TestStaticPage;

import com.seabee.snapdragon.dao.BlogEntryDao;
import com.seabee.snapdragon.dto.BlogEntry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class BlogEntryDaoTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    BlogEntryDao dao = (BlogEntryDao) ctx.getBean("BlogEntryDao");
    
    @Test
    public void StaicPageDbTest(){
        //Creat A list of tags
        List<String> tags = new ArrayList<>();
        tags.add("TagA");
        tags.add("TagB");
        tags.add("TagC");
        //Create a Blog entry object
        BlogEntry entry = new BlogEntry();
        entry.setEntryName("New Test Blog");
        entry.setEntryBody("Apples");
        entry.setTags(tags);
        //Add the object to the Db
        dao.addBlogEntry(entry);
        //expected id, auto generated
        int entryId = entry.getEntryId();
        // Get the Body and asssert
        String expected = entry.getEntryBody();
        String actual = dao.getBlogEntryById(entryId).getEntryBody();
        Assert.assertEquals(expected, actual);
        //not tested but will throw an error if it fails.
        dao.getAllBlogEntrys();
        //test edit
        entry.setEntryName("Bannanas");
        entry.setEntryBody("Bannanas");
        dao.updateBlogEntry(entry);
        // Get the Body and asssert
        expected = entry.getEntryBody();
        actual = dao.getBlogEntryById(entryId).getEntryBody();
        Assert.assertEquals(expected, actual);
        //delete
        dao.getBlogEntrysByTag("TagA");
        dao.removeBlogEntry(entryId);
        
    }
    
    
}
