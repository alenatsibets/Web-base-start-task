package com.example.webbasestarttask.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    static Logger logger = LogManager.getLogger();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        logger.info("+++++++++ contextInitialized: " + sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        logger.info("--------- contextDestroyed: " + sce.getServletContext().getContextPath());
    }
}
