package com.gb.service.interfaces;

import com.gb.model.Comment;

import java.util.List;

/**
 * Created by gblau on 2016-11-14.
 */
public interface LeaveNoteService extends BaseService<Comment> {
    List<Comment> findCommentsBySearch(String key);
}
