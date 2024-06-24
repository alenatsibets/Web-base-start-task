package com.example.webbasestarttask.controller;

import java.io.*;

import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.CommandType;
import com.example.webbasestarttask.controller.command.Router;
import com.example.webbasestarttask.model.exception.CommandException;
import com.example.webbasestarttask.model.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.example.webbasestarttask.util.constant.PagePath.ERROR_500;

@WebServlet(name = "firstServlet", urlPatterns = {"/controller", "*.do"})
@MultipartConfig
public class Controller extends HttpServlet {

    @Override
    public void init() {
        ConnectionPool.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        Router page;
        try {
            page = command.execute(request, response);
            if (page.getRouteType() == Router.RouteType.FORWARD){
                request.getRequestDispatcher(page.getPage()).forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + page.getPage());
            }

        } catch (CommandException e) {
            request.setAttribute("error_msg", e.getCause());
            request.getRequestDispatcher(ERROR_500).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        ConnectionPool.getInstance().deregisterDrivers();
    }
}