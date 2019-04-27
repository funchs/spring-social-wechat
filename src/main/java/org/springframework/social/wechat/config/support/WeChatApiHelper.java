package org.springframework.social.wechat.config.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.wechat.api.WeChat;

/**
 * @author renq
 */
public class WeChatApiHelper implements ApiHelper<WeChat> {

    private final UsersConnectionRepository usersConnectionRepository;

    private final UserIdSource userIdSource;

    public WeChatApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userIdSource = userIdSource;
    }

    @Override
    public WeChat getApi() {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting API binding instance for WeChat");
        }

        Connection<WeChat> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId()).findPrimaryConnection(WeChat.class);
        if (logger.isDebugEnabled() && connection == null) {
            logger.debug("No current connection; Returning default WeChatTemplate instance.");
        }
        return connection != null ? connection.getApi() : null;
    }

    private final static Log logger = LogFactory.getLog(WeChatApiHelper.class);

}
