package org.demo.controller.ws;

import org.demo.model.ws.WsAnswer;
import org.demo.model.ws.WsMessage1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by Sebastian Börebäck on 2016-03-30.
 */
@Controller
public class WsController {

	private static final Logger log = LoggerFactory.getLogger(WsController.class);
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/wsservice")
	@SendTo("/ws/wsanswer")
	public WsAnswer webHandleMessage(WsMessage1 message) throws InterruptedException {
		log.info("Got Ws message " + message.toString());
		return new WsAnswer("Hello, " + message.getName() + "!");
	}

	public void serverInformClients(WsAnswer update) {
		this.template.convertAndSend("/ws/wsanswer", update);
	}

}
