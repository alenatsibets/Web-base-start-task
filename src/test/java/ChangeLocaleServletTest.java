import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.webbasestarttask.controller.ChangeLocaleServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
public class ChangeLocaleServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher requestDispatcher;

    @InjectMocks
    ChangeLocaleServlet servlet;

    @BeforeEach
    void setUp() throws ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getHeader("referer")).thenReturn("/login.jsp");
    }

    @Test
    void testDoGet() throws IOException {
        when(request.getParameter("lang")).thenReturn("be");
        Locale expectedLocale = new Locale("be");
        servlet.doGet(request, response);

        verify(session).setAttribute(eq("locale"), eq(expectedLocale));
        verify(response).sendRedirect("/login.jsp");
    }

    @Test
    void testAddedCookie() throws IOException {
        when(request.getParameter("lang")).thenReturn("en");
        Locale expectedLocale = new Locale("en");
        servlet.doGet(request, response);

        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        verify(response).addCookie(cookieCaptor.capture());

        List<Cookie> capturedCookies = cookieCaptor.getAllValues();

        assertEquals(1, capturedCookies.size());

        Cookie localeCookie = capturedCookies.get(0);
        assertEquals("locale", localeCookie.getName());
        assertEquals("en", localeCookie.getValue());
        assertEquals(60 * 60 * 24 * 7, localeCookie.getMaxAge());
    }
}
