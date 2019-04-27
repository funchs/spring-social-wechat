package org.springframework.social.wechat.api;

import org.springframework.social.wechat.api.model.UserInfo;

/**
 * @author renq
 */
public interface UserOperations {

    UserInfo getUserInfo(String openid);

}
