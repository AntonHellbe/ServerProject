(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:chatCtrl
	* @description
	* # chatCtrl
	* Controller of the app
	*/

  	angular
		.module('chat')
		.controller('ChatCtrl', Chat);

		Chat.$inject = ['ChatService','$rootScope','$log'];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Chat(ChatService,$rootScope,$log) {
			/*jshint validthis: true */
			var vm = this;
			vm.messages = [];

			vm.currentUser = $rootScope.authData.principal.username;

			ChatService.connect();

			vm.sendMsg= function(){
				//vm.message;
				var msg={};
				//msg.stamp= new Date();
				msg.sender = vm.currentUser;
				msg.message = vm.message;

				//var sendMsg = {"sender": "ACCOUNT", "crudType": "DELETE", "token": $rootScope.authToken};
				//sendMsg.affectedId = user.id;


				$log.info("sending > " + JSON.stringify(msg));
				ChatService.send(msg);

				//clear field
				vm.message="";

				//vm.messages.push(msg);

			};

			ChatService.receive().then(null, null, function (chatAnswer) {
				$log.info("got answer " + chatAnswer);
				vm.messages.push(chatAnswer);
			});

		}



})();
