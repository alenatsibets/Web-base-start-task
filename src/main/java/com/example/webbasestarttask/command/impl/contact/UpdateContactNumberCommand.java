package com.example.webbasestarttask.command.impl.contact;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.ContactService;
import com.example.webbasestarttask.service.impl.ContactServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.webbasestarttask.util.PagePath.CONTACT_SUCCESS;
import static com.example.webbasestarttask.util.PagePath.UNSUCCESSFUL;
import static com.example.webbasestarttask.util.ParamConstant.PARAM_CONTACT_NAME;
import static com.example.webbasestarttask.util.ParamConstant.PARAM_NUMBER;

public class UpdateContactNumberCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ContactService contactService = ContactServiceImpl.getInstance();
        String name = request.getParameter(PARAM_CONTACT_NAME);
        String number = request.getParameter(PARAM_NUMBER);
        try {
            if (contactService.updateContactName(name, number)){
                logger.info("Contact updated successfully");
                return CONTACT_SUCCESS;
            } else {
                logger.info("Update failed");
            }
        } catch (ServiceException e) {
            logger.error("Contacts cannot be updated", e);
            throw new CommandException(e);
        }
        return UNSUCCESSFUL;
    }
}