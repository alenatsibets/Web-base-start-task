package com.example.webbasestarttask.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Enumeration;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    static Logger logger = LogManager.getLogger();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeAdded(event);
        Enumeration<String> attributeNames = event.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attributeValue = event.getSession().getAttribute(attributeName);
            if (attributeValue != null) {
                logger.info("attributeAdded: " + attributeName + " = " + attributeValue);
            }
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeReplaced(event);
        Enumeration<String> attributeNames = event.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attributeValue = event.getSession().getAttribute(attributeName);
            if (attributeValue != null) {
                logger.info("attributeReplaced: " + attributeName + " = " + attributeValue);
            }
        }
    }
}
