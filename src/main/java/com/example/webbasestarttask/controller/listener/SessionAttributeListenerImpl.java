package com.example.webbasestarttask.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    static Logger logger = LogManager.getLogger();
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeAdded(event);
        logger.info("+++<<<<---------> attributeAdded: " + event.getSession().getAttribute("user_name"));
        logger.info("+++<<<<---------> attributeAdded: " + event.getSession().getAttribute("current_page"));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeRemoved(event);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeReplaced(event);
        logger.info("###<<<<---------> attributeReplaced: " + event.getSession().getAttribute("user_name"));
        logger.info("###<<<<---------> attributeReplaced: " + event.getSession().getAttribute("current_page"));
    }
}
