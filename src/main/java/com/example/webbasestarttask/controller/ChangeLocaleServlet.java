package com.example.webbasestarttask.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.util.Locale;

import java.io.IOException;

@WebServlet("/changeLocale")
public class ChangeLocaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String language = request.getParameter("lang");
        if (language != null && !language.isEmpty()) {
            Locale locale = new Locale(language);
            HttpSession session = request.getSession();
            session.setAttribute("locale", locale);
            Cookie localeCookie = new Cookie("locale", locale.toString());
            localeCookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(localeCookie);
        }
        response.sendRedirect(request.getHeader("referer"));
    }
}
