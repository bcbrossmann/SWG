/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.BlogEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class BlogEntryDaoDbImpl implements BlogEntryDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_BLOGENTRY //Needs AuthorId
            = "insert into BlogEntry (EntryName, EntryBody, DateAdded, DateModified, AuthorId) values (?, ?, NOW(), NOW(), ?)";
    private static final String SQL_UPDATE_BLOGENTRY
            = "update BlogEntry set EntryName = ?, EntryBody = ?, DateModified = NOW() where EntryId = ?";
    private static final String SQL_SELECT_BLOGENTRY
            = "select * from BlogEntry where EntryId = ?";

    private static final String SQL_DELETE_BLOGENTRY
            = "delete from BlogEntry where EntryId = ?";
    private static final String SQL_SELECT_ALL_BLOGENTRY
            = "select * from BlogEntry";

    //Add to database the check if tags repeat
    private static final String SQL_INSERT_TAG
            = "call InsertTag(?)";
    private static final String SQL_SELECT_BY_TAG_NAME
            = "select * from BlogEntry be inner join BlogEntryTag bet on be.EntryId = bet.EntryId "
            + "inner join Tag t on bet.TagId = t.TagId where t.TagName = ?";
    private static final String SQL_SELECT_TAG_BY_ENTRYID
            = "select TagName from Tag inner join BlogEntryTag on Tag.TagId = BlogEntryTag.TagId where BlogEntryTag.EntryId = ?";

    private static final String SQL_INSERT_BLOGENTRYTAG
            = "call InsertBlogEntryTag(?, ?)";
    private static final String SQL_SELECT_BLOGENTRYTAG
            = "select TagId from BlogEntryTag where BlogEntryTag.EntryId = ?";
    private static final String SQL_DELETE_BLOGENTRYTAG
            = "delete from BlogEntryTag where EntryId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBlogEntry(BlogEntry entry) {

        entry.setEntryBody(entry.getEntryBody().replace("\n", ""));

        jdbcTemplate.update(SQL_INSERT_BLOGENTRY,
                entry.getEntryName(),
                entry.getEntryBody(),
                //fake author id
                1234);
        entry.setEntryId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        List<String> tags = entry.getTags();
        for (String tag : tags) {
            jdbcTemplate.update(SQL_INSERT_TAG,
                    tag);

            int tagId = jdbcTemplate.queryForObject("select TagId from Tag where TagName = ?", Integer.class, tag);

            jdbcTemplate.update(SQL_INSERT_BLOGENTRYTAG,
                    entry.getEntryId(),
                    tagId);
        }
    }

    @Override
    public BlogEntry getBlogEntryById(int id) {
        try {
            BlogEntry entry = jdbcTemplate.queryForObject(SQL_SELECT_BLOGENTRY, new BlogEntryMapper(), id);
            //Get a list of tags
            entry.setTags(jdbcTemplate.queryForList(SQL_SELECT_TAG_BY_ENTRYID, String.class, id));
            return entry;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void removeBlogEntry(int id) {
        jdbcTemplate.update(SQL_DELETE_BLOGENTRYTAG, id);
        jdbcTemplate.update(SQL_DELETE_BLOGENTRY, id);
    }

    @Override
    public void updateBlogEntry(BlogEntry entry) {

        entry.setEntryBody(entry.getEntryBody().replace("\n", ""));

        jdbcTemplate.update(SQL_UPDATE_BLOGENTRY,
                entry.getEntryName(),
                entry.getEntryBody(),
                entry.getEntryId());

        List<String> tags = entry.getTags();
        for (String tag : tags) {
            jdbcTemplate.update(SQL_INSERT_TAG, tag);

            int tagId = jdbcTemplate.queryForObject("select TagId from Tag where TagName = ?", Integer.class, tag);

            jdbcTemplate.update(SQL_INSERT_BLOGENTRYTAG,
                    entry.getEntryId(),
                    tagId);
        }
    }

    @Override
    public List<BlogEntry> getAllBlogEntrys() {
        try {
            List<BlogEntry> entrys = jdbcTemplate.query(SQL_SELECT_ALL_BLOGENTRY, new BlogEntryMapper());

            for (BlogEntry entry : entrys) {
                entry.setTags(jdbcTemplate.queryForList(SQL_SELECT_TAG_BY_ENTRYID, String.class, entry.getEntryId()));
            }

            return entrys;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<BlogEntry> getBlogEntrysByTag(String tag) {
        try {
            List<BlogEntry> entrys = jdbcTemplate.query(SQL_SELECT_BY_TAG_NAME, new BlogEntryMapper(), tag);

            for (BlogEntry entry : entrys) {
                entry.setTags(jdbcTemplate.queryForList(SQL_SELECT_TAG_BY_ENTRYID, String.class, entry.getEntryId()));
            }

            return entrys;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    

    private static final class BlogEntryMapper implements RowMapper<BlogEntry> {

        @Override
        public BlogEntry mapRow(ResultSet rs, int i) throws SQLException {

            BlogEntry entry = new BlogEntry();
            entry.setEntryId(rs.getInt("EntryId"));
            entry.setEntryName(rs.getString("EntryName"));
            entry.setEntryBody(rs.getString("EntryBody"));
            entry.setDateAdded(rs.getTimestamp("DateAdded").toLocalDateTime());
            entry.setDateModified(rs.getTimestamp("DateModified").toLocalDateTime());
            entry.setEntryId(rs.getInt("EntryId"));

            return entry;
        }

    }
}
