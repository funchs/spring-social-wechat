package org.springframework.social.wechat.api;

import org.springframework.social.ApiBinding;

/**
 * @author renq
 */
public interface WeChat extends ApiBinding {

    UserOperations userOperations();
}
