package com.example.webbasestarttask.controller.command.impl.user;

import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.Router;
import com.example.webbasestarttask.model.exception.CommandException;
import com.example.webbasestarttask.model.exception.ServiceException;
import com.example.webbasestarttask.model.service.UserService;
import com.example.webbasestarttask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.constant.AttributeConstant.EMAIL;
import static com.example.webbasestarttask.util.constant.PagePath.*;

public class DeleteUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(EMAIL) == null) {
            logger.info("User is not logged in");
            return new Router(LOGIN);
        }
        String userEmail = (String) session.getAttribute(EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (userService.deleteUser(userEmail)) {
                return new Router("view_login", Router.RouteType.REDIRECT);
            } else {
                logger.info("Delete failed");
            }
        } catch (ServiceException e) {
            logger.error("User cannot be deleted", e);
            throw new CommandException(e);
        }
        return new Router(UNSUCCESSFUL);
    }
}
