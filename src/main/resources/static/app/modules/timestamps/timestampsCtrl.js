(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.controller:timestampsCtrl
	 * @description
	 * # timestampsCtrl
	 * Controller of the app
	 */

	angular
		.module('timestamps')
		.controller('TimestampsCtrl', Timestamps);

	Timestamps.$inject = ['$mdDialog', '$log', 'WebsocketService', '$rootScope', '$filter', '$http'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Timestamps($mdDialog, $log, WebsocketService, $rootScope, $filter, $http) {
		/*jshint validthis: true */
		var vm = this;


		/**
		 * Error handler
		 * @param message error message
		 */
		vm.showAlert = function (message) {
			$mdDialog.show(
				$mdDialog.alert()
					.clickOutsideToClose(true)
					.title('Error with stamps!')
					.textContent(message)
					.ariaLabel('Error with stamps')
					.ok('OK')
			);
		};

		/**
		 * Handles websocket messages
		 */
		WebsocketService.receive().then(null, null, function (wsUpdate) {

			/**
			 * Show error if there was problem with websockets
			 */
			if (wsUpdate.error != undefined) {
				//$log.info("Error " + wsUpdate.error);
				vm.showAlert(wsUpdate.error);
				return;
			}

			if (wsUpdate.area == "ACCOUNT") {
				if (wsUpdate.crudType != undefined) {
					if (wsUpdate.crudType == "ALL") {

						if (wsUpdate.token == $rootScope.authToken) {
							//$log.info("I asked for it " + $rootScope.authToken);
							vm.users = wsUpdate.payloadList ? wsUpdate.payloadList : [];
						}
						else {
							//$log.info("Someone else wanted get all");
							vm.users = vm.users ? wsUpdate.payloadList : [];

						}
					}
				}
			}

			//Check that is account answer
			if (wsUpdate.area == "TIMESTAMP") {
				if (wsUpdate.crudType != undefined) {
					switch (wsUpdate.crudType) {
						case "ADD":
							//$log.info("ws Add timestamp " + JSON.stringify(wsUpdate.payload));
							//$log.info("updated timestamps for  " + wsUpdate.affectedId);
							vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
							if (vm.selectedUser == undefined) {
								//$log.info("didnt find user " + wsUpdate.affectedId);
							}
							else {
								//$log.info("found " + vm.selectedUser.firstName);
								//$log.info("payload " + JSON.stringify(wsUpdate.payload));
								if(vm.selectedUser.stamps != undefined) {
									//$log.info("has stamps");
									if(vm.selectedUser.stamps.length <=0) {
										//$log.info("stamps are 0 or less");
										vm.selectedUser.stamps= [];
									}
									vm.selectedUser.stamps.push(wsUpdate.payload);
								}
								else {
									//$log.info("has no stamps");
									vm.selectedUser.stamps = [];
									vm.selectedUser.stamps.push(wsUpdate.payload);
								}
							}

							break;
						case "DELETE":
							//$log.info("got delete timestamp id: " + wsUpdate.affectedId);
							vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
							var found = $filter('filter')(vm.selectedUser.stamps, {id: wsUpdate.payload.id})[0];
							vm.selectedUser.stamps.splice(vm.selectedUser.stamps.indexOf(found), 1);
							break;
						case "UPDATE":

							//$log.info("Got update from ws");
							//$log.info("updated timestamps for  " + wsUpdate.affectedId);
							vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
							var found = $filter('filter')(vm.selectedUser.stamps, {id: wsUpdate.payload.id})[0];
							if (found != undefined) {
								//$log.info("found user " + found);
								var idx = vm.selectedUser.stamps.indexOf(found);
								vm.selectedUser.stamps.splice(idx, 1);
								vm.selectedUser.stamps.splice(idx, 0, wsUpdate.payload);
							}
							else {
								// TODO show error message
								//$log.info("didnt find user " + wsUpdate.affectedId);
							}
							break;

						case "ALL":
							//$log.info("got All users STAmps");

							if (wsUpdate.token == $rootScope.authToken) {
								//$log.info("I asked for it " + $rootScope.authToken);
								if (vm.selectedUser == undefined) {
									vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
								}
								vm.selectedUser.stamps = wsUpdate.payloadList ? wsUpdate.payloadList : [];
							}
							else {
								//$log.info("Someone else wanted get all");
								vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
								//$log.info("selected User " + JSON.stringify(vm.selectedUser));
								vm.selectedUser.stamps = wsUpdate.payloadList;
							}
							break;

					}

				}
			}
			//vm.selectedUser = null;

		});


		/**
		 * default get all users
		 */
		vm.getAll = function () {

			//$log.info("get all users");

			var allstr = {"area": "ACCOUNT", "crudType": "ALL", "token": $rootScope.authToken};
			WebsocketService.send(allstr);
			//AccountsService.query().$promise.then(
			//	function (success) {
			//		console.log("Success");
			//		vm.users = success ? success : [];
			//		//todo get all users stamps
			//		//vm.users.forEach(vm.getStamps)
			//
			//
			//	},
			//	function (error) {
			//		alert("Failed " + JSON.stringify(error));
			//	},
			//	function (update) {
			//		alert("Got notification" + JSON.stringify(update));
			//	}
			//);
		};




		vm.getStamps = function (user, shouldUpdate) {

			vm.selectedUser = user;

			if (user.showBody == undefined) {
				user.showBody = false;
			}
			if (user.stamps == undefined || shouldUpdate == true) {

				if (user.rfidKey != null) {
					//set active user
					vm.selectedUser = user;
					var allstr = {"area": "TIMESTAMP", "crudType": "ALL", "token": $rootScope.authToken};
					allstr.affectedId = user.id;

					WebsocketService.send(allstr);
					//TimestampsService.get({id: user.id}).$promise.then(
					//	function (success) {
					//		console.log("Success getting times");
					//		//stamplist.push(success);
					//		user.stamps = success;
					//	},
					//	function (error) {
					//		alert("Failed " + JSON.stringify(error));
					//	}
					//);
				}
				if (shouldUpdate == true)
					return user.showBody;
			}
			return !user.showBody;
		};

		vm.doPrimaryAction = function (ev, user, stamp) {
			//console.log("calling event");

			$mdDialog.show({
					controller: DialogController,
					templateUrl: '/app/modules/timestamps/dialog1.tmpl.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose: true,
					locals: {user: user, stamp: stamp}
				})
				.then(function (updateStamp) {
						//set active user
						vm.selectedUser = user;
						//console.log("answeard " + updateStamp);
						if (updateStamp == undefined) {
							//console.log("del " + stamp.id);


							var sendMsg = {"area": "TIMESTAMP", "crudType": "DELETE", "token": $rootScope.authToken};
							sendMsg.affectedId = user.id;
							sendMsg.payload = stamp;

							//$log.info("sending > " + JSON.stringify(sendMsg));
							WebsocketService.send(sendMsg);

							////del
							//TimestampsService.remove({id: user.id, stampId: stamp.id}).$promise.then(
							//	function (success) {
							//		console.log("Success delete");
							//
							//	},
							//	function (error) {
							//		alert("Failed " + JSON.stringify(error));
							//	}
							//);
						}
						else {
							//update
							//console.log("updated " + updateStamp.date);
							var sendMsg = {"area": "TIMESTAMP", "crudType": "UPDATE", "token": $rootScope.authToken};
							sendMsg.payload = updateStamp;
							sendMsg.affectedId = user.id;

							//$log.info("sending > " + JSON.stringify(sendMsg));
							WebsocketService.send(sendMsg);
							//TimestampsService.update({id: user.id, stampId: stamp.id}, updateStamp).$promise.then(
							//	function (success) {
							//		console.log("Success getting times");
							//		$log.info("success " + JSON.stringify(success));
							//		//stamp = success;
							//		var idx = user.stamps.indexOf(stamp);
							//		user.stamps[idx] = success;
							//	},
							//	function (error) {
							//		alert("Failed " + JSON.stringify(error));
							//	}
							//);
						}
					}, function () {
						vm.status = 'You cancelled the dialog.';
						//console.log("You cancelled the dialog.");
					}
				);
		};

		/**
		 * Add a new timestamp
		 * @param ev
		 * @param user
		 */
		vm.addStamp = function (ev, user) {
			//set active user
			vm.selectedUser = user;

			$mdDialog.show({
					controller: AddTimeController,
					templateUrl: '/app/modules/timestamps/addTime.tmpl.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose: true,
					locals: {user: user}
				})
				.then(function (newStamp) {
						//console.log("ADDSTAMP IN CTRL " + JSON.stringify(newStamp));
						vm.status = 'You said the information was "' + newStamp + '".';
						//ADD
						//console.log("ADDSTAMP " + newStamp.date);

						var tempDate = newStamp.date;
						newStamp.date = tempDate.getTime();


						var sendMsg = {"area": "TIMESTAMP", "crudType": "ADD", "token": $rootScope.authToken};
						sendMsg.payload = newStamp;
						sendMsg.affectedId = user.id;

						//$log.info("sending > " + JSON.stringify(sendMsg));
						WebsocketService.send(sendMsg);

						//TimestampsService.save({id: user.id, stampId: true}, newStamp).$promise.then(
						//	function (success) {
						//		$log.info("Success Adding time");
						//		vm.status = "ADD" + JSON.stringify(success);
						//		user.stamps.push(success);
						//	},
						//	function (error) {
						//		alert("Failed " + JSON.stringify(error));
						//	}
						//);
					}, function () {
						//console.log("You cancelled the dialog.");
					}
				);
		};

		//get all users
		vm.getAll();
		//end of timestamp Controlelr
	}


	/**
	 * Handles add timestamp dialog
	 * @param $scope
	 * @param $mdDialog
	 * @param user
	 * @param $log
	 * @constructor
	 */
	function AddTimeController($scope, $mdDialog, user, $log) {
		$scope.user = user;
		$scope.newStamp = {};
		$scope.newStamp.date = new Date();
		$scope.newStamp.checkIn = false;
		$scope.newStamp.rfidkey = $scope.user.rfidKey;
		$scope.dpDate = new Date();

		/**
		 * Cancel creating stamp
		 */
		$scope.cancel = function () {
			$mdDialog.cancel();
		};

		/**
		 * add new stamp
		 * @param newStamp
		 */
		$scope.addStamp = function (newStamp) {
			//$log.info("ADDSTAMP called with " + JSON.stringify($scope.newStamp));
			$mdDialog.hide($scope.newStamp);
		};

		/**
		 * onChange handles update time and date
		 * @param datep new Date
		 */
		$scope.updateDate = function (datep) {
			var dp = $scope.dpDate;
			var current = $scope.newStamp.date;
			current.setMonth(dp.getMonth());
			current.setDate(dp.getDate());
		};

		//end of
	}

	/**
	 * Handles update dialog for timestamp
	 * @param $scope
	 * @param $mdDialog
	 * @param user
	 * @param stamp
	 * @param $log
	 * @constructor
	 */
	function DialogController($scope, $mdDialog, user, stamp, $log) {
		$scope.user = user;
		$scope.stamp = angular.copy(stamp);
		$scope.userDate = new Date(stamp.date);
		$scope.userTime = new Date(stamp.date);

		/**
		 * onChange of the date/time part. since they are 2 diffrent element
		 * @param type type of update time or Date
		 */
		$scope.updateDate = function (type) {
			if (type == 'time') {
				if ($scope.userTime != undefined) {
					//$log.info("current date " + $scope.stamp.date);
					var tempDate = new Date($scope.stamp.date);

					$scope.userTime.setUTCFullYear(tempDate.getUTCFullYear());
					$scope.userTime.setUTCMonth(tempDate.getUTCMonth());
					$scope.userTime.setUTCDate(tempDate.getUTCDate());

					$scope.stamp.date = $scope.userTime;
				}
			}
			if (type == 'date') {
				if ($scope.userDate != undefined) {
					var tempDate = new Date($scope.stamp.date);
					$scope.userDate.setHours(tempDate.getHours());
					$scope.userDate.setMinutes(tempDate.getMinutes());
					$scope.userDate.setSeconds(tempDate.getSeconds());
					$scope.userDate.setMilliseconds(tempDate.getMilliseconds());
					$scope.stamp.date = $scope.userDate;
				}
			}
		};

		/**
		 * Hide the dialog
		 */
		$scope.hide = function () {
			$mdDialog.hide();
		};
		/**
		 * On cancel button click
		 */
		$scope.cancel = function () {
			$mdDialog.cancel();
		};

		/**
		 * On update timestamp click, send to TimestampController the updated timestamp
		 * @param updateStamp the updated timestamp
		 */
		$scope.answer = function (updateStamp) {
			if (updateStamp != undefined) {
				var temp = new Date(updateStamp.date);
				updateStamp.date = temp.getTime();
				//console.log("answer called with " + JSON.stringify(updateStamp));
				stamp = updateStamp;
			}
			$mdDialog.hide(updateStamp);
		};
	}

})();
