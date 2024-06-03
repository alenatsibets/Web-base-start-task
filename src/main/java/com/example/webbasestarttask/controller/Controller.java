package com.example.webbasestarttask.controller;

import java.io.*;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.command.CommandType;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import static com.example.webbasestarttask.util.Constant.ERROR_505;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
//    static Logger logger = LogManager.getLogger();

    public void init() {
        ConnectionPool.getInstance();
//        logger.info("---------> ServletInit: " + this.getServletInfo());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
            //response.sendRedirect(page);
        } catch (CommandException e) {
            //response.sendError(500);
            request.setAttribute("error_msg", e.getCause());
            request.getRequestDispatcher(ERROR_505).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
//        logger.info("---------> ServletDestroyed: " + this.getServletName());
    }
}