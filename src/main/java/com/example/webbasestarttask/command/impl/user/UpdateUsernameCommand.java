package com.example.webbasestarttask.command.impl.user;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.entity.User;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.UserService;
import com.example.webbasestarttask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.webbasestarttask.util.AttributeConstant.EMAIL;
import static com.example.webbasestarttask.util.PagePath.UNSUCCESSFUL;
import static com.example.webbasestarttask.util.PagePath.USER_SUCCESS;
import static com.example.webbasestarttask.util.ParamConstant.PARAM_USERNAME;

public class UpdateUsernameCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String userEmail = (String) request.getSession().getAttribute(EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        User user;
        try {
            user = userService.findUserByEmail(userEmail);
        } catch (ServiceException e) {
            logger.error("User cannot be founded", e);
            throw new CommandException(e);
        }
        String username = request.getParameter(PARAM_USERNAME);
        try {
            if (userService.updateUsername(user, username)){
                logger.info("User updated successfully");
                return USER_SUCCESS;
            } else {
                logger.info("Update failed");
            }
        } catch (ServiceException e) {
            logger.error("Cannot update user", e);
            throw new CommandException(e);
        }
        return UNSUCCESSFUL;
    }
}
