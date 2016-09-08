/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.controller;

import com.seabee.snapdragon.dao.SMBTestDao;
import com.seabee.snapdragon.dao.StaticPageDao;
import com.seabee.snapdragon.dto.StaticPage;
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

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/page")
public class StaticPageController {

    // Here for front-end testing purposes
    // StaticPageDao dao = new SMBTestDao();
    StaticPageDao dao;

    @Inject
    public StaticPageController(StaticPageDao dao) {
        this.dao = dao;
    }

    /*
    Visit Static Pge
    site-title/page-title GET
     */
    @RequestMapping(value = "/{pagePath}", method = RequestMethod.GET)
    public String goToStaticPage(@PathVariable @Valid String pagePath, Model model) {
        // get StaticPage by path name
        // populate the model with the static page
        // return static-page.jsp
        model.addAttribute("staticPage", dao.getStaticPageByPathName(pagePath));
        return "page";
    }

    /*
    Create Static Page
    /page/userId POST */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addStaticPage(@Valid @RequestBody StaticPage page) {
        // take in a new StaticPage object
        // add to the database
        dao.addStaticPage(page);
    }

    /*Edit Static Page
    /page/userId/pageId PUT*/
    @RequestMapping(value = "/{staticPageId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editStaticPage(@Valid @RequestBody StaticPage page, @PathVariable("staticPageId") int id) {
        //  take a static page object, and replace it? 
        // Yes.
        page.setSpId(id);
        dao.updateStaticPage(page);
    }

    /*Delete Static Page
    /page/userId/siteId/pageId DELETE */
    @RequestMapping(value = "/{staticPageId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaticPage(@PathVariable("staticPageId") int id) {
        //  take in the page path to delete?
        // Remove by page id?
        dao.removeStaticPage(id);
    }

    /*
    Get all static pages
    /page/all
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPage> getAllStaticPages() {
        return dao.getAllStaticPages();
    }
}
