package com.example.webbasestarttask.command.impl.user;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.entity.User;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.UserService;
import com.example.webbasestarttask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.AttributeConstant.EMAIL;
import static com.example.webbasestarttask.util.PagePath.LOGIN;
import static com.example.webbasestarttask.util.PagePath.USER;

public class ShowUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(EMAIL) == null){
            logger.info("User is not logged in");
            return LOGIN;
        }
        String userEmail = (String) session.getAttribute(EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        User user;
        try {
            user = userService.findUserByEmail(userEmail);
        } catch (ServiceException e) {
            logger.error("User cannot be founded", e);
            throw new CommandException(e);
        }
        session.setAttribute("user", user);
        logger.info("User data returned successfully");
        return USER;
    }
}