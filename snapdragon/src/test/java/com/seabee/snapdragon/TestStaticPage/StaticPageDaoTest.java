/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.TestStaticPage;

import com.seabee.snapdragon.dao.StaticPageDao;
import com.seabee.snapdragon.dto.Picture;
import com.seabee.snapdragon.dto.StaticPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class StaticPageDaoTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    StaticPageDao dao = (StaticPageDao) ctx.getBean("StaticPageDao");
    
    @Test
    public void StaicPageDbTest(){
        //Create a picture and sttic page object
        Picture pic = new Picture();
        pic.setSpImageUrl("not.a.real/url");
        pic.setSpImageAltText("this is a picture");
        StaticPage page = new StaticPage();
        page.setSpTitle("Test Path");
        page.setSpBody("Test Body");
        page.setPicture(pic);
        //Add the object to the Db
        dao.addStaticPage(page);
        //expected pathname, auto generated
        String pathname = page.getSpPath();
        // Get the Body and asssert
        String expected = page.getSpBody();
        String actual = dao.getStaticPageByPathName(pathname).getSpBody();
        Assert.assertEquals(expected, actual);
        //delete
        dao.removeStaticPage(page.getSpId());
    }
    
    
}
