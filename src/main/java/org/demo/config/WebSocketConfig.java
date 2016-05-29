package org.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;


/***
 * Websocket configurations. setting up the STOMPJS websocket configurations.
 * @author Sebastian Börebäck
 */
@Configuration
@EnableWebSocketMessageBroker
//public class WebSocketConfig extends WebSocketMessageBrokerConfigurationSupport implements WebSocketMessageBrokerConfigurer {
public class WebSocketConfig extends WebSocketMessageBrokerConfigurationSupport implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		super.configureWebSocketTransport(registry);
	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		return super.configureMessageConverters(messageConverters);
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		super.configureClientInboundChannel(registration);
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		super.configureClientOutboundChannel(registration);
	}


	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		super.addReturnValueHandlers(returnValueHandlers);
	}

	/**
	 * Registering the websocket endpoints used by STOMP
	 * @param registry
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("Register " + registry.toString());
		registry.addEndpoint("/wsservice").setAllowedOrigins("*").withSockJS();
		registry.addEndpoint("/wschat").setAllowedOrigins("*").withSockJS();
	}

	/**
	 * configuering the websocket endpoints, to always user /ws
	 * @param config
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		System.out.println("config " + config.toString());
		config.enableSimpleBroker("/ws");
		config.setApplicationDestinationPrefixes("/ws");

	}

	@Bean
	public WebSocketHandler subProtocolWebSocketHandler() {
		return new CustomSubProtocolWebSocketHandler(clientInboundChannel(), clientOutboundChannel());
	}
}

