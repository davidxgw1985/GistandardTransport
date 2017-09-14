package com.gistandard.transport.tools.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by m on 2016/5/25.
 */
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 定义编码格式 UTF-8
     */
    public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";

    /**
     * 定义编码格式 GBK
     */
    public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";

    private static final String URL_PARAM_CONNECT_FLAG = "&";

    private static final String EMPTY =null;

    private static MultiThreadedHttpConnectionManager connectionManager = null;

    private static int connectionTimeOut = 25000;

    private static int socketTimeOut = 25000;

    private static int maxConnectionPerHost = 200;

    private static int maxTotalConnections = 200;

    private static HttpClient client;

    static{
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
        connectionManager.getParams().setSoTimeout(socketTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
        client = new HttpClient(connectionManager);
    }

    /**
     * POST方式提交数据
     * @param url
     *          待请求的URL
     * @param params
     *          要提交的数据
     * @param req_headers
     *          编码
     * @return
     *          响应结果
     * @throws IOException
     *          IO异常
     */
    public static String URLPost(String url, Map<String, Object> params,Map<String,String> req_headers){
        StringBuffer info = new StringBuffer();
        info.append("vlep_plug-url:"+url);
        info.append("vlep_plug-params:"+params.toString());
        info.append("vlep_plug-req_headers:"+req_headers.toString());
        logger.info("vlep_plug-URLPost:"+info.toString());


        String response = EMPTY;
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);

            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            postMethod.setRequestHeader("charset","UTF-8");

            for(Entry<String, String> so : req_headers.entrySet()){
                postMethod.setRequestHeader(so.getKey(),so.getValue());
            }

            //将表单的值放入postMethod中
            Set<String> keySet = params.keySet();
            String value =   null;
            for(String key : keySet){
                if(params.get(key) != null){
                    if(params.get(key).getClass().isArray()){
                        Object[] a_obj = (Object[])params.get(key);
                        for(Object obj : a_obj){
                            postMethod.addParameter(key, obj == null ? "" : obj.toString());
                        }
                    }else{
                        value = params.get(key).toString();
                        postMethod.addParameter(key, value);
                    }
                }
            }
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");


            //执行postMethod
            int statusCode = client.executeMethod(postMethod);
            if(statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            }else{
                logger.error("响应状态码 = " + postMethod.getStatusCode());
            }
        }catch(HttpException e){
            logger.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
            e.printStackTrace();
        }catch(IOException e){
            logger.error("发生网络异常", e);
            e.printStackTrace();
        }finally{
            if(postMethod != null){
                postMethod.releaseConnection();
                postMethod = null;
            }
            info = null;
        }

        logger.info("vlep_plug-response:"+response);
        return response;
    }

    /**
     * JSON Post提交
     * @param url 地址
     * @param req_headers 头部
     * @param obj 参数
     * @return
     */
    public static String jsonPost(String url,Map<String,String> req_headers,Object obj) {
        //创建httpclient工具对象
//        HttpClient client = new HttpClient();
        //创建post请求方法
        PostMethod myPost = new PostMethod(url);
        //设置请求超时时间
//        client.setConnectionTimeout(300*1000);
        String responseString = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        try {
            //设置请求头部类型
            myPost.setRequestHeader("Content-Type","application/json");
            myPost.setRequestHeader("charset","UTF-8");

            for(Entry<String, String> so : req_headers.entrySet()){
                myPost.setRequestHeader(so.getKey(),so.getValue());
            }

            //设置请求体，即xml文本内容，注：这里写了两种方式，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
//	          myPost.setRequestBody(xmlString);

            //设置XML文件流
//	            InputStream body=this.getClass().getResourceAsStream("/"+xmlFileName);
//	            myPost.setRequestBody();
            myPost.setRequestEntity(new ByteArrayRequestEntity(JSON.toJSONString(obj).getBytes("UTF-8")));
            int statusCode = client.executeMethod(myPost);
            if(statusCode == HttpStatus.SC_OK){
                bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                bos = new ByteArrayOutputStream();
                int count = 0;
                while((count = bis.read(bytes))!= -1){
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                responseString = new String(strByte,0,strByte.length,"UTF-8");
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if (bos != null) bos.close();
                if (bis != null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myPost.releaseConnection();

        logger.info("vlep_plug-response:"+responseString);
        return responseString;
    }

    /**
     * JSON Post提交(做快捷支付的时候考虑到http异常进行了特殊处理)
     * @param url 地址
     * @param req_headers 头部
     * @param obj 参数
     * @return
     */
    public static String jsonPostPay(String url,Map<String,String> req_headers,Object obj) {
        //创建httpclient工具对象
//        HttpClient client = new HttpClient();
        //创建post请求方法
        PostMethod myPost = new PostMethod(url);
        //设置请求超时时间
//        client.setConnectionTimeout(300*1000);
        String responseString = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        try {
            //设置请求头部类型
            myPost.setRequestHeader("Content-Type","application/json");
            myPost.setRequestHeader("charset","UTF-8");

            for(Entry<String, String> so : req_headers.entrySet()){
                myPost.setRequestHeader(so.getKey(),so.getValue());
            }

            //设置请求体，即xml文本内容，注：这里写了两种方式，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
//	          myPost.setRequestBody(xmlString);

            //设置XML文件流
//	            InputStream body=this.getClass().getResourceAsStream("/"+xmlFileName);
//	            myPost.setRequestBody();
            myPost.setRequestEntity(new ByteArrayRequestEntity(JSON.toJSONString(obj).getBytes("UTF-8")));
            int statusCode = client.executeMethod(myPost);
            if(statusCode == HttpStatus.SC_OK){
                bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                bos = new ByteArrayOutputStream();
                int count = 0;
                while((count = bis.read(bytes))!= -1){
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                responseString = new String(strByte,0,strByte.length,"UTF-8");
            }
        } catch (HttpException e) {
            e.printStackTrace();
            responseString = "exception";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            responseString = "exception";
        } catch (IOException e) {
            e.printStackTrace();
            responseString = "exception";
        } finally{
            try {
                if (bos != null) bos.close();
                if (bis != null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myPost.releaseConnection();

        logger.info("vlep_plug-response:"+responseString);
        return responseString;
    }


    /**
     * 发送xml数据请求到server端
     * @param url xml请求数据地址
     * @param req_headers 发送的xml数据流
     * @return null发送失败，否则返回响应内容
     */
    public static String xmlPost(String url,Map<String,String> req_headers,String xml){
        StringBuffer info = new StringBuffer();
        info.append("vlep_plug-url:"+url);
        info.append("vlep_plug-params:"+xml);
        info.append("vlep_plug-req_headers:"+req_headers.toString());
        logger.info("vlep_plug-xmlPost:"+info.toString());


        //创建httpclient工具对象
        HttpClient client = new HttpClient();
        //创建post请求方法
        PostMethod myPost = new PostMethod(url);
        //设置请求超时时间
        client.setConnectionTimeout(300*1000);
        String responseString = null;
        try{
            //设置请求头部类型
            myPost.setRequestHeader("Content-Type","text/xml");
            myPost.setRequestHeader("charset","UTF-8");

            for(Entry<String, String> so : req_headers.entrySet()){
                myPost.setRequestHeader(so.getKey(),so.getValue());
            }

            //设置请求体，即xml文本内容，注：这里写了两种方式，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
//	          myPost.setRequestBody(xmlString);

            //设置XML文件流
//	            InputStream body=this.getClass().getResourceAsStream("/"+xmlFileName);
//	            myPost.setRequestBody();
            myPost.setRequestEntity(new ByteArrayRequestEntity(xml.getBytes("UTF-8")));
            int statusCode = client.executeMethod(myPost);
            if(statusCode == HttpStatus.SC_OK){
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count = 0;
                while((count = bis.read(bytes))!= -1){
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                responseString = new String(strByte,0,strByte.length,"UTF-8");
                bos.close();
                bis.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            info = null;
        }
        myPost.releaseConnection();

        logger.info("vlep_plug-response:"+responseString);
        return responseString;
    }

    /**
     * GET方式提交数据
     * @param url
     *          待请求的URL
     * @param params
     *          要提交的数据
     * @param req_headers
     *          编码
     * @return
     *          响应结果
     * @throws IOException
     *          IO异常
     */
    public static String URLGet(String url, Map<String, Object> params,Map<String,String> req_headers){
        StringBuffer info = new StringBuffer();
        info.append("vlep_plug-url:"+url);
        info.append("vlep_plug-params:"+params);
        info.append("vlep_plug-req_headers:"+req_headers.toString());
        logger.info("vlep_plug-URLGet:"+info.toString());

        String response = EMPTY;
        GetMethod getMethod = null;
        StringBuffer strtTotalURL = new StringBuffer(url);

        if(params != null){
            if(strtTotalURL.indexOf("?") == -1) {
                strtTotalURL.append("?").append(getUrl(params, "UTF-8"));
            } else {
                strtTotalURL.append("&").append(getUrl(params, "UTF-8"));
            }
        }
        logger.debug("GET请求URL = \n" + strtTotalURL.toString());




        try {
            getMethod = new GetMethod(strtTotalURL.toString());

            //设置请求头部类型
            getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            getMethod.setRequestHeader("charset","UTF-8");

            for(Entry<String, String> so : req_headers.entrySet()){
                getMethod.setRequestHeader(so.getKey(),so.getValue());
            }


            //执行getMethod
            int statusCode = client.executeMethod(getMethod);
            if(statusCode == HttpStatus.SC_OK) {
                response = getMethod.getResponseBodyAsString();
            }else{
                logger.debug("响应状态码 = " + getMethod.getStatusCode());
            }
        }catch(HttpException e){
            logger.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
            e.printStackTrace();
        }catch(IOException e){
            logger.error("发生网络异常", e);
            e.printStackTrace();
        }finally{
            if(getMethod != null){
                getMethod.releaseConnection();
                getMethod = null;
            }
            info = null;
        }

        logger.info("vlep_plug-response:"+response);
        return response;
    }

    /**
     * 据Map生成URL字符串
     * @param map
     *          Map
     * @param valueEnc
     *          URL编码
     * @return
     *          URL
     */
    private static String getUrl(Map<String, Object> map, String valueEnc) {

        if (null == map || map.keySet().size() == 0) {
            return (EMPTY);
        }
        StringBuffer url = new StringBuffer();
        Set<String> keys = map.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            if (map.containsKey(key)) {
                String str = map.get(key) != null ? map.get(key).toString()  : null;
                try {
                    str = URLEncoder.encode(str, valueEnc);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL = EMPTY;
        strURL = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }

        return (strURL);
    }

    /**
     * 将bean转化为map
     *
     * @title convertBean
     * @describe TODO
     * @param bean
     * @return
     * @author M.simple
     * @version 1.0
     */
    public static Map convertBean(Object bean) {
        try {

            Class type = bean.getClass();
            Map returnMap = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
            return returnMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
//        String url = "http://172.16.2.80:8080/vlep_im/groupController/createTempGroup.do";
//        TempGroupRoomRequest tempGroupRoomRequest = new TempGroupRoomRequest();
//        tempGroupRoomRequest.setRoomName("XXXXT160A0000368(1)");
//        tempGroupRoomRequest.setCreator("CN0002519960410999984");
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("CN0002519960410999984");
//        tempGroupRoomRequest.setSubjectUser(arrayList);
//        tempGroupRoomRequest.setOperateType("0");
//        String resultStr = HttpClientUtil.URLPost(url, convertBean(tempGroupRoomRequest),
//                HeadAuthentication.setIMHead("002"));
//        JSONObject jsonObject = JSON.parseObject(resultStr);
//        boolean result = jsonObject.getBoolean("result");
//        if (result) {
//            System.out.println(jsonObject.getLong("object"));
//        }
        String url = "http://172.16.2.141:8089/vlep_im/msgSystemController/contentSystem.do";
        Map<String, Object> req = new HashMap<>();
        req.put("sysCode","1003");
        req.put("platAccounts","CN0002519960101000000");
        req.put("body","{\n" +
                " \"nickName\":\"下单人昵称\"  \n" +
                " \"busiBookNo\": \"NJ_YH_MBT160A0000283(1)\", --订单号\n" +
                " \"comQuoteId\": \"您的好友【下单人昵称】给您推送了一个订单【NJ_YH_MBT160A0000283(1)】,关注，确认接受本次关注？\"\n" +
                "     }");
        req.put("remindCode","1");
        String resultStr = HttpClientUtil.URLPost(url,req,
                HeadAuthentication.setIMHead("002"));
        System.out.print(resultStr);
    }
}

class TempGroupRoomRequest{

    private Long roomId;

    private String roomName;

    /**
     * 创建人
     */
    private String creator;

    private String operateType;

    /**
     * 房间成员
     */
    List<String> subjectUser;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<String> getSubjectUser() {
        return subjectUser;
    }

    public void setSubjectUser(List<String> subjectUser) {
        this.subjectUser = subjectUser;
    }

}
