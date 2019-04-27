package org.springframework.social.wechat.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.wechat.api.WeChat;
import org.springframework.social.wechat.api.UserOperations;
import org.springframework.social.wechat.api.model.UserInfo;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

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
}
