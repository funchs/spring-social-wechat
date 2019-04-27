package org.springframework.social.wechat.config.xml;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.wechat.config.support.WeChatApiHelper;
import org.springframework.social.wechat.connect.WeChatConnectionFactory;
import org.springframework.social.wechat.security.WeChatAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

import java.util.Map;

/**
 * @author renq
 */
public class WeChatConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {

    protected WeChatConfigBeanDefinitionParser() {
        super(WeChatConnectionFactory.class, WeChatApiHelper.class);
    }

    @Override
    protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
        return WeChatAuthenticationService.class;
    }

    @Override
    protected BeanDefinition getConnectionFactoryBeanDefinition(String appId, String appSecret, Map<String, Object> allAttributes) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(WeChatConnectionFactory.class).addConstructorArgValue(appId).addConstructorArgValue(appSecret);
        return builder.getBeanDefinition();
    }

}
