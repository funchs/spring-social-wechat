package org.springframework.social.wechat.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author renq
 */
@ConfigurationProperties(prefix = "spring.social.wechat")
public class WeChatProperties {

    private String appId;
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
