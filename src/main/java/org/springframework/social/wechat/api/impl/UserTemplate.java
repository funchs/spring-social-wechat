package org.springframework.social.wechat.api.impl;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.URIBuilder;
import org.springframework.social.wechat.api.UserOperations;
import org.springframework.social.wechat.api.model.UserInfo;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author renq
 */
public class UserTemplate extends AbstractOAuth2ApiBinding implements UserOperations {

    private final String accessToken;

    private static final String URI_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";

    public UserTemplate(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public UserInfo getUserInfo(String openid) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("openid", openid);
        params.add("lang", "zh-CN");
        params.add("access_token", accessToken);
        return this.getRestTemplate().getForObject(URIBuilder.fromUri(URI_USER_INFO).queryParams(params).build(), UserInfo.class);
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        return converter;
    }
}
