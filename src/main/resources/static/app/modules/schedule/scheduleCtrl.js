(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:scheduleCtrl
	* @description
	* # scheduleCtrl
	* Controller of the app
	*/

  	angular
		.module('schedule')
		.controller('ScheduleCtrl', Schedule);

		Schedule.$inject = ['WebsocketService','$log','$rootScope'];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Schedule(WebsocketService,$log,$rootScope) {
			/*jshint validthis: true */
			var vm = this;



			WebsocketService.receive().then(null, null, function (wsUpdate) {

				if (wsUpdate.error != undefined) {
					$log.info("Error " + wsUpdate.error);
					return;
				}
				else {
					$log.info("NO Error " + wsUpdate.error);
				}
				//$log.info("got update from server: " + JSON.stringify(wsUpdate));

				//Check that is account answer
				if (wsUpdate.area == "SCHEDULE") {
					if (wsUpdate.crudType != undefined) {
						switch (wsUpdate.crudType) {
							case "ADD":
								$log.info("ws Add user " + JSON.stringify(wsUpdate.payload));
								vm.schedules.push(wsUpdate.payload);
								break;
							case "DELETE":
								$log.info("got delete user id: " + wsUpdate.affectedId);
								var found = $filter('filter')(vm.schedules, {id: wsUpdate.affectedId})[0];
								vm.schedules.splice(vm.schedules.indexOf(found), 1);
								break;
							case "UPDATE":

								$log.info("Got update from ws");
								$log.info("updated user " + wsUpdate.payload.firstName);
								var found = $filter('filter')(vm.schedules, {id: wsUpdate.payload.id})[0];
								if (found != undefined) {
									$log.info("found user " + found);
									var idx = vm.schedules.indexOf(found);
									vm.schedules.splice(idx, 1);
									vm.schedules.splice(idx, 0, wsUpdate.payload);
								}
								else {
									//TODO show error msg about update
									$log.info("didnt find user " + wsUpdate.affectedId);
								}
								break;

							case "ALL":
								$log.info("got All schedules");

								if (wsUpdate.token == $rootScope.authToken) {
									$log.info("I asked for it " + $rootScope.authToken);
									vm.schedules = wsUpdate.payloadList ? wsUpdate.payloadList : [];
								}
								else {
									$log.info("Someone else wanted get all");
									vm.schedules= vm.schedules ? wsUpdate.payloadList : [];
								}
								break;

							case "PASSWORD":
								$log.info("Got Changepassword from ws");
								//$log.info("updated user " + wsUpdate.payload.firstName);
								var found = $filter('filter')(vm.schedules, {id: wsUpdate.affectedId})[0];
								if (found != undefined) {
									$log.info("found user " + found);
									var idx = vm.schedules.indexOf(found);
									vm.schedules.splice(idx, 1);
									vm.schedules.splice(idx, 0, wsUpdate.payload);
								}
								else {
									//TODO show error msg about update
									$log.info("didnt find user " + wsUpdate.affectedId);
								}
								break

						}

					}
				}

			});

			vm.getAll = function(isManual) {
				$log.info("Get all man:" + isManual);

				if(isManual) {
					var allstr = {"area": "SCHEDULE", "crudType": "ALL", "token": $rootScope.authToken};
					WebsocketService.send(allstr);

				}
				else {
					var schedules = WebsocketService.getSchedules();
					if(schedules.length == 0) {
						var allstr = {"area": "SCHEDULE", "crudType": "ALL", "token": $rootScope.authToken};
						WebsocketService.send(allstr);
					}
					else {
						vm.schedules= schedules;
					}
				}
			};


			//get all..
			vm.schedules = vm.getAll();
		}

})();
