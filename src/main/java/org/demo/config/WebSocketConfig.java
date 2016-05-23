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

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("Register " + registry.toString());
		registry.addEndpoint("/wsservice").setAllowedOrigins("*").withSockJS();
		registry.addEndpoint("/wschat").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		System.out.println("config " + config.toString());
		config.enableSimpleBroker("/ws");
		config.setApplicationDestinationPrefixes("/ws");

	}

//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry config) {
//		config.enableSimpleBroker("/topic");
//		config.setApplicationDestinationPrefixes("/app");
//	}
//
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/hello").withSockJS();
//	}

	@Bean
	public WebSocketHandler subProtocolWebSocketHandler() {
		return new CustomSubProtocolWebSocketHandler(clientInboundChannel(), clientOutboundChannel());
	}
}

//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//
///**
// * Created by Sebastian Börebäck on 2016-03-30.
// */
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
//
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		System.out.println("Register "+registry.toString());
//		registry.addEndpoint("/hello").withSockJS();
//	}
//
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry config) {
//		System.out.println("config "+config.toString());
//		config.enableSimpleBroker("/topic");
//		config.setApplicationDestinationPrefixes("/app");
//
//	}
//
//
//
//
//
//
//}
