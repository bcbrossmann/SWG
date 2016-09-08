/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.StaticPage;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface StaticPageDao {
    public void addStaticPage(StaticPage page);
    public StaticPage getStaticPageByPathName(String path);
    public void removeStaticPage(int id);
    public void updateStaticPage(StaticPage page);
    public List<StaticPage> getAllStaticPages();
}
