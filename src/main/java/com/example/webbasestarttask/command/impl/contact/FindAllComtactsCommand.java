package com.example.webbasestarttask.command.impl.contact;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.entity.Contact;
import com.example.webbasestarttask.entity.User;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.ContactService;
import com.example.webbasestarttask.service.UserService;
import com.example.webbasestarttask.service.impl.ContactServiceImpl;
import com.example.webbasestarttask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.example.webbasestarttask.util.AttributeConstant.CONTACT_LIST;
import static com.example.webbasestarttask.util.AttributeConstant.EMAIL;
import static com.example.webbasestarttask.util.PagePath.CONTACTS;
import static com.example.webbasestarttask.util.PagePath.LOGIN;

public class FindAllComtactsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(false);
        String userEmail = (String) session.getAttribute(EMAIL);
        if (session.getAttribute(EMAIL) == null){
            logger.info("User is not logged in");
            return LOGIN;
        }
        UserService userService = UserServiceImpl.getInstance();
        ContactService contactService = ContactServiceImpl.getInstance();
        User user;
        try {
            user = userService.findUserByEmail(userEmail);
        } catch (ServiceException e) {
            logger.error("User cannot be founded", e);
            throw new CommandException(e);
        }
        List<Contact> contacts;
        try {
            contacts = contactService.getContacts(user.getId());
        } catch (ServiceException e) {
            logger.error("Contacts cannot be founded", e);
            throw new CommandException(e);
        }
        session.setAttribute(CONTACT_LIST, contacts);
        logger.info("Contacts list returned");
        return CONTACTS;
    }
}
