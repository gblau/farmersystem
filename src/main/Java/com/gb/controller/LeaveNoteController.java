package com.gb.controller;

import com.gb.model.Comment;
import com.gb.service.interfaces.LeaveNoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gblau on 2016-11-14.
 */
@RestController
public class LeaveNoteController {
    @Autowired
    private LeaveNoteService service;

    /**
     * http://localhost:8080/leavenote/commit
     * @param comment
     * @return
     */
    @RequestMapping("/leavenote/commit")
    public Comment commitLeaveNote(Comment comment) {
        System.out.println(comment);
        if(commentElementEmpty(comment))
            return null;

        service.insert(comment);
        return comment;
    }

    private boolean commentElementEmpty(Comment comment) {
        return StringUtils.isEmpty(comment.getNickname())
            || StringUtils.isEmpty(comment.getMessage());
    }
}
