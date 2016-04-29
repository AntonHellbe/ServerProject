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

	Accounts.$inject = ['AccountsService', '$q'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Accounts(AccountsService, $q) {
		/*jshint validthis: true */
		var vm = this;

		vm.authorities = [{authority: "ROLE_USER"}, {authority: "ROLE_ADMIN"}]

		//vm.allusers="all users";

		vm.addUser = function (newUser) {


			//var aur = {authority: newUser.authorities.authority};
			//newUser.authorities = [aur];
			console.log("add user" + JSON.stringify(newUser));

			AccountsService.save(newUser)
				.$promise.then(
				function (success) {
					console.log("Successfuly Added");
					vm.allusers.push(success);
				},
				function (error) {
					alert("Failed " + JSON.stringify(error));
				},
				function (update) {
					alert("Got notification" + JSON.stringify(update));
				});
		};

		vm.updateUser = function (user) {

				AccountsService.update(user)
					.$promise.then(
					function (success) {
						console.log("Successfuly updated");
					},
					function (error) {
						alert("Failed " + JSON.stringify(error));
					},
					function (update) {
						alert("Got notification" + JSON.stringify(update));
					});

		};

		vm.deleteUser = function (user) {
			AccountsService.remove(user)
				.$promise.then(
				function (success) {
					console.log("Successfuly Removed User");
					vm.allusers.splice(vm.allusers.indexOf(user), 1);
				},
				function (error) {
					alert("Failed " + JSON.stringify(error));
				},
				function (update) {
					alert("Got notification" + JSON.stringify(update));
				});
		};

		AccountsService.query().$promise.then(
			function (success) {
				console.log("Success");
				vm.allusers = success ? success : [];
			},
			function (error) {
				alert("Failed " + JSON.stringify(error));
			},
			function (update) {
				alert("Got notification" + JSON.stringify(update));
			});


	}

})();
