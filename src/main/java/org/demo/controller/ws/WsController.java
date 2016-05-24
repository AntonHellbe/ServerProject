package org.demo.controller.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.model.ScheduleStamp;
import org.demo.model.TimeStamp;
import org.demo.model.security.Account;
import org.demo.model.ws.ChatAnswer;
import org.demo.model.ws.ChatMessage;
import org.demo.model.ws.WsAnswer;
import org.demo.model.ws.WsMessage;
import org.demo.repository.AccountRepository;
import org.demo.service.AccountService;
import org.demo.service.ScheduleService;
import org.demo.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Börebäck on 2016-03-30.
 */
@CrossOrigin(origins = "*")
@Controller
public class WsController {

	private static final Logger log = LoggerFactory.getLogger(WsController.class);

	@Autowired
	AccountService userService;

	@Autowired
	TimeService timeService;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	ScheduleService scheduleService;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/wschat")
	@SendTo("/ws/wschatanswer")
	public ChatAnswer webChatMessage(ChatMessage message) throws InterruptedException {

		log.info("got chat message "+message);
		return new ChatAnswer(message);
	}


	@MessageMapping("/wsservice")
	@SendTo("/ws/wsanswer")
	public WsAnswer webHandleMessage(WsMessage message) throws InterruptedException {

		log.info("got message: " + message);

		WsAnswer answer = new WsAnswer(message);

		if (message.getArea() != null) {

			switch (message.getArea()) {

				case ACCOUNT:
					answer = doAccountAction(userService, message, answer);
					log.info("account answer "+answer.toString());
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
		if (message.getCrudType() != null) {
			switch (message.getCrudType()) {

				case ADD:
					break;
				case UPDATE:
					break;
				case DELETE:
					break;
				case GET:
					break;
				case ALL:
					ResponseEntity<List<ScheduleStamp>> list = this.scheduleService.getAll();
					answer.setPayloadList(list.getBody().toArray());
					break;
			}
		} else {
			answer.setError("Missing CrudType!");
		}
		return answer;
	}

	private WsAnswer doTimeAction(TimeService service, WsMessage message, WsAnswer answer) {
		TimeStamp stamp;
		if (message.getCrudType() != null) {
			switch (message.getCrudType()) {

				case ADD:
					log.info("Add timestamp for " + message.getAffectedId());
					stamp = mapper.convertValue(message.getPayload(), TimeStamp.class);
					ResponseEntity<TimeStamp> addedStamp = service.addTime(message.getAffectedId(), stamp);
					answer.setPayload(addedStamp.getBody());
					break;
				case UPDATE:
					log.info("Update timestamp for " + message.getAffectedId());
					stamp = mapper.convertValue(message.getPayload(), TimeStamp.class);
					ResponseEntity<TimeStamp> updateStamp = service.updateTime(message.getAffectedId(), stamp.getId(), stamp);
					answer.setPayload(updateStamp.getBody());

					break;
				case DELETE:
					log.info("delete timestamp for " + message.getAffectedId());
					stamp = mapper.convertValue(message.getPayload(), TimeStamp.class);
					ResponseEntity<TimeStamp> deletedStamp = service.deleteTime(message.getAffectedId(), stamp.getId());
					answer.setPayload(deletedStamp.getBody());

					break;
				case GET:
					break;
				case ALL:
					ResponseEntity<ArrayList<TimeStamp>> list = this.timeService.getAll(message.getAffectedId());
					if (list == null) {
						answer.setError("List is empty!");
						return answer;
					}
					answer.setPayloadList(list.getBody().toArray());
					break;

			}
		} else {
			answer.setError("Missing CrudType!");
		}
		return answer;
	}

	private WsAnswer doAccountAction(AccountService service, WsMessage message, WsAnswer answer) {
		if (message.getCrudType() != null) {


			switch (message.getCrudType()) {

				case ADD:
					Account addAccount = null;
					addAccount = mapper.convertValue(message.getPayload(), Account.class);
					if (addAccount != null) {
						log.info("adding account " + addAccount);
						ResponseEntity<Account> serviceUser = service.addUser(addAccount);
						log.info(serviceUser.getStatusCode().toString());
						if (!serviceUser.getStatusCode().equals(HttpStatus.OK)) {
							log.info("in the wrong spot bro");
							answer.setError("Status code: " + serviceUser.getStatusCode()
									+ "no username, firstname and lastname!");
							log.info("from add "+answer.toString());
							return answer;
						} else {
							log.info("Det gick ju bra Sture!");
						}
						answer.setPayload(serviceUser.getBody());
					} else {
						// TODO: 2016-05-17 :21:02 Fix error management
						log.info("Failed to save " + message);
						answer.setError("Failed to save " + message);
					}
					break;
				case UPDATE:

					Account updatedAccount = mapper.convertValue(message.getPayload(), Account.class);

					if (updatedAccount != null) {
						log.info("saving updated account " + updatedAccount);
						ResponseEntity<Account> serviceUser = service.updateUser(updatedAccount);
						if (serviceUser.getStatusCode() == HttpStatus.OK) {
							answer.setPayload(serviceUser.getBody());
						} else {
							answer.setError("Couldnt update user "+serviceUser.getStatusCode());
						}


					} else {
						// TODO: 2016-05-17 :21:02 Fix error management
						log.info("Failed to save " + message);
					}

					break;
				case DELETE:
					log.info("delete user " + message.getAffectedId());
					service.removeUser(message.getAffectedId());
					break;
				case GET:
					break;
				case ALL:
					ResponseEntity<List<Account>> list = service.getAllUser();
					answer.setPayloadList(list.getBody().toArray());
					break;
				case PASSWORD:
					String newPassword = mapper.convertValue(message.getPayload(),String.class);
					log.info("new password " + newPassword);
					try {
						answer.setPayload(service.passwordUpdater(newPassword, message.getAffectedId()));
					} catch (Exception e) {
						answer.setError(e.getMessage());
					}
					break;
			}
		} else {
			answer.setError("Missing CrudType!");
		}
		return answer;
	}

	/**
	 * Send updates from server with out client.
	 * Can be used to inform about server restarts etc.
	 *
	 * @param update the update message.
	 */
	public void serverInformClients(WsAnswer update) {
		this.template.convertAndSend("/ws/wsanswer", update);
	}

}
