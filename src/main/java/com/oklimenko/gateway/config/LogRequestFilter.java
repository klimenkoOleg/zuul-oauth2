package com.oklimenko.gateway.config;


import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LogRequestFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(LogRequestFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = new HttpServletRequestWrapper(ctx.getRequest());
        String requestData = null;
        try {
            if (request.getContentLength() > 0) {
                requestData = CharStreams.toString(request.getReader());
            }
        } catch (Exception e) {
            log.error("Error parsing request", e);
//            throw e;
        }
//        try {
        String line = String.format("Request, %s,%s,%s \r\n",
                request.getRequestURL(),
                request.getMethod(), requestData);

        System.out.println(line);
        log.error(line);
        log.debug(line);
//            BufferedWriter bw = Files.newBufferedWriter(Paths.get(recordFile),
//                    Charset.forName("UTF-8"), StandardOpenOption.APPEND);
//            bw.write(line);
//            bw.close();
//        } catch (IOException e) {
//            log.error("Error writing request", e);
//            throw e;
//        }
        return null;
    }
}