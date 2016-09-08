/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.Picture;
import com.seabee.snapdragon.dto.StaticPage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class SMBTestDao implements StaticPageDao {

    @Override
    public void addStaticPage(StaticPage page) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StaticPage getStaticPageByPathName(String path) {
        StaticPage sp = new StaticPage();
        sp.setSpId(3);
        sp.setSpTitle("By Brian Test Page");
        sp.setSpBody("<p>Here is some <b>bold</b> text and some <i>italic</i> text<br>I am a line break.</p>");
        sp.setSpPath("by-brian-test-page");

        Picture picture = new Picture();
        picture.setPictureId(5);
        picture.setImageHeight(300);
        picture.setImageWidth(300);
        picture.setSpImageUrl("https://ucarecdn.com/4ebff25c-4eaa-4499-9ad8-aa6abe04d6a8/10154070_10100514301243079_8187882550074345888_n.jpg");
        picture.setSpImageAltText("tongues!");
        sp.setPicture(picture);
        
        return sp;
    }

    @Override
    public void removeStaticPage(int id) {
        throw new UnsupportedOperationException("DELETE WORKS!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateStaticPage(StaticPage page) {
        StaticPage myPage = page;
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        StaticPage sp1 = new StaticPage();
        sp1.setSpTitle("By Brian Test Page");
        sp1.setSpBody("<p>Here is some <b>bold</b> text and some <i>italic</i> text<br>I am a line break.</p>");
        sp1.setSpPath("by-brian-test-page");

        Picture picture1 = new Picture();
        picture1.setImageHeight(300);
        picture1.setImageWidth(300);
        picture1.setSpImageUrl("http://placekitten.com/300/300");
        picture1.setSpImageAltText("kittens are cute");
        sp1.setPicture(picture1);
        
        StaticPage sp2 = new StaticPage();
        sp2.setSpTitle("Another Test Page");
        sp2.setSpPath("another-test-page");
        sp2.setSpBody("Lalalala more testing");
        
        Picture pic2 = new Picture();
        pic2.setImageHeight(275);
        pic2.setImageWidth(350);
        pic2.setSpImageUrl("http://placekitten.com/275/350");
        pic2.setSpImageAltText("more kittens!");
        sp2.setPicture(picture1);
        
        List list = new ArrayList();
        list.add(sp1);
        list.add(sp2);
        return list;
    }

}
