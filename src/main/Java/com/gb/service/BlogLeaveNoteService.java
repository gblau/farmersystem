package com.gb.service;

import com.gb.common.util.SqlSearchUtil;
import com.gb.dao.LeaveNoteMapper;
import com.gb.model.Comment;
import com.gb.service.interfaces.LeaveNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by gblau on 2016-11-14.
 */
@Service
public class BlogLeaveNoteService extends BlogService<Comment> implements LeaveNoteService {
    @Autowired
    private LeaveNoteMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }

    @Override
    public List<Comment> findCommentsBySearch(String key) {
        if(StringUtils.isEmpty(key))
            throw new NoSuchElementException();

        key = SqlSearchUtil.getFuzzyQueryString(key);
        return dao.selectCommentsBySearch(key);
    }
}