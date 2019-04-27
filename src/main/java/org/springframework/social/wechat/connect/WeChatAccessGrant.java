package org.springframework.social.wechat.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author renq
 */
public class WeChatAccessGrant extends AccessGrant {

    private String openid;

    private String unionid;

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, String openid, String unionid) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.openid = openid;
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
