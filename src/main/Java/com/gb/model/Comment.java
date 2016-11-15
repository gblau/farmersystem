package com.gb.model;

/**
 * Created by gblau on 2016-11-14.
 */
public class Comment {
    private int id;
    private String nickname;
    private String web;
    private String message;
    private int note_id;

    public boolean isNicknameEmpty(){
        return this.nickname==null || "".equals(nickname);
    }
    public boolean isMessageEmpty() {
        return message==null || "".equals(message);
    }

    public int getId() {
        return id;
    }
    public Comment setId(int id) {
        this.id = id;
        return this;
    }
    public String getNickname() {
        return nickname;
    }
    public Comment setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    public String getWeb() {
        return web;
    }
    public Comment setWeb(String web) {
        this.web = web;
        return this;
    }
    public String getMessage() {
        return message;
    }
    public Comment setMessage(String message) {
        this.message = message;
        return this;
    }
    public int getNote_id() {
        return note_id;
    }
    public Comment setNote_id(int note_id) {
        this.note_id = note_id;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", web='" + web + '\'' +
                ", message='" + message + '\'' +
                ", note_id=" + note_id +
                '}';
    }
}
