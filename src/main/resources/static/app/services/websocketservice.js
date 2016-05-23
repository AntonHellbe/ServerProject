/**
 * Created by sebadmin on 2016-05-13.
 */

(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:timestampsService
	 * @description
	 * # timestampsService
	 * Service of the app
	 */
		//angular.module("essence").service("ChatService", function ($q, $timeout) {
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
		service.CHAT_TOPIC = "/ws/wsanswer";
		service.CHAT_BROKER = "/ws/wsservice";

		//service.SOCKET_URL = "/serviceupdate";
		//service.CHAT_TOPIC = "/topic/greetings";
		//service.CHAT_BROKER = "/ws/serviceupdate";

		service.receive = function () {
			return listener.promise;
		};

		service.send = function (message) {
			if (socket.stomp != null) {
				console.log("sending1 " + JSON.stringify(message));
				var test = JSON.stringify({'name': message.name});
				var str = JSON.stringify(message);
				console.log("sending2 " + test);
				//var name = document.getElementById('name').value;
				//socket.stomp.send(service.CHAT_BROKER, {}, JSON.stringify({ 'name': message.name }));

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

			//[{"token":null,"content":null,"area":"ACCOUNT","crudType":"ALL","affectedId":null

			//If a all request is called
			if (message.crudType == 'ALL') {
				handleAllAnswer(message);
			}

			$mdToast.show(
				$mdToast.simple()
					.content("Area: " + message.area + " - Change: " + message.crudType)
					.position('top right')
					.hideDelay(2000)
					.parent("#parent")
			);

			return message;
		};

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
		}

		var startListener = function () {
			console.log("Got connection");
			socket.stomp.subscribe(service.CHAT_TOPIC, function (data) {
				listener.notify(getMessage(data.body));
			});
		};


		var initialize = function () {
			socket.client = new SockJS(service.SOCKET_URL);
			socket.stomp = Stomp.over(socket.client);
			socket.stomp.connect({}, startListener);
			//socket.stomp.onclose = reconnect;
		};


		service.connect = function () {
			socket.client = new SockJS(service.SOCKET_URL);
			socket.stomp = Stomp.over(socket.client);
			socket.stomp.onclose = reconnect;
			socket.stomp.connect({}, startListener);

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

		service.getSchedules = function() {
			return schedules;
		};

		service.getTimestamps = function() {
			return timeStamps;
		};

		service.getUsers = function() {
			return users;
		};



		//initialize();
		return service;
	}

})();