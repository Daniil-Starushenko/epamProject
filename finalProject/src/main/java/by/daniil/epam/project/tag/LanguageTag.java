package by.daniil.epam.project.tag;

import by.daniil.epam.project.controller.LanguageFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageTag extends TagSupport {
    private static Logger logger = LogManager.getLogger(LanguageTag.class);
    private String actionUrl;
    private String jspPage;

    public void setJspPage(String jspPage) {
        this.jspPage = jspPage;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
//    <div class="language">
//    <c:url value="/user/language.html" var="languageUrl"/>
//    <form action="${languageUrl}" method="post">
//        <button name="lang" value="eng" type="submit" class="all_button">
//            <fmt:message key="english" bundle="${message}"/>
//        </button>
//        <button name="lang" value="rus" type="submit" class="all_button">
//            <fmt:message key="russian" bundle="${message}"/>
//        </button>
//    </form>
//     </div>

    @Override
    public int doStartTag() throws JspException {
        JspWriter jspWriter = pageContext.getOut();
        Locale locale = getCurrentLocale((HttpServletResponse)pageContext.getResponse(), pageContext.getSession());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("text.properties", locale);

        try {
            jspWriter.write("<div class=\"language\">");
            jspWriter.write(String.format("<form action=\"%s\" method=\"post\">", actionUrl));
            jspWriter.write(String.format("<button name=\"lang\" value=\"eng\" type=\"submit\" class=\"all_button\">" +
                    "%s</button>" , resourceBundle.getString("english")));
            jspWriter.write(String.format("<button name=\"lang\" value=\"rus\" type=\"submit\" class=\"all_button\">" +
                    "%s</button>" , resourceBundle.getString("russian")));
            jspWriter.write("</form>");
            jspWriter.write("</div>");
        } catch (IOException e) {
            logger.error(e);
        }
        return SKIP_BODY;
    }

    private Locale getCurrentLocale(HttpServletResponse response, HttpSession session) {
        String localeText = String.valueOf(session.getAttribute("text"));
        Locale currentLocale;
        if (session.getAttribute("text") != null) {
           String[] parameters = localeText.split("_");
           currentLocale = new Locale(parameters[0], parameters[1]);
        } else {
            currentLocale = new Locale("en", "US");
        }
        session.setAttribute("text", currentLocale);
        Cookie localeCookie = new Cookie("text", currentLocale.getLanguage() + "_" + currentLocale.getCountry());
        response.addCookie(localeCookie);
        return currentLocale;
    }
}
