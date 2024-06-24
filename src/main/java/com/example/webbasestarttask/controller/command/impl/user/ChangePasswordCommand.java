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

import static com.example.webbasestarttask.util.constant.AttributeConstant.*;
import static com.example.webbasestarttask.util.constant.ParamConstant.*;
import static com.example.webbasestarttask.util.constant.PagePath.*;

public class ChangePasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String oldPassword = request.getParameter(PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute(EMAIL);
            try {
                if (userService.changePassword(oldPassword, newPassword, username)) {
                    page = PASSWORD_CHANGE_SUCCESS;
                } else {
                    page = CHANGE_PASSWORD;
                    request.setAttribute("password_msg", "incorrect password");
                }
                session.setAttribute(CURRENT_PAGE, page);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return new Router(page);
    }
}
