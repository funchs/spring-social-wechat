package org.springframework.social.wechat.config.xml;

import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.config.xml.AbstractProviderConfigNamespaceHandler;

/**
 * @author renq
 */
public class WeChatNamespaceHandler extends AbstractProviderConfigNamespaceHandler {
    @Override
    protected AbstractProviderConfigBeanDefinitionParser getProviderConfigBeanDefinitionParser() {
        return null;
    }
}
