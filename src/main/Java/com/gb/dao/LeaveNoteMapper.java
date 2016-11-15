package com.gb.dao;

import com.gb.model.Comment;

import java.util.List;

/**
 * Created by gblau on 2016-11-14.
 */
public interface LeaveNoteMapper extends Mapper<Comment> {
    List<Comment> selectCommentsBySearch(String key);
}
