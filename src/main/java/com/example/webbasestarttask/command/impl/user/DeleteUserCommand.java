package com.example.webbasestarttask.command.impl.user;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.UserService;
import com.example.webbasestarttask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.AttributeConstant.EMAIL;
import static com.example.webbasestarttask.util.PagePath.*;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(EMAIL) == null){
            logger.info("User is not logged in");
            return LOGIN;
        }
        String userEmail = (String) session.getAttribute(EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (userService.deleteUser(userEmail)){
                return MAIN;
            } else {
                logger.info("Delete failed");
            }
        } catch (ServiceException e) {
            logger.error("User cannot be deleted", e);
            throw new CommandException(e);
        }
        return UNSUCCESSFUL;
    }
}
