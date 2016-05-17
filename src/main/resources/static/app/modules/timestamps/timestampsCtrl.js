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

	//Timestamps.$inject = [ 'TimestampsService', '$mdDialog', '$log', 'WebsocketService', '$rootScope', '$filter'];
	Timestamps.$inject = [ '$mdDialog', '$log', 'WebsocketService', '$rootScope', '$filter'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	//function Timestamps( TimestampsService, $mdDialog, $log, WebsocketService, $rootScope, $filter) {
	function Timestamps( $mdDialog, $log, WebsocketService, $rootScope, $filter) {
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
			if (wsUpdate.area == "ACCOUNT") {
				if (wsUpdate.crudType != undefined) {
					if (wsUpdate.crudType == "ALL") {

						if (wsUpdate.token == $rootScope.authToken) {
							$log.info("I asked for it " + $rootScope.authToken);
							vm.users = wsUpdate.payloadList ? wsUpdate.payloadList : [];
						}
						else {
							$log.info("Someone else wanted get all");
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
							$log.info("ws Add user " + JSON.stringify(wsUpdate.payload));
							vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
							vm.selectedUser.stamps.push(wsUpdate.payload);
							break;
						case "DELETE":
							$log.info("got delete user id: " + wsUpdate.affectedId);
							vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
							var found = $filter('filter')(vm.selectedUser.stamps, {id: wsUpdate.payload.id})[0];
							vm.selectedUser.stamps.splice(vm.selectedUser.stamps.indexOf(found), 1);
							break;
						case "UPDATE":

							$log.info("Got update from ws");
							$log.info("updated user " + wsUpdate.payload.firstName);
							vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
							var found = $filter('filter')(vm.selectedUser.stamps, {id: wsUpdate.payload.id})[0];
							if (found != undefined) {
								$log.info("found user " + found);
								var idx = vm.selectedUser.stamps.indexOf(found);
								vm.selectedUser.stamps.splice(idx, 1);
								vm.selectedUser.stamps.splice(idx, 0, wsUpdate.payload);
							}
							else {
								TODO
								show
								error
								msg
								about
								update
								//$log.info("didnt find user " + wsUpdate.affectedId);
							}
							break;

						case "ALL":
							$log.info("got All users STAmps");

							if (wsUpdate.token == $rootScope.authToken) {
								$log.info("I asked for it " + $rootScope.authToken);
								vm.selectedUser.stamps = wsUpdate.payloadList ? wsUpdate.payloadList : [];
							}
							else {
								$log.info("Someone else wanted get all");
								vm.selectedUser = $filter('filter')(vm.users, {id: wsUpdate.affectedId})[0];
								$log.info("selected User " + JSON.stringify(vm.selectedUser));
								vm.selectedUser.stamps =wsUpdate.payloadList;
							}
							break;

					}

				}
			}
			vm.selectedUser = null;

		});


		/**
		 * default get all users
		 */
		vm.getAll = function () {

			$log.info("get all users");

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

		//vm.users = [];
		vm.getAll();

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
			console.log("calling event");

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
							console.log("del " + stamp.id);


							var sendMsg = {"area": "TIMESTAMP", "crudType": "DELETE", "token": $rootScope.authToken};
							sendMsg.affectedId = user.id;
							sendMsg.payload = stamp;

							$log.info("sending > " + JSON.stringify(sendMsg));
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
							console.log("updated " + updateStamp.date);
							var sendMsg = {"area": "TIMESTAMP", "crudType": "UPDATE", "token": $rootScope.authToken};
							sendMsg.payload = updateStamp;
							sendMsg.affectedId = user.id;

							$log.info("sending > " + JSON.stringify(sendMsg));
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
						console.log("You cancelled the dialog.");
					}
				);
		};

		//vm.addNowStamp = function (user) {
		//	console.log("add NOW timestamp");
		//	TimestampsService.save({id: user.id}).$promise.then(
		//		function (success) {
		//			console.log("Success update time");
		//			vm.status = "update" + JSON.stringify(success);
		//			user.stamps.push(success);
		//		},
		//		function (error) {
		//			alert("Failed " + JSON.stringify(error));
		//		}
		//	);
		//};

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
						console.log("ADDSTAMP IN CTRL " + JSON.stringify(newStamp));
						vm.status = 'You said the information was "' + newStamp + '".';
						//ADD
						console.log("ADDSTAMP " + newStamp.date);

						var tempDate = newStamp.date;
						newStamp.date = tempDate.getTime();


						var sendMsg = {"area": "TIMESTAMP", "crudType": "ADD", "token": $rootScope.authToken};
						sendMsg.payload = newStamp;
						sendMsg.affectedId = user.id;

						$log.info("sending > " + JSON.stringify(sendMsg));
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
						vm.status = 'You cancelled the dialog.';
						console.log("You cancelled the dialog.");
					}
				);
		}
	}


	function AddTimeController($scope, $mdDialog, user, $log) {
		$scope.user = user;
		$scope.newStamp = {};
		$scope.newStamp.date = new Date();
		$scope.newStamp.checkIn = false;
		$scope.newStamp.rfidkey = $scope.user.rfidKey;
		$scope.dpDate = new Date();

		////console.log(JSON.stringify(user));
		//$scope.hide = function () {
		//    console.log("calling hide");
		//    $mdDialog.hide();
		//};
		$scope.cancel = function () {
			$mdDialog.cancel();
		};
		$scope.addStamp = function (newStamp) {
			$log.info("ADDSTAMP called with " + JSON.stringify($scope.newStamp));
			$mdDialog.hide($scope.newStamp);
		};

		$scope.updateDate = function (datep) {
			var dp = $scope.dpDate;
			var current = $scope.newStamp.date;
			current.setMonth(dp.getMonth());
			current.setDate(dp.getDate());
		};

	}

	function DialogController($scope, $mdDialog, user, stamp,$log) {
		$scope.user = user;
		$scope.stamp = angular.copy(stamp);
		$scope.userDate = new Date(stamp.date);
		$scope.userTime = new Date(stamp.date);

		//TODO problem when switching between saving date and time. if date is change then update time date is gone..
		$scope.updateDate = function (type) {
			if (type == 'time') {
				if ($scope.userTime != undefined) {
					$log.info("current date " + $scope.stamp.date);
					var tempDate = new Date($scope.stamp.date);
					//$log.info("current date " + tempDate);
					//$log.info("current year " + tempDate.getUTCFullYearYear());
					//$log.info("current month " + tempDate.getUTCMonth());
					//$log.info("current day " + tempDate.getUTCDate());

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

		//console.log(JSON.stringify(user));
		$scope.hide = function () {
			$mdDialog.hide();
		};
		$scope.cancel = function () {
			$mdDialog.cancel();
		};
		$scope.answer = function (updateStamp) {
			if (updateStamp != undefined) {
				var temp = new Date(updateStamp.date);
				updateStamp.date = temp.getTime();
				console.log("answer called with " + JSON.stringify(updateStamp));
				stamp = updateStamp;
			}
			$mdDialog.hide(updateStamp);
		};
	}

})();
