package com.example.webbasestarttask.command;


import static com.example.webbasestarttask.util.PagePath.INDEX;

public class Router {
    private String page = INDEX;
    private RouteType routeType = RouteType.FORWARD;
    enum RouteType {
        FORWARD, REDIRECT;
    }
    public Router(String page) {
        this.page = page;
    }
    public Router(String page, RouteType routeType) {
        this.page = page;
        this.routeType = routeType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.routeType = RouteType.REDIRECT;
    }
}
