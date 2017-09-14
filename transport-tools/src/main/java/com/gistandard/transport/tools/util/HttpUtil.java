package com.gistandard.transport.tools.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.tools.bean.http.HttpResult;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yujie
 * @ClassName HttpUtil
 * @Description
 * @Version 1.0
 * @Date 2015-07-15
 */
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * http post方式请求url
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static HttpResult post(String url, Map<String, Object> params) {
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvpList = null;
		if (params != null && params.size() > 0) {
			nvpList = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvpList.add(new BasicNameValuePair(key, StringUtil.getNotNullStr(params.get(key), "")));
			}
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nvpList));
			} catch (UnsupportedEncodingException e) {
				logger.info("http post param set error : {}", e.getCause());
			}
		}
		return httpExecute(httpPost);
	}

	/**
	 * http get方式请求url
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static HttpResult get(String url, Map<String, Object> params) {
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append(url);
		if (params != null && params.size() > 0) {
			if (url.indexOf("?") < 0) {
				paramBuilder.append("?");
			}
			if (!url.endsWith("&") && !url.endsWith("?")) {
				paramBuilder.append("&");
			}
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				paramBuilder.append(key).append("=").append(StringUtil.getNotNullStr(params.get(key), "")).append("&");
			}
			paramBuilder.deleteCharAt(paramBuilder.length() - 1);
		}
		logger.info("http get url is : {}", paramBuilder.toString());
		HttpGet httpGet = new HttpGet(paramBuilder.toString());
		return httpExecute(httpGet);
	}

	/**
	 * http post方式请求url
	 * @param url
	 * @param params
	 * @return
	 */
	public static HttpResult post(String url, Map<String, Object> params,Map<String,String> req_headers) {
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvpList = null;
		if(params != null && params.size() > 0){
			nvpList = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvpList.add(new BasicNameValuePair(key, StringUtil.getNotNullStr(params.get(key),"")));
			}
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nvpList));
			} catch (UnsupportedEncodingException e) {
				logger.info("http post param set error : {}", e.getCause());
			}
		}

		for(Map.Entry<String, String> so : req_headers.entrySet()){
			httpPost.setHeader(so.getKey(),so.getValue());
		}

		return httpExecute(httpPost);
	}

	public static HttpResult httpExecute(HttpUriRequest request) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpResult httpResult = null;
		try {
			httpResult = httpClient.execute(request, getResponseHandler());
		} catch (Exception e) {
			logger.info("http req url[{}] failed, reason is :{}", request.getURI(), e.getCause());
			httpResult.setStatusCode(-1);
			httpResult.setStatus(false);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpResult;
	}

	public static ResponseHandler<HttpResult> getResponseHandler() {
		return new ResponseHandler<HttpResult>() {
			@Override
			public HttpResult handleResponse(final HttpResponse response) throws IOException {
				HttpResult httpResult = new HttpResult();
				int status = response.getStatusLine().getStatusCode();
				httpResult.setStatusCode(status);
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					httpResult.setStatus(true);
					httpResult.setResult(entity.toString());
				} else {
					httpResult.setStatus(false);
				}
				return httpResult;
			}
		};
	}

	public static String jsonPost(String url, Map<String, Object> requestHeaderMap, Object objParam) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod myPost = new PostMethod(url);
		String responseString = null;

		myPost.setRequestHeader("Content-Type", "application/json");
		myPost.setRequestHeader("charset", "UTF-8");

		if (requestHeaderMap != null && requestHeaderMap.size() > 0) {
			for (Entry<String, Object> entry : requestHeaderMap.entrySet()) {
				myPost.setRequestHeader((String) entry.getKey(), (String) entry.getValue());
			}
		}
		myPost.setRequestEntity(new ByteArrayRequestEntity(JSON.toJSONString(objParam).getBytes("UTF-8")));
		int statusCode = client.executeMethod(myPost);
		if (statusCode == 200) {
			BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
			byte[] bytes = new byte[1024];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int count = 0;
			while ((count = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, count);
			}
			byte[] strByte = bos.toByteArray();
			responseString = new String(strByte, 0, strByte.length, "UTF-8");
			bos.close();
			bis.close();
		}
		myPost.releaseConnection();
		return responseString;
	}

}
