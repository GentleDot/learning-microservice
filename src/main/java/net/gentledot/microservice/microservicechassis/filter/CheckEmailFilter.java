package net.gentledot.microservice.microservicechassis.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmailFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String developerEmail = httpRequest.getHeader("X-Developer-Email");

        if (StringUtils.isBlank(developerEmail) || !checkEmailAddress(developerEmail)) {
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

            PrintWriter writer = response.getWriter();
            writer.print("{\"error\":\"Invalid email\"}");

            return;
        }

        chain.doFilter(request, response);
    }

    private boolean checkEmailAddress(String address) {
        Pattern pattern = Pattern.compile("([\\w~\\-.+]+)@([\\w~\\-]+)\\.([\\w~\\-]+)");
        Matcher matcher = pattern.matcher(address);

        boolean matchResult = matcher.matches();
        if (matchResult) {
            log.info("checkEmail_Name : {}", matcher.group(1));
            log.info("checkEmail_domainName : {}", matcher.group(2));
            log.info("checkEmail_domain : {}", matcher.group(3));
        }

        return matchResult;
    }

}
