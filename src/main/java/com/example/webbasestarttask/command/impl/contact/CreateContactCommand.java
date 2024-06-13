package com.example.webbasestarttask.command.impl.contact;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.entity.User;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.ContactService;
import com.example.webbasestarttask.service.UserService;
import com.example.webbasestarttask.service.impl.ContactServiceImpl;
import com.example.webbasestarttask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.webbasestarttask.util.AttributeConstant.EMAIL;
import static com.example.webbasestarttask.util.PagePath.CONTACT_SUCCESS;
import static com.example.webbasestarttask.util.ParamConstant.PARAM_CONTACT_NAME;
import static com.example.webbasestarttask.util.ParamConstant.PARAM_NUMBER;

public class CreateContactCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = (String) request.getSession().getAttribute(EMAIL);
        String name = request.getParameter(PARAM_CONTACT_NAME);
        String number = request.getParameter(PARAM_NUMBER);
        UserService userService = UserServiceImpl.getInstance();
        ContactService contactService = ContactServiceImpl.getInstance();
        User user;
        try {
            user = userService.findUserByEmail(email);
        } catch (ServiceException e) {
            logger.error("User cannot be founded", e);
            throw new CommandException(e);
        }
        try {
            contactService.createContact(name, number, user.getId());
        } catch (ServiceException e) {
            logger.error("Contact cannot to be created", e);
            throw new CommandException(e);
        }
        logger.info("Contact created");
        return CONTACT_SUCCESS;
    }
}
