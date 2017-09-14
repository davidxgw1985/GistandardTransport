package com.gistandard.transport.system.common.wrapper;

import com.gistandard.transport.system.utils.HttpHelper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by m on 2017/4/19.
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

        };
    }
}