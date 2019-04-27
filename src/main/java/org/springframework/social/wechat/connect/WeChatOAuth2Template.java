package org.springframework.social.wechat.connect;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author renq
 */
public class WeChatOAuth2Template extends OAuth2Template {

    private static final String URI_QR_CONNECT = "https://open.weixin.qq.com/connect/qrconnect";
    private static final String URI_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String URI_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeChatOAuth2Template(String appId, String appSecret) {
        super(appId, appSecret, URI_QR_CONNECT, URI_ACCESS_TOKEN);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        parameters.set("appid", parameters.getFirst("client_id"));
        parameters.set("secret", parameters.getFirst("client_secret"));
        parameters.remove("client_id");
        parameters.remove("client_secret");
        return super.postForAccessGrant(accessTokenUrl, parameters);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> parameters) {
        parameters.set("appid", parameters.getFirst("client_id"));
        parameters.remove("client_id");
        parameters.remove("client_secret");
        parameters.set("refresh_token", refreshToken);
        parameters.set("grant_type", "refresh_token");
        return super.postForAccessGrant(URI_REFRESH_TOKEN, parameters);
    }

    @Override
    protected WeChatAccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
        return new WeChatAccessGrant(accessToken, scope, refreshToken, expiresIn, (String) response.get("openid"), (String) response.get("unionid"));
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return replaceParamKey(super.buildAuthorizeUrl(parameters));
    }

    @Override
    public String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters parameters) {
        return replaceParamKey(super.buildAuthorizeUrl(grantType, parameters));
    }

    private String replaceParamKey(String url) {
        return url.replace("client_id", "appid").replace("client_secret", "secret");
    }

}
