package com.gistandard.transport.system.frame.disruptor.init;

import com.gistandard.transport.system.frame.disruptor.DisruptorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by m on 2016/12/7.
 */
public class DisruptorServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(DisruptorServlet.class);

    @Override
    public void destroy() {
        DisruptorHelper.getInstance().shutdown();
        logger.info("DisruptorServlet destroy");
    }

    @Override
    public void init() throws ServletException {
        DisruptorHelper.getInstance().start();
        logger.info("DisruptorServlet init");
    }
}
