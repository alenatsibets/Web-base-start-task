package com.example.webbasestarttask.controller.command;


public class Router {
    private String page;
    private RouteType routeType = RouteType.FORWARD;

    public enum RouteType {
        FORWARD, REDIRECT
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

    public RouteType getRouteType() {
        return routeType;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.routeType = RouteType.REDIRECT;
    }
}
