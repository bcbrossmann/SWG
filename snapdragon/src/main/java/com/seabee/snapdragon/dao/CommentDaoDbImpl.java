/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dao;

import com.seabee.snapdragon.dto.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class CommentDaoDbImpl implements CommentDao {
    
    JdbcTemplate jdbcTemplate;
    
    private final String GET_COMMENTS_BY_BLOGID = "select * from Comments where BlogEntryId = ? order by CommentDate";
    private final String ADD_COMMENT = "insert into Comments(CommentorName, CommentBody, CommentDate, BlogEntryId)" +
            "values(?, ?, NOW(), ?)";
    private final String DELETE_COMMENT = "delete from Comments where CommentId = ?";
    private final String GET_ALL_COMMENTS_REVERSE_CHRON = "SELECT * FROM Comments ORDER BY CommentDate Desc";
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> getCommentsByBlogId(int blogEntryId) {
        return jdbcTemplate.query(GET_COMMENTS_BY_BLOGID, new CommentMapper(), blogEntryId);
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        jdbcTemplate.update(ADD_COMMENT, comment.getCommentorName(), comment.getCommentBody(), comment.getBlogEntryId());
        comment.setCommentId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public void deleteCommentById(int commentId) {
        jdbcTemplate.update(DELETE_COMMENT, commentId);
    }

    @Override
    public List<Comment> getAllCommentsReverseChronOrder() {
        return jdbcTemplate.query(GET_ALL_COMMENTS_REVERSE_CHRON, new CommentMapper());
    }
    
    public static final class CommentMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet rs, int i) throws SQLException {
            Comment c = new Comment();
            c.setCommentId(rs.getInt("CommentId"));
            c.setCommentorName(rs.getString("CommentorName"));
            c.setCommentBody(rs.getString("CommentBody"));
            c.setBlogEntryId(rs.getInt("BlogEntryId"));
            c.setDatePosted(rs.getTimestamp("CommentDate").toLocalDateTime());
            return c;
        }
        
    }
    
}
