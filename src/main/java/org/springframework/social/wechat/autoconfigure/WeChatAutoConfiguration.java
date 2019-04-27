package org.springframework.social.wechat.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.wechat.api.WeChat;
import org.springframework.social.wechat.connect.WeChatConnectionFactory;

/**
 * @author renq
 */
@Configuration
@ConditionalOnClass({ SocialConfigurerAdapter.class, WeChatConnectionFactory.class })
@ConditionalOnProperty(prefix = "spring.social.wechat", name = "app-id")
@AutoConfigureBefore(SecurityAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WeChatAutoConfiguration {

	@Configuration
	@EnableSocial
	@EnableConfigurationProperties(WeChatProperties.class)
	@ConditionalOnWebApplication
	protected static class WeChatConfigurerAdapter extends SocialConfigurerAdapter {

		private final WeChatProperties properties;

		protected WeChatConfigurerAdapter(WeChatProperties properties) {
			this.properties = properties;
		}

		@Bean
		@ConditionalOnMissingBean(WeChat.class)
		@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
		public WeChat qq(ConnectionRepository repository) {
			Connection<WeChat> connection = repository
					.findPrimaryConnection(WeChat.class);
			return connection != null ? connection.getApi() : null;
		}

		@Override
		public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
			configurer.addConnectionFactory(this.createConnectionFactory());
		}

		private ConnectionFactory<WeChat> createConnectionFactory() {
			return new WeChatConnectionFactory(this.properties.getAppId(),
					this.properties.getAppSecret());
		}

	}

}