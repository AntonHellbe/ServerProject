/**
 * Created by sebadmin on 2016-05-13.
 */

(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:timestampsService
	 * @description
	 * # websocketservice
	 * Service of the app, handles the websocket communication
	 */
	angular
		.module('essence')
		.factory('WebsocketService', WebsocketService);

	WebsocketService.$inject = ['$q', '$timeout', '$rootScope', '$mdToast'];

	function WebsocketService($q, $timeout, $rootScope, $mdToast) {

		var users = [];
		var timeStamps = [];
		var schedules = [];

		var service = {}, listener = $q.defer(), socket = {
			client: null,
			stomp: null
		}, messageIds = [];

		service.RECONNECT_TIMEOUT = 30000;
		service.SOCKET_URL = "/wsservice";
		service.SERVICE_TOPIC = "/ws/wsanswer";
		service.SERVICE_BROKER = "/ws/wsservice";

		/**
		 * when message is recived there is a async promise for it
		 * @returns {*}
		 */
		service.receive = function () {
			return listener.promise;
		};

		/**
		 * Send websocket message
		 * @param message
		 */
		service.send = function (message) {
			if (socket.stomp != null) {
				//console.log("sending data: " + JSON.stringify(message));
				var str = JSON.stringify(message);
				socket.stomp.send(service.SERVICE_BROKER, {}, str);
			}
		};

		/**
		 * Reconnect if lost connection
		 */
		var reconnect = function () {
			$timeout(function () {
				initialize();
			}, this.RECONNECT_TIMEOUT);
		};

		/**
		 * Handles reciviing message from server
		 * @param data the new data from server
		 */
		var getMessage = function (data) {
			var message = JSON.parse(data), out = {};

			//If a all request is called
			if (message.crudType == 'ALL') {
				handleAllAnswer(message);
			}

			//show toast for the new date from server
			//with area, and type of update
			$mdToast.show(
				$mdToast.simple()
					.content("Area: " + message.area + " - Change: " + message.crudType)
					.position('top right')
					.hideDelay(2000)
			);

			return message;
		};

		/**
		 * stores the updated data locally in this service
		 * so that you dont need to do extra roundtrips to server for data
		 * @param message the data
		 */
		var handleAllAnswer = function (message) {
			switch (message.area) {
				case "ACCOUNT":
					users = message.payloadList;
					break;
				case "TIMESTAMP":
					timeStamps = message.payloadList;
					break;
				case "SCHEDULE":
					schedules = message.payloadList;
					break;
			}
		};

		/**
		 * Start to listen for changes from server through websocket
		 */
		var startListener = function () {
			//console.log("Got connection");
			socket.stomp.subscribe(service.SERVICE_TOPIC, function (data) {
				listener.notify(getMessage(data.body));
			});
		};


		/**
		 * Initiliaze connection
		 */
		var initialize = function () {
			socket.client = new SockJS(service.SOCKET_URL);
			socket.stomp = Stomp.over(socket.client);
			socket.stomp.connect({}, startListener);
			//socket.stomp.onclose = reconnect;
		};


		/**
		 * Connect to server
		 */
		service.connect = function () {
			socket.client = new SockJS(service.SOCKET_URL);
			socket.stomp = Stomp.over(socket.client);
			socket.stomp.onclose = reconnect;
			socket.stomp.connect({}, startListener);

			//console.log("connected");

		};


		/**
		 * disconnect to server
		 */
		service.disconnect = function () {
			if (socket.stomp != null) {
				socket.stomp.unsubscribe();
				socket.stomp.disconnect();

			}
			//setConnected(false);
			//console.log("Disconnected");
		};

		/**
		 * Get locally stored schedules
		 * @returns {Array}
		 */
		service.getSchedules = function() {
			return schedules;
		};

		/**
		 * Get locally stored timestamps
		 * @returns {Array}
		 */
		service.getTimestamps = function() {
			return timeStamps;
		};

		/**
		 * get locally stored users
		 * @returns {Array}
		 */
		service.getUsers = function() {
			return users;
		};



		//initialize();
		return service;
	}

})();