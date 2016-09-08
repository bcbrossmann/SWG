/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarymvc.dao;

import com.sg.dvdlibrarymvc.model.DVD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoDbImpl implements DvdLibraryDao {

    private static final String SQL_INSERT_DVD
            = "INSERT INTO dvds(title, release_date, mpaa_rating, director_name, studio, user_rating)"
            + "values(?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_DVD
            = "DELETE FROM dvds WHERE dvd_id=?";

    private static final String SQL_SELECT_DVD
            = "SELECT * FROM dvds WHERE dvd_id=?";

    private static final String SQL_UPDATE_DVD
            = "UPDATE dvds SET title=?, release_date=?, mpaa_rating=?, director_name=?, studio=?, user_rating=?"
            + "WHERE dvd_id=?";

    private static final String SQL_SELECT_ALL_DVDS
            = "SELECT * FROM dvds";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public DVD addDVD(DVD dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getMpaaRating(),
                dvd.getDirectorName(),
                dvd.getStudio(),
                dvd.getUserRating()
        );
        //SELECT LAST_INSERT_ID IS A BUILT-IN FEATURE, NOT SOMETHING WE DEFINED
        dvd.setDVDId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return dvd;
    }

    @Override
    public void removeDVD(int DVDId) {
        jdbcTemplate.update(SQL_DELETE_DVD, DVDId);
    }

    @Override
    public void updateDVD(DVD dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getMpaaRating(),
                dvd.getDirectorName(),
                dvd.getStudio(),
                dvd.getUserRating(),
                dvd.getDVDId()
        );
    }

    @Override
    public List<DVD> getAllDVDs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());
    }

    @Override
    public DVD getDVDById(int DVDId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DvdMapper(), DVDId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {
        if(criteria == null || criteria.size()==0){
            return getAllDVDs();
        }
        
        StringBuilder query = new StringBuilder("SELECT * FROM dvds WHERE ");
        
        int numParams = criteria.size();
        int paramPosition = 0;
        
        String[] paramVals = new String[numParams];
        
        Set<SearchTerm> keyset = criteria.keySet();
        Iterator<SearchTerm> iter = keyset.iterator();
        
        while(iter.hasNext()){
            SearchTerm currentKey = iter.next();
            String currentValue = criteria.get(currentKey);
            
            if(paramPosition>0){
                query.append(" and ");
            }
            
            query.append(currentKey);
            query.append(" =? ");
            
            paramVals[paramPosition] = currentValue;
            paramPosition++;
        }
        
        return jdbcTemplate.query(query.toString(), new DvdMapper(), paramVals);
    }

    private static final class DvdMapper implements RowMapper<DVD> {

        @Override
        public DVD mapRow(ResultSet rs, int i) throws SQLException {
            DVD dvd = new DVD();
            dvd.setDVDId(rs.getInt("dvd_id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseDate(rs.getString("release_date"));
            dvd.setMpaaRating(rs.getString("mpaa_rating"));
            dvd.setDirectorName(rs.getString("director_name"));
            dvd.setUserRating(rs.getString("user_rating"));
            dvd.setStudio(rs.getString("studio"));
            return dvd;
        }

    }
    
}
