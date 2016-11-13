package com.gb.model;

/**
 * Created by gblau on 2016-11-12.
 */
public class Response {
    Object data = "no data";

    public Object getData() {
        return data;
    }
    public Response setData(Object data) {
        this.data = data;
        return this;
    }
}
