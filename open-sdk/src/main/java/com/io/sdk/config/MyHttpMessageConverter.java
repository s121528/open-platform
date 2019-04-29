package com.io.sdk.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * base 64位加密  消息转化器
 *
 * @author 马凯
 * @date 2018年4月3日
 */
//@Component
public class MyHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    public final static Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> supportedMediaTypes2 = new ArrayList<>();
        supportedMediaTypes2.add(new MediaType("text", "html", UTF8));
        supportedMediaTypes2.add(new MediaType("text", "html", Charset.forName("GBK")));
        return supportedMediaTypes2;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        XmlMapper xmlMapper = new XmlMapper();
        String copyToString = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("GBK"));
        byte[] decode = Base64.getDecoder().decode(copyToString);
        if (clazz.equals(String.class)) {
            return new String(decode);
        }
        Object readValue = xmlMapper.readValue(new String(decode), clazz);
        return readValue;
    }

    @Override
    protected void writeInternal(Object t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        byte[] encode;
        if (t.getClass().equals(String.class)) {
            String writeValueAsString = (String) t;
            encode = writeValueAsString.getBytes();
        } else {
            XmlMapper xmlMapper = new XmlMapper();
            String writeValueAsString = xmlMapper.writeValueAsString(t);
            encode = Base64.getEncoder().encode(writeValueAsString.getBytes());
        }
        outputMessage.getBody().write(encode);

    }

}
