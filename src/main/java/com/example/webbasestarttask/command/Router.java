package com.example.webbasestarttask.command;


import static com.example.webbasestarttask.util.Constant.INDEX;

public class Router {
    private String page = INDEX;
    private Type type = Type.FORWARD;
    enum Type{
        FORWARD, REDIRECT;
    }
    public Router(String page) {
        this.page = page;
    }
    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }
}
