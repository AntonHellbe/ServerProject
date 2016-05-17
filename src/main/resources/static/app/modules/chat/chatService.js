(function() {
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
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Chat.$inject = ['$q','$timeout'];

		function Chat ($q, $timeout) {
			var service = {}, listener = $q.defer(), socket = {
				client: null,
				stomp: null
			}, messageIds = [];

			service.RECONNECT_TIMEOUT = 30000;
			service.SOCKET_URL = "/wschat";
			service.CHAT_TOPIC = "/ws/wschatanswer";
			service.CHAT_BROKER = "/ws/wschat";

			service.receive = function () {
				return listener.promise;
			};

			service.send = function (message) {
				if (socket.stomp != null) {
					console.log("sending1 " + JSON.stringify(message));
					var str = JSON.stringify(message);

					socket.stomp.send(service.CHAT_BROKER, {}, str);
				}


				//socket.stomp.send(service.CHAT_BROKER, {
				//	priority: 9
				//},message);
			};

			var reconnect = function () {
				$timeout(function () {
					initialize();
				}, this.RECONNECT_TIMEOUT);
			};

			var getMessage = function (data) {
				var message = JSON.parse(data), out = {};
				return message;
			};

			var startListener = function () {
				socket.stomp.subscribe(service.CHAT_TOPIC, function (data) {
					listener.notify(getMessage(data.body));
				});
			};


			//var initialize = function () {
			//	socket.client = new SockJS(service.SOCKET_URL);
			//	socket.stomp = Stomp.over(socket.client);
			//	socket.stomp.connect({}, startListener);
			//	socket.stomp.onclose = reconnect;
			//};


			service.connect = function () {
				socket.client = new SockJS(service.SOCKET_URL);
				socket.stomp = Stomp.over(socket.client);
				socket.stomp.connect({}, startListener);
				socket.stomp.onclose = reconnect;
				console.log("connected");
			};

			service.disconnect = function () {
				if (socket.stomp != null) {
					socket.stomp.unsubscribe();
					socket.stomp.disconnect();

				}
				//setConnected(false);
				console.log("Disconnected");
			};

			return service;
		}

})();
