(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.controller:accountsCtrl
	 * @description
	 * # accountsCtrl
	 * Controller of the app
	 */

	angular
		.module('accounts')
		.controller('AccountsCtrl', Accounts);

	//Accounts.$inject = ['AccountsService', 'WebsocketService', '$log', '$filter', '$rootScope'];
	Accounts.$inject = ['WebsocketService', '$log', '$rootScope', '$filter'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Accounts(WebsocketService, $log, $rootScope, $filter) {
		/*jshint validthis: true */
		var vm = this;

		//vm.authorities = [{authority: "ROLE_USER"}, {authority: "ROLE_ADMIN"}];
		//roles list
		//vm.items = [{authority: "ROLE_USER"}, {authority: "ROLE_ADMIN"}, {authority: "ROLE_PI"}];
		vm.items = ["ROLE_USER", "ROLE_ADMIN", "ROLE_PI"];
		vm.allusers = [];
		vm.cats = [];

		vm.updateUserId = "";
		vm.updateUserIdx = -1;

		WebsocketService.receive().then(null, null, function (wsUpdate) {

			$log.info("Got Account: " + wsUpdate);

			if (wsUpdate.error != undefined) {
				$log.info("Error " + wsUpdate.error);
				return;
			}
			else {
				$log.info("NO Error " + wsUpdate.error);

			}
			//$log.info("got update from server: " + JSON.stringify(wsUpdate));

			//Check that is account answer
			if (wsUpdate.area == "ACCOUNT") {
				if (wsUpdate.crudType != undefined) {
					switch (wsUpdate.crudType) {
						case "ADD":
							$log.info("ws Add user " + JSON.stringify(wsUpdate.payload));
							vm.allusers.push(wsUpdate.payload);
							break;
						case "DELETE":
							$log.info("got delete user id: " + wsUpdate.affectedId);
							var found = $filter('filter')(vm.allusers, {id: wsUpdate.affectedId})[0];
							vm.allusers.splice(vm.allusers.indexOf(found), 1);
							break;
						case "UPDATE":

							$log.info("Got update from ws");
							$log.info("updated user " + wsUpdate.payload.firstName);
							var found = $filter('filter')(vm.allusers, {id: wsUpdate.payload.id})[0];
							if (found != undefined) {
								$log.info("found user " + found);
								var idx = vm.allusers.indexOf(found);
								vm.allusers.splice(idx, 1);
								vm.allusers.splice(idx, 0, wsUpdate.payload);
							}
							else {
								//TODO show error msg about update
								$log.info("didnt find user " + wsUpdate.affectedId);
							}
							break;

						case "ALL":
							$log.info("got All users");

							if (wsUpdate.token == $rootScope.authToken) {
								$log.info("I asked for it " + $rootScope.authToken);
								vm.allusers = wsUpdate.payloadList ? wsUpdate.payloadList : [];
							}
							else {
								$log.info("Someone else wanted get all");
								vm.allusers = vm.allusers ? wsUpdate.payloadList : [];
							}
							break;

						case "PASSWORD":
							$log.info("Got Changepassword from ws");
							//$log.info("updated user " + wsUpdate.payload.firstName);
							var found = $filter('filter')(vm.allusers, {id: wsUpdate.affectedId})[0];
							if (found != undefined) {
								$log.info("found user " + found);
								var idx = vm.allusers.indexOf(found);
								vm.allusers.splice(idx, 1);
								vm.allusers.splice(idx, 0, wsUpdate.payload);
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

		/**
		 * default get all users
		 */
		vm.getAll = function (isManual) {

			if (isManual) {
				var allstr = {"area": "ACCOUNT", "crudType": "ALL", "token": $rootScope.authToken};
				WebsocketService.send(allstr);

			}
			else {
				var users = WebsocketService.getUsers();
				if (users.length == 0) {
					var allstr = {"area": "ACCOUNT", "crudType": "ALL", "token": $rootScope.authToken};
					WebsocketService.send(allstr);
				}
				else {
					vm.allusers = users;
				}
			}
			//AccountsService.query().$promise.then(
			//    function (success) {
			//        console.log("Success");
			//        vm.allusers = success ? success : [];
			//    },
			//    function (error) {
			//        alert("Failed " + JSON.stringify(error));
			//    },
			//    function (update) {
			//        alert("Got notification" + JSON.stringify(update));
			//    }
			//);
		};

		vm.getAll();

		vm.cancelAdd = function () {
			console.log("calling cancel add");
			vm.newUser = {};
			vm.showAddUser = !vm.showAddUser;
		};

		//add a new user
		vm.addUser = function (newUser) {
			$log.info("add user " + JSON.stringify(newUser));

			//TODO check that rfid exists
			if (newUser == undefined) {
				return;
			}
			if (newUser.rfidKey != undefined) {
				if (newUser.rfidKey.enabled == undefined) {
					newUser.rfidKey.enabled = false;
				}
			}


			if (newUser.authorities != undefined) {
				var size = newUser.authorities.length;
				var auths = newUser.authorities;
				var templist = [];
				for (var i = 0; i < size; i++) {
					console.log("auth " + JSON.stringify(auths[i]));
					templist.push({authority: auths[i].authority});
				}
				newUser.authorities = templist;
				console.log("add user" + JSON.stringify(newUser));
			}


			var sendMsg = {"area": "ACCOUNT", "crudType": "ADD", "token": $rootScope.authToken};
			sendMsg.payload = newUser;

			$log.info("sending > " + JSON.stringify(sendMsg));
			WebsocketService.send(sendMsg);

			//AccountsService.save(newUser)
			//	.$promise.then(
			//	function (success) {
			//		console.log("Successfuly Added");
			//		vm.allusers.push(success);
			//	},
			//	function (error) {
			//		alert("Failed " + JSON.stringify(error));
			//	},
			//	function (update) {
			//		alert("Got notification" + JSON.stringify(update));
			//	});

		};


		//update user
		vm.updateUser = function (user, index) {
			$log.info("updateing " + JSON.stringify(user));
			//$log.info("updateing idx: " + index);

			vm.updateUserId = user.id;
			vm.updateUserIdx = index;


			if (user.rfidKey != undefined) {
				if (user.rfidKey.enabled == undefined) {
					user.rfidKey.enabled = false;
				}
			}

			var sendMsg = {"area": "ACCOUNT", "crudType": "UPDATE", "token": $rootScope.authToken};
			sendMsg.payload = user;
			sendMsg.affectedId = user.id;

			$log.info("sending > " + JSON.stringify(sendMsg));
			WebsocketService.send(sendMsg);

			//AccountsService.update(user)
			//	.$promise.then(
			//	function (success) {
			//		$log.info("update success");
			//		$log.info("answer " + JSON.stringify(success));
			//
			//		$log.info("updateing idx: " + index);
			//
			//	},
			//	function (error) {
			//		alert("Failed " + JSON.stringify(error));
			//	},
			//	function (update) {
			//		alert("Got notification" + JSON.stringify(update));
			//	});

		};

		/**
		 * Delete user
		 * @param user the user to be removed
		 */
		vm.deleteUser = function (user) {
			$log.info("delete user " + user.id);

			var sendMsg = {"area": "ACCOUNT", "crudType": "DELETE", "token": $rootScope.authToken};
			sendMsg.affectedId = user.id;

			$log.info("sending > " + JSON.stringify(sendMsg));
			WebsocketService.send(sendMsg);

			//AccountsService.remove(user)
			//	.$promise.then(
			//	function (success) {
			//		console.log("Successfuly Removed User");
			//		vm.allusers.splice(vm.allusers.indexOf(user), 1);
			//	},
			//	function (error) {
			//		alert("Failed " + JSON.stringify(error));
			//	},
			//	function (update) {
			//		alert("Got notification" + JSON.stringify(update));
			//	});
		};

		vm.toggle = function (item, selected) {
			$log.info("select item " + item + " add to list: " + selected);

			var res = undefined;
			if (selected != undefined) {
				res = $filter('filter')(selected, item)[0];
			}


			if (res == undefined) {
				$log.info("not in list");
				$log.info("ADD");
				selected.push({authority: item});

			}
			else {
				var idx = selected.indexOf(res);
				$log.info("Remove" + JSON.stringify(res) + " at: " + idx);
				selected.splice(idx, 1);
			}

		};
		vm.exists = function (item, selected) {

			var res = undefined;
			if (selected != undefined) {
				//$log.info("selected is " + JSON.stringify(selected));

				res = $filter('filter')(selected, item)[0];
				//$log.info("res:" + JSON.stringify(res));
			}
			else {
				//$log.info("selected is undefined " + JSON.stringify(selected));
			}
			return (res != undefined);
		};
		vm.isIndeterminate = function (selected) {
			//$log.info("is indeterminate: " + +((selected.length !== 0 &&
			//	selected.length !== vm.items.length)));
			return (selected.length !== 0 &&
			selected.length !== vm.items.length);
		};
		vm.isChecked = function (selected) {
			if (selected == undefined) {
				$log.info("user has no Auths");
				return false;
			}
			else {
				//$log.info("is selcted: " + (selected.length === vm.items.length));
				return selected.length === vm.items.length;
			}
		};
		vm.toggleAll = function (selected) {
			if (selected.length === vm.items.length) {
				$log.info("all is selected, deselect all!");
				selected.length = 0;
				selected = [];
			} else if (selected.length === 0 || selected.length > 0) {
				$log.info("None is selected, select all");
				//selected = vm.items.slice(0);
				selected.length = 0;

				angular.forEach(vm.items, function (item) {
					selected.push({authority: item});
				});

				$log.info("selected " + JSON.stringify(selected));
				$log.info("vm selected " + JSON.stringify(vm.selected));
			}
		};

		vm.changePassword = function (user, newPassword) {
			$log.info("change password");
			$log.info("user " + user.username + " newpass " + newPassword);

			if (newPassword != undefined) {


				var sendMsg = {"area": "ACCOUNT", "crudType": "PASSWORD", "token": $rootScope.authToken};
				sendMsg.payload = newPassword;
				sendMsg.affectedId = user.id;

				$log.info("sending > " + JSON.stringify(sendMsg));
				WebsocketService.send(sendMsg);
				vm.showChangePassword = !vm.showChangePassword;
				vm.newPassword = "";
			}
		};
	}
})();
