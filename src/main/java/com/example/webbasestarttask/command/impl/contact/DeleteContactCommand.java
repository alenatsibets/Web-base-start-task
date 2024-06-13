package com.example.webbasestarttask.command.impl.contact;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteContactCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
//        String userEmail = (String) request.getSession().getAttribute("email");
//        User user = userService.getUserByEmail(userEmail);
//        String name = request.getParameter("name");
//        if (contactService.deleteContact(name, user.getUserId())){
//            response.sendRedirect("pages/contact-success.jsp");
//            logger.info("Contact deleted successfully");
//        } else {
//            logger.info("Delete failed");
//        }
        return null;
    }
}
