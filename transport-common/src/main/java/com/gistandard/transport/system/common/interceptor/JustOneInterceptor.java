package com.gistandard.transport.system.common.interceptor;

import com.gistandard.transport.system.common.annotation.JustOne;
import com.gistandard.transport.system.common.interceptor.service.JustOneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * 因为方法在集群某一机器调用时时间过长造成nginx请求超时，
 * nginx对请求进行转发到其他机器，造成请求了多次，这里通过拦截每次请求的reqId，
 * 来拦截掉多余请求，保证数据唯一
 * Created by m on 2016/9/5.
 */
public class JustOneInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(JustOneInterceptor.class);

    @Autowired
    private JustOneService justOneService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            JustOne annotation = method.getAnnotation(JustOne.class);
            if (annotation != null) {
                String requestId = request.getHeader("requestId");
                String seqId = request.getHeader("seqId");
//                MSHttpServletRequestWrapper requestWrapper = new MSHttpServletRequestWrapper(request);
//                byte[] body = requestWrapper.body;
//                String jsonStr = new String(body, "UTF-8");
//                AppBaseRequest reqBean = JSON.parseObject(jsonStr, AppBaseRequest.class);
                boolean b = justOneService.checkReqIdOne(requestId, seqId, method.getName());
                if (!b){
                    logger.info(method.getName() + "该业务不做重复请求,已被拦截");
                }
                return b;
            }
            return true;
        }else {
            return super.preHandle(request, response, handler);
        }
    }

    /***
     * Get request query string, form method : post
     *
     * @param request
     * @return byte[]
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        /*当无请求参数时，request.getContentLength()返回-1 */
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /***
     * Get request query string, form method : post
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    public static class MSHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private final byte[] body; //报文

        public MSHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            body = getRequestPostBytes(request);
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
}
