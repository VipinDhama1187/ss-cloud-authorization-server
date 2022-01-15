package com.vipin.authorization.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("client1")
			.secret("secret1")
			.scopes("read")
			.authorizedGrantTypes("password")
		.and()
			.withClient("client2")
			.secret("secret2")
			.scopes("read", "write")
			.authorizedGrantTypes("authorization_code")
			/**
			 * redirection URL is responsible to send authorization code on this url
			 * http://localhost:8081
			 * http://localhost:8080/oauth/authorize?response_type=code&client_id=client2&scope=read
			 * response_type=code means get the code in redirect URL for client_id = client2
			 * the above url will redirect you to Spring security loginform
			 * where user will end username and password after submit we will get approve or reject screen
			 * after approval you will get below redirection url
			 * http://localhost:8081/?code=BFJsfm
			 * 
			 * access 
 				http://localhost:8080/oauth/token?grant_type=authorization_code&code=<code from the previous output>&scope=read

				use basic auth for client for which we got the code and get oauth token
			 */
			.redirectUris("http://localhost:8081");

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

}
