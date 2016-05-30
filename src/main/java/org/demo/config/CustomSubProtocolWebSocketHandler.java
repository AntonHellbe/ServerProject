package org.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

/**
 * Created by Sebastian Börebäck on 2016-05-13.
 */
public class CustomSubProtocolWebSocketHandler extends SubProtocolWebSocketHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomSubProtocolWebSocketHandler.class);

//	@Autowired
//	private SessionHandler sessionHandler;

	public CustomSubProtocolWebSocketHandler(MessageChannel clientInboundChannel, SubscribableChannel clientOutboundChannel) {
		super(clientInboundChannel, clientOutboundChannel);
	}


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("New websocket connection was established");
		super.afterConnectionEstablished(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		LOGGER.info("Websocket DISCONNECTED");
		super.afterConnectionClosed(session, closeStatus);
	}
}