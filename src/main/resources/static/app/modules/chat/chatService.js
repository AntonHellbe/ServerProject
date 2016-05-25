(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:chatService
	 * @description
	 * # chatService
	 * Service of the app
	 */

	angular
		.module('chat')
		.factory('ChatService', Chat);

	Chat.$inject = ['$q', '$timeout'];

	function Chat($q, $timeout) {
		var service = {}, listener = $q.defer(), socket = {
			client: null,
			stomp: null
		}, messageIds = [];

		service.RECONNECT_TIMEOUT = 30000;
		service.SOCKET_URL = "/wschat";
		service.SERVICE_TOPIC = "/ws/wschatanswer";
		service.SERVICE_BROKER = "/ws/wschat";

		service.receive = function () {
			return listener.promise;
		};

		/**
		 * Send chat message
		 * @param message
		 */
		service.send = function (message) {
			if (socket.stomp != null) {
				//console.log("sending1 " + JSON.stringify(message));
				var str = JSON.stringify(message);
				socket.stomp.send(service.SERVICE_BROKER, {}, str);
			}

		};

		/**
		 * Reconnect on lost connection to websocket server
		 */
		var reconnect = function () {
			$timeout(function () {
				initialize();
			}, this.RECONNECT_TIMEOUT);
		};

		/**
		 * Handle messegas got from websocket
		 * @param data
		 */
		var getMessage = function (data) {
			var message = JSON.parse(data), out = {};
			return message;
		};

		/**
		 * Listen for websocket messages
		 */
		var startListener = function () {
			socket.stomp.subscribe(service.SERVICE_TOPIC, function (data) {
				listener.notify(getMessage(data.body));
			});
		};

		/**
		 * Connect to websocket
		 */
		service.connect = function () {
			socket.client = new SockJS(service.SOCKET_URL);
			socket.stomp = Stomp.over(socket.client);
			socket.stomp.connect({}, startListener);
			socket.stomp.onclose = reconnect;
			//console.log("connected");
		};

		/**
		 * disconnect from websocket
		 */
		service.disconnect = function () {
			if (socket.stomp != null) {
				socket.stomp.unsubscribe();
				socket.stomp.disconnect();

			}
			//setConnected(false);
			//console.log("Disconnected");
		};

		return service;
	}

})();
