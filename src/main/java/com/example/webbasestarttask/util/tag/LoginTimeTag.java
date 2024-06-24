package com.example.webbasestarttask.util.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

import static com.example.webbasestarttask.util.constant.AttributeConstant.TIME;

public class LoginTimeTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String time = (String) pageContext.getSession().getAttribute(TIME);

        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
