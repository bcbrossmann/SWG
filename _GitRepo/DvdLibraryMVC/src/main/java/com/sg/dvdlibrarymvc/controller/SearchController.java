package com.sg.dvdlibrarymvc.controller;

import com.sg.dvdlibrarymvc.dao.DvdLibraryDao;
import com.sg.dvdlibrarymvc.dao.SearchTerm;
import com.sg.dvdlibrarymvc.model.DVD;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    private DvdLibraryDao dao;

    @Inject
    public SearchController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }

    @RequestMapping(value = "search/dvds", method = RequestMethod.POST)
    @ResponseBody
    public List<DVD> searchDVDs(@RequestBody Map<String, String> searchMap) {
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        String currentTerm = searchMap.get("title");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.TITLE, currentTerm);
        }
        currentTerm = searchMap.get("releaseDate");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.RELEASE_DATE, currentTerm);
        }
        currentTerm = searchMap.get("mpaaRating");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.MPAA_RATING, currentTerm);
        }
        currentTerm = searchMap.get("directorName");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DIRECTOR_NAME, currentTerm);
        }
        currentTerm = searchMap.get("studio");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STUDIO, currentTerm);
        }
        currentTerm = searchMap.get("userRating");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.USER_RATING, currentTerm);
        }
        return dao.searchDVDs(criteriaMap);
    }
}
