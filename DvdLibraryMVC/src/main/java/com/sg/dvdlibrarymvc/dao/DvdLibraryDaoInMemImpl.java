/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarymvc.dao;

import com.sg.dvdlibrarymvc.model.DVD;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoInMemImpl implements DvdLibraryDao {

    private Map<Integer, DVD> dvdMap = new HashMap<>();

    private static int dvdIdCounter = 0;

    @Override
    public DVD addDVD(DVD dvd) {
        dvd.setDVDId(dvdIdCounter);
        dvdIdCounter++;
        dvdMap.put(dvd.getDVDId(), dvd);
        return dvd;
    }

    @Override
    public void removeDVD(int DVDId) {
        dvdMap.remove(DVDId);
    }

    @Override
    public void updateDVD(DVD dvd) {
        dvdMap.put(dvd.getDVDId(), dvd);
    }

    @Override
    public List<DVD> getAllDVDs() {
        Collection<DVD> c = dvdMap.values();
        return new ArrayList(c);
    }

    @Override
    public DVD getDVDById(int DVDId) {
        return dvdMap.get(DVDId);
    }

    @Override
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {

        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String releaseDateCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String mpaaRatingCriteria = criteria.get(SearchTerm.MPAA_RATING);
        String directorNameCriteria = criteria.get(SearchTerm.DIRECTOR_NAME);
        String userRatingCriteria = criteria.get(SearchTerm.USER_RATING);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);

        Predicate<DVD> titleMatches;
        Predicate<DVD> releaseDateMatches;
        Predicate<DVD> mpaaRatingMatches;
        Predicate<DVD> directorNameMatches;
        Predicate<DVD> userRatingMatches;
        Predicate<DVD> studioMatches;

        Predicate<DVD> truePredicate = (c) -> {
            return true;
        };

        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getTitle().equals(titleCriteria);

        releaseDateMatches = (releaseDateCriteria == null || releaseDateCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getReleaseDate().equals(releaseDateCriteria);

        mpaaRatingMatches = (mpaaRatingCriteria == null || mpaaRatingCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getMpaaRating().equals(mpaaRatingCriteria);

        directorNameMatches = (directorNameCriteria == null || directorNameCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getDirectorName().equals(directorNameCriteria);

        userRatingMatches = (userRatingCriteria == null || userRatingCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getUserRating().equals(userRatingCriteria);
        
        studioMatches = (studioCriteria == null || studioCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getStudio().equals(studioCriteria);

        return dvdMap.values().stream()
                .filter(titleMatches
                        .and(releaseDateMatches)
                        .and(mpaaRatingMatches)
                        .and(directorNameMatches)
                        .and(userRatingMatches)
                        .and(studioMatches))
                .collect(Collectors.toList());
    }

}


