package org.demo.controller.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.model.security.Account;
import org.demo.model.ws.WsAnswer;
import org.demo.model.ws.WsMessage;
import org.demo.service.AccountService;
import org.demo.service.ScheduleService;
import org.demo.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Sebastian Börebäck on 2016-03-30.
 */
@Controller
public class WsController {

	private static final Logger log = LoggerFactory.getLogger(WsController.class);

	@Autowired
	AccountService userService;

	@Autowired
	TimeService timeService;

	@Autowired
	ScheduleService scheduleService;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/wsservice")
	@SendTo("/ws/wsanswer")
	public WsAnswer webHandleMessage(WsMessage message) throws InterruptedException {

		log.info("got message: " + message);

		WsAnswer answer = new WsAnswer(message);

		if (message.getArea() != null) {

			switch (message.getArea()) {

				case ACCOUNT:
					answer = doAccountAction(userService, message, answer);
					break;
				case TIMESTAMP:
					answer = doTimeAction(timeService, message, answer);
					break;
				case SCHEDULE:
					answer = doScheduleAction(scheduleService, message, answer);
					break;
				default:
					answer = new WsAnswer("Hello, " + message.getName() + "!");
			}
		} else {
			answer = new WsAnswer("Hello, " + message.getName() + "!");
		}

		log.info("returing " + answer);

		//log.info("Got Ws message " + message.toString());
//		return new WsAnswer("Hello, " + message.getName() + "!");
		return answer;
	}

	private WsAnswer doScheduleAction(ScheduleService service, WsMessage message, WsAnswer answer) {
		return null;
	}

	private WsAnswer doTimeAction(TimeService service, WsMessage message, WsAnswer answer) {
		return null;
	}

	private WsAnswer doAccountAction(AccountService service, WsMessage message, WsAnswer answer) {
		if (message.getCrudType() != null) {


			switch (message.getCrudType()) {

				case ADD:
					Account addAccount = null;
					addAccount = mapper.convertValue(message.getPayload(), Account.class);
					if (addAccount != null) {
						log.info("adding account " + addAccount );
						ResponseEntity<Account> serviceUser = service.addUser(addAccount);

						answer.setPayload(serviceUser.getBody());
					} else {
						// TODO: 2016-05-17 :21:02 Fix error management
						log.info("Failed to save " + message);
					}

					break;
				case UPDATE:

					Account updatedAccount = null;
					updatedAccount = mapper.convertValue(message.getPayload(), Account.class);

					if (updatedAccount != null) {
						log.info("saving updated account " + updatedAccount);
						ResponseEntity<Account> serviceUser = service.updateUser(updatedAccount);

						answer.setPayload(serviceUser.getBody());
					} else {
						// TODO: 2016-05-17 :21:02 Fix error management
						log.info("Failed to save " + message);
					}

					break;
				case DELETE:
					log.info("delete user " + message.getAffectedId());
					ResponseEntity<Account> serviceUser = service.removeUser(message.getAffectedId());
					break;
				case GET:
					break;
				case ALL:
					ResponseEntity<List<Account>> list = service.getAllUser();
					answer.setPayloadList(list.getBody().toArray());
					break;
			}
		}
		return answer;
	}

	public void serverInformClients(WsAnswer update) {
		this.template.convertAndSend("/ws/wsanswer", update);
	}

}
