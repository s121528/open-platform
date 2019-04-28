package com.io.sdk.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.core.SpringVersion;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 我的自定义RestTemplate
 * @author Alan
 *
 */
@Component
public class MkRestTemplate extends RestTemplate {
	
	private static final boolean fastjsonPresent = ClassUtils.isPresent("com.alibaba.fastjson.JSON", MkRestTemplate.class.getClassLoader());
	
	public MkRestTemplate() {
		super();
		    SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
	        httpRequestFactory.setReadTimeout(1800000);
	        httpRequestFactory.setConnectTimeout(120000);
		this.setRequestFactory(httpRequestFactory);
		List<HttpMessageConverter<?>> newMsgConverter = this.getMessageConverters();
		
		//去除所有的Json序列化,重新加载
		for (int i = 0; i < newMsgConverter.size(); ) {
			if(newMsgConverter.get(i).getClass().equals(StringHttpMessageConverter.class)){
				StringHttpMessageConverter httpMessageConverter = (StringHttpMessageConverter) newMsgConverter.get(i);
				httpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
			}
			
			if (newMsgConverter.get(i).getClass().equals(org.springframework.http.converter.json.MappingJackson2HttpMessageConverter.class)) {
				newMsgConverter.remove(i);
				continue;
			}
			
			i++;
		}

		//如果Fastjson存在,加载fastjson,并且根据版本号,自动添加不同的转换器.Spring4.2更新.
		if(fastjsonPresent){
			String version = SpringVersion.getVersion().replaceAll("[\\.\\D]", "");
			
			List<MediaType> supportedMediaTypes = new ArrayList<>();
			supportedMediaTypes.add(new MediaType("application", "json", StandardCharsets.UTF_8));
			supportedMediaTypes.add(new MediaType("application", "*+json", StandardCharsets.UTF_8));
			
			if(Integer.parseInt(version) >= 420){
				FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4 = new FastJsonHttpMessageConverter4();
				fastJsonHttpMessageConverter4.setSupportedMediaTypes(supportedMediaTypes);
				newMsgConverter.add(0, fastJsonHttpMessageConverter4);
			} else {
				FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
				fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
				newMsgConverter.add(0, fastJsonHttpMessageConverter);
			}
			
		}
		
	}
	
	public MkRestTemplate(ClientHttpRequestFactory requestFactory) {
		this();
		setRequestFactory(requestFactory);
	}

	public MkRestTemplate(List<HttpMessageConverter<?>> messageConverters) {
		super(messageConverters);
	}

	public <T> T putForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
		RequestCallback requestCallback = httpEntityCallback(request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<>(responseType, getMessageConverters());
		return execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
	}

	public <T> T putForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {

		RequestCallback requestCallback = httpEntityCallback(request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(responseType, getMessageConverters());
		return execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
	}

	public <T> T putForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
		RequestCallback requestCallback = httpEntityCallback(request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(responseType, getMessageConverters());
		return execute(url, HttpMethod.PUT, requestCallback, responseExtractor);
	}

	public <T> T deleteForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
		RequestCallback requestCallback = httpEntityCallback(request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<>(responseType, getMessageConverters());
		return execute(url, HttpMethod.DELETE, requestCallback, responseExtractor, uriVariables);
	}

	public <T> T deleteForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {

		RequestCallback requestCallback = httpEntityCallback(request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(responseType, getMessageConverters());
		return execute(url, HttpMethod.DELETE, requestCallback, responseExtractor, uriVariables);
	}

	public <T> T deleteForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
		RequestCallback requestCallback = httpEntityCallback(request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(responseType, getMessageConverters());
		return execute(url, HttpMethod.DELETE, requestCallback, responseExtractor);
	}


}
