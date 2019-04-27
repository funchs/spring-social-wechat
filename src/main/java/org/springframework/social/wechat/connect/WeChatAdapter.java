package org.springframework.social.wechat.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.wechat.api.WeChat;
import org.springframework.social.wechat.api.model.UserInfo;

/**
 * @author renq
 */
public class WeChatAdapter implements ApiAdapter<WeChat> {

    private String openid;

    public WeChatAdapter() {
    }

    public WeChatAdapter(String openid) {
        this.openid = openid;
    }

    @Override
    public boolean test(WeChat weChat) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(WeChat weChat, ConnectionValues connectionValues) {
        UserInfo userInfo = weChat.userOperations().getUserInfo(openid);
        connectionValues.setProviderUserId(userInfo.getOpenid());
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getHeadimgurl());
        connectionValues.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(WeChat weChat) {
        UserInfo userInfo = weChat.userOperations().getUserInfo(openid);
        return new UserProfileBuilder().setName(userInfo.getNickname())
                .setUsername("WeChat_" + userInfo.getOpenid()).build();
    }

    @Override
    public void updateStatus(WeChat weChat, String s) {

    }
}
