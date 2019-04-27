package org.springframework.social.wechat.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.wechat.api.WeChat;
import org.springframework.social.wechat.api.impl.WeChatTemplate;

/**
 * @author renq
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    public WeChatServiceProvider(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret));
    }

    @Override
    public WeChat getApi(String accessToken) {
        return new WeChatTemplate(accessToken);
    }
}
