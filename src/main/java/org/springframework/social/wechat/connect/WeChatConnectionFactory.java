package org.springframework.social.wechat.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.wechat.api.WeChat;

/**
 * @author renq
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    public WeChatConnectionFactory(String appId, String appSecret) {
        super("wechat", new WeChatServiceProvider(appId, appSecret), new WeChatAdapter());
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        return ((WeChatAccessGrant) accessGrant).getOpenid();
    }

    @Override
    public Connection<WeChat> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<>(this.getProviderId(), this.extractProviderUserId(accessGrant), accessGrant.getAccessToken(), accessGrant.getRefreshToken(), accessGrant.getExpireTime(), (OAuth2ServiceProvider<WeChat>) this.getServiceProvider(), new WeChatAdapter(this.extractProviderUserId(accessGrant)));
    }
}
