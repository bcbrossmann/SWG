/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.BlogEntry;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface BlogEntryDao {
    public void addBlogEntry(BlogEntry entry);
    public BlogEntry getBlogEntryById(int id);
    public void removeBlogEntry(int id);
    public void updateBlogEntry(BlogEntry entry);
    public List<BlogEntry> getAllBlogEntrys();
    public List<BlogEntry> getBlogEntrysByTag(String tag);
}
