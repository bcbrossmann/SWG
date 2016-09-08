/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.Picture;
import com.seabee.snapdragon.dto.StaticPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class StaticPageDaoDbImpl implements StaticPageDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_STATICPAGE
            = "insert into StaticPage (StaticPagePath, StaticPageTitle, StaticPageBody, PictureId) values (?, ?, ?, ?)";
    private static final String SQL_UPDATE_STATICPAGE
            = "update StaticPage set StaticPagePath = ?, StaticPageTitle = ?, StaticPageBody = ? where StaticPageId = ?";
    private static final String SQL_SELECT_STATICPAGE
            = "select * from StaticPage inner join Picture on StaticPage.PictureId = Picture.PictureId where StaticPagePath = ?";
    private static final String SQL_SELECT_ALL_STATICPAGE
            = "select * from StaticPage inner join Picture on StaticPage.PictureId = Picture.PictureId";

    private static final String SQL_INSERT_PICTURE
            = "insert into Picture (PictureURL, PictureWidth, PictureHeight, AltText) values (?, ?, ?, ?)";
    private static final String SQL_DELETE_PICTURE_AND_STATIC_PAGE
            = "delete Picture.*, StaticPage.* from Picture inner join StaticPage on Picture.PictureId = StaticPage.PictureId where StaticPageId = ?";
    private static final String SQL_UPDATE_PICTURE
            = "update Picture set PictureURL = ?, PictureWidth = ?, PictureHeight = ?, AltText = ? where PictureId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addStaticPage(StaticPage page) {

        page.setSpBody(page.getSpBody().replace("\n", ""));

        int pictureId = addPicture(page);


        //Create a path name from the title
        String pathName = page.getSpTitle().toLowerCase().replaceAll("[^a-zA-Z ]", "").replaceAll(" ", "-");

        jdbcTemplate.update(SQL_INSERT_STATICPAGE,
                pathName,
                page.getSpTitle(),
                page.getSpBody(),
                pictureId);
       
        int spId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        page.setSpId(spId);
        jdbcTemplate.update("Call updateStaticPath(?, ?)", pathName, spId);
        page.setSpPath(jdbcTemplate.queryForObject("select StaticPagePath from StaticPage where StaticPageId = ?", String.class, spId));
    }
    
     @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
     private int addPicture(StaticPage page) {
        Picture pic = page.getPicture();
        jdbcTemplate.update(SQL_INSERT_PICTURE,
                pic.getSpImageUrl(),
                pic.getImageWidth(),
                pic.getImageHeight(),
                pic.getSpImageAltText());

        int pictureId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        pic.setPictureId(pictureId);
        return pictureId;
     }

    @Override
    public StaticPage getStaticPageByPathName(String path) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATICPAGE, new StaticPageMapper(), path);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void removeStaticPage(int id) {

        jdbcTemplate.update(SQL_DELETE_PICTURE_AND_STATIC_PAGE, id);
        //jdbcTemplate.update(SQL_DELETE_STATICPAGE, id);
    }

    @Override
    public void updateStaticPage(StaticPage page) {
        // handle \n characters from CKEditor
        page.setSpBody(page.getSpBody().replace("\n", ""));

        Picture pic = page.getPicture();
        jdbcTemplate.update(SQL_UPDATE_PICTURE,
                pic.getSpImageUrl(),
                pic.getImageWidth(),
                pic.getImageHeight(),
                pic.getSpImageAltText(),
                //where
                pic.getPictureId());

        jdbcTemplate.update(SQL_UPDATE_STATICPAGE,
                //Make the new path out of the title
                page.getSpTitle().toLowerCase().replaceAll(" ", "-"),
                page.getSpTitle(),
                page.getSpBody(),
                //where
                page.getSpId());
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STATICPAGE, new StaticPageMapper());
    }

    //picture mapper
    private static final class StaticPageMapper implements RowMapper<StaticPage> {

        @Override
        public StaticPage mapRow(ResultSet rs, int i) throws SQLException {
            Picture p = new Picture();
            p.setPictureId(rs.getInt("PictureId"));
            p.setSpImageUrl(rs.getString("PictureURL"));
            p.setImageWidth(rs.getInt("PictureWidth"));
            p.setImageHeight(rs.getInt("PictureHeight"));
            p.setSpImageAltText(rs.getString("AltText"));

            StaticPage sp = new StaticPage();
            sp.setSpId(rs.getInt("StaticPageId"));
            sp.setSpPath(rs.getString("StaticPagePath"));
            sp.setSpTitle(rs.getString("StaticPageTitle"));
            sp.setSpBody(rs.getString("StaticPageBody"));

            sp.setPicture(p);

            return sp;
        }

    }
}
