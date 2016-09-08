/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarymvc.dao;

import com.sg.dvdlibrarymvc.model.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface DvdLibraryDao {

    public DVD addDVD(DVD dvd);

    public void removeDVD(int DVDId);

    public void updateDVD(DVD dvd);

    public List<DVD> getAllDVDs();

    public DVD getDVDById(int DVDId);

    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria);
}

