/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.controller;

import com.seabee.snapdragon.dao.BlogEntryDao;
import com.seabee.snapdragon.dao.SMBTestDao;
import com.seabee.snapdragon.dao.StaticPageDao;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class SiteController {

    StaticPageDao spDao;
    BlogEntryDao blogDao;

    @Inject
    public SiteController(StaticPageDao spDao, BlogEntryDao blogDao) {
        this.spDao = spDao;
        this.blogDao = blogDao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String goToHomePage() {
        return "home";
    }

    /* ---------- BLOG ENTRY PATHS ---------- */
    // DISPLAY DASHBOARD-BLOG
    @RequestMapping(value = "/manage-blog-entries", method = RequestMethod.GET)
    public String goToManageBlogEntries() {
        return "manage-blog-entries";
    }

    // DISPLAY DASHBOARD-BLOG
    @RequestMapping(value = "/add-blog-entry", method = RequestMethod.GET)
    public String goToAddBlogEntry() {
        return "add-blog-entry";
    }

    // DISPLAY DASHBOARD-BLOG
    @RequestMapping(value = "/edit-blog-entry/{blogId}", method = RequestMethod.GET)
    public String goToEditBlogEntry(@PathVariable("blogId") int blogEntryId, Model model) {
        model.addAttribute("blogEntry", blogDao.getBlogEntryById(blogEntryId));
        return "edit-blog-entry";
    }

    /* ---------- STATIC PAGE PATHS ---------- */
    // DISPLAY DASHBOARD-MANAGE-STATIC-PAGE
    @RequestMapping(value = "/manage-static-page", method = RequestMethod.GET)
    public String goToDashboardManagePages() {
        return "manage-static-page";
    }

    // DISPLAY DASHBOARD-ADD-STATIC-PAGE
    @RequestMapping(value = "/add-static-page", method = RequestMethod.GET)
    public String goToDashboardAddPages() {
        return "add-static-page";
    }

    // DISPLAY DASHBOARD-EDIT-STATIC-PAGE
    @RequestMapping(value = "/edit-static-page/{pagePath}", method = RequestMethod.GET)
    public String goToDashboardEditPages(@PathVariable("pagePath") String path, Model model) {
        model.addAttribute("staticPage", spDao.getStaticPageByPathName(path));
        return "edit-static-page";
    }

    /* ---------- MISC PATHS ---------- */
    // DISPLAY DASHBOARD-OVERVIEW 
    @RequestMapping(value = "/dashboard-overview", method = RequestMethod.GET)
    public String goToDashboardOverview() {
        return "dashboard-overview";
    }

    // DISPLAY DASHBOARD-VISIT-SITE
    // is this a thing? That should be covered in /page/pagePath, right?
    @RequestMapping(value = "/dashboard-visit-site", method = RequestMethod.GET)
    public String goToDashboardVisitSite() {
        return "dashboard-visit-site";
    }

    // DISPLAY DASHBOARD-SETTINGS
    @RequestMapping(value = "/dashboard-settings", method = RequestMethod.GET)
    public String goToDashboardSettings() {
        return "dashboard-settings";
    }

    // DISPLAY DASHBOARD-HELP
    // what is this? contact form
    @RequestMapping(value = "/dashboard-help", method = RequestMethod.GET)
    public String goToDashboardHelp() {
        return "dashboard-help";
    }

    // DISPLAY DASHBOARD-PROFILE
    // and this? could be a stats page
    @RequestMapping(value = "/dashboard-profile", method = RequestMethod.GET)
    public String goToDashboardProfile() {
        return "dashboard-profile";
    }
    
    // DISPLAY MANAGE-COMMENTS
    @RequestMapping(value = "/manage-comments", method = RequestMethod.GET)
    public String goToManageComments() {
        return "manage-comments";
    }
}
