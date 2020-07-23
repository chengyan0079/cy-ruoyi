package com.cy.ruoyi.mall.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *  配置授权中心
  */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	// accessToken有效期
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 7200; // 两小时
	// 刷新accessToken有效期
	private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 7200; // 两小时

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	// @Autowired
	// private UserDetailsService userDetailsService;

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// 添加授权用户
		clients.jdbc(dataSource);
//				.withClient("client_1").secret(new BCryptPasswordEncoder().encode("123456"))
//				.authorizedGrantTypes("password", "refresh_token", "authorization_code")// 授权范围
//				.redirectUris("http://www.baidu.com").authorities("ROLE_ADMIN", "ROLE_USER")// 客户端可以访问权限
//				.scopes("all").accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService());
	}

//	@Bean
//	UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//		userDetailsService.createUser(User.withUsername("user_1").password(new BCryptPasswordEncoder().encode("123456"))
//				.authorities("ROLE_USER").build());
//		userDetailsService.createUser(User.withUsername("user_2")
//				.password(new BCryptPasswordEncoder().encode("1234567")).authorities("ROLE_USER").build());
//		return userDetailsService;
//	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
				.allowFormAuthenticationForClients();// 允许表单登陆

	}

	@Bean
	public AuthenticationManager authenticationManager() {
		AuthenticationManager authenticationManager = new AuthenticationManager() {

			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return daoAuhthenticationProvider().authenticate(authentication);
			}
		};
		return authenticationManager;
	}

	@Bean
	public AuthenticationProvider daoAuhthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 加密方式
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

}
