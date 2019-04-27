package org.springframework.social.wechat.api.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.wechat.api.UserOperations;
import org.springframework.social.wechat.api.WeChat;

/**
 * @author renq
 */
public class WeChatTemplate extends AbstractOAuth2ApiBinding implements WeChat {

    private UserOperations userOperations;

    public WeChatTemplate(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(this.getRestTemplate().getRequestFactory()));
        this.userOperations = new UserTemplate(accessToken);
    }

    @Override
    public UserOperations userOperations() {
        return userOperations;
    }
}
