package com.sg.dvdlibrarymvc.controller;

import com.sg.dvdlibrarymvc.dao.DvdLibraryDao;
import com.sg.dvdlibrarymvc.model.DVD;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeControllerNoAjax {

    private DvdLibraryDao dao;

    @Inject
    public HomeControllerNoAjax(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayDvdLibraryNoAjax", method = RequestMethod.GET)
    public String displayDvdLibraryNoAjax(Model model) {
        List<DVD> cList = dao.getAllDVDs();
        model.addAttribute("dvdLibrary", cList);
        return "displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "/addNewDvdNoAjax", method = RequestMethod.POST)
    public String addNewDvdNoAjax(HttpServletRequest req) {
        String title = req.getParameter("title");
        String releaseDate = req.getParameter("releaseDate");
        String mpaaRating = req.getParameter("mpaaRating");
        String directorName = req.getParameter("directorName");
        String studio = req.getParameter("studio");
        String userRating = req.getParameter("userRating");
        DVD dvd = new DVD();
        dvd.setTitle(title);
        dvd.setReleaseDate(releaseDate);
        dvd.setMpaaRating(mpaaRating);
        dvd.setDirectorName(directorName);
        dvd.setStudio(studio);
        dvd.setUserRating(userRating);
        dao.addDVD(dvd);
        return "redirect:displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "/deleteDvdNoAjax", method = RequestMethod.GET)
    public String deleteDvdNoAjax(HttpServletRequest req) {
        int DVDId = Integer.parseInt(req.getParameter("DVDId"));
        dao.removeDVD(DVDId);
        return "redirect:displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "displayNewDvdFormNoAjax", method = RequestMethod.GET)
    public String displayNewDvdFormNoAjax() {
        return "newDvdFormNoAjax";
    }

    @RequestMapping(value = "/displayEditDvdFormNoAjax", method = RequestMethod.GET)
    public String displayEditDvdFormNoAjax(HttpServletRequest req, Model model) {
        int DVDId = Integer.parseInt(req.getParameter("DVDId"));
        DVD dvd = dao.getDVDById(DVDId);
        model.addAttribute("dvd", dvd);
        return "editDvdFormNoAjax";
    }

    @RequestMapping(value = "/editDvdNoAjax", method = RequestMethod.POST)
    public String editDvdNoAjax(@Valid @ModelAttribute("dvd") DVD dvd, BindingResult result) {
        if (result.hasErrors()) {
            return "editDvdFormNoAjax";
        }
        dao.updateDVD(dvd);
        return "redirect:displayDvdLibraryNoAjax";
    }
}
