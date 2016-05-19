(function () {
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

	Schedule.$inject = ['WebsocketService', '$log', '$rootScope', 'moment', 'alert'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Schedule(WebsocketService, $log, $rootScope, moment, alert) {
		/*jshint validthis: true */
		var vm = this;


		//These variables MUST be set as a minimum for the calendar to work
		vm.calendarView = 'week';
		vm.viewDate = new Date();


		vm.isCellOpen = true;

		vm.eventClicked = function (event) {
			alert.show('Clicked', event);
		};

		vm.eventEdited = function (event) {
			alert.show('Edited', event);
		};

		vm.eventDeleted = function (event) {
			alert.show('Deleted', event);
		};

		vm.eventTimesChanged = function (event) {
			alert.show('Dropped or resized', event);
		};

		vm.toggle = function ($event, field, event) {
			$event.preventDefault();
			$event.stopPropagation();
			event[field] = !event[field];
		};

		//view-date="calendarDate"
		//events="events"
		//view-title="calendarTitle"
		//on-event-click="eventClicked(calendarEvent)"
		//on-event-times-changed="calendarEvent.startsAt = calendarNewEventStart; calendarEvent.endsAt = calendarNewEventEnd"
		//edit-event-html="'<i class=\'glyphicon glyphicon-pencil\'></i>'"
		//delete-event-html="'<i class=\'glyphicon glyphicon-remove\'></i>'"
		//on-edit-event-click="eventEdited(calendarEvent)"
		//on-delete-event-click="eventDeleted(calendarEvent)"
		//cell-is-open="true">


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
								vm.schedules = vm.schedules ? wsUpdate.payloadList : [];
							}

							if (vm.schedules.length > 0) {
								$log.info("there are scheds from server");
								vm.events.length = 0;
								vm.events = [];
								angular.forEach(vm.schedules, function (shed, index) {
									//alert(value.name);
									vm.events.push(
										{
											title: shed.userId.toString(),
											type: 'info',
											startsAt: new Date(shed.from),
											endsAt: new Date(shed.to),
											draggable: true,
											resizable: true
										}
									);
								});
							}

							//{
							//	title: 'An event',
							//		type: 'info',
							//	startsAt: moment().startOf('week').subtract(2, 'days').add(8, 'hours').toDate(),
							//	endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
							//	draggable: true,
							//	resizable: true
							//}
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

		vm.getAll = function (isManual) {
			$log.info("Get all man:" + isManual);

			if (isManual) {
				var allstr = {"area": "SCHEDULE", "crudType": "ALL", "token": $rootScope.authToken};
				WebsocketService.send(allstr);

			}
			else {
				var schedules = WebsocketService.getSchedules();
				if (schedules.length == 0) {
					$log.info("get sched from server");
					var allstr = {"area": "SCHEDULE", "crudType": "ALL", "token": $rootScope.authToken};
					WebsocketService.send(allstr);
				}
				else {
					$log.info("I have all scheds!");
					vm.schedules = schedules;
					if (vm.events == undefined) {
						$log.info("NO events!");
						vm.events = [];

						angular.forEach(vm.schedules, function (shed, index) {
							//alert(value.name);
							vm.events.push(
								{
									title: shed.userId.toString(),
									type: 'info',
									startsAt: new Date(shed.from),
									endsAt: new Date(shed.to),
									draggable: true,
									resizable: true
								}
							);
						});
					}
					else {
						if (vm.events.length == 0) {
							//vm.events.length = 0;
							vm.events = [];
							angular.forEach(vm.schedules, function (shed, index) {
								//alert(value.name);
								vm.events.push(
									{
										title: shed.userId.toString(),
										type: 'info',
										startsAt: new Date(shed.from),
										endsAt: new Date(shed.to),
										draggable: true,
										resizable: true
									}
								);
							});
						}
						else {
							$log.info("I still have events!");
						}
					}
				}
			}
		};


		//get all..
		vm.schedules = vm.getAll(false);

		moment.locale('en_gb', {
			week: {
				dow: 1 // Monday is the first day of the week
			}
		});


	}

})();
