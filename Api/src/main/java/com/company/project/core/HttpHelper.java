package com.company.project.core;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpHelper {
	public static String post(String jsonStr, String url, String appId){
		// 设置超时时间
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000)
				.setConnectionRequestTimeout(3000).build();

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		post.setConfig(defaultRequestConfig);

		// 传json字符串 "{\"key\":\"value\"}"
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(jsonStr);

		stringEntity.setContentType("text/json");
		stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(stringEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ResponseHandler<String> responseHandler = response -> {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		};

		try {
			return httpClient.execute(post, responseHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
