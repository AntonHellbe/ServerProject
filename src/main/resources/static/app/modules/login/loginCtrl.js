(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.controller:loginCtrl
	 * @description
	 * # loginCtrl
	 * Controller of the app
	 */

	angular
		.module('login')
		.controller('LoginCtrl', Login);

	Login.$inject = ['$rootScope', '$http', '$state', 'WebsocketService', '$timeout', 'homeService','$mdDialog','$filter'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Login($rootScope, $http, $state, WebsocketService, $timeout, homeService,$mdDialog,$filter) {

		/*jshint validthis: true */
		var vm = this;

		var ip = $rootScope.ip;

		vm.credentials = {};



		/**
		 * Do the Authentication
		 * @param credentials user credentials
		 * @param callback callbackfunction called when got result from server.
		 */
		var authenticate = function (credentials, callback) {

			var headers = credentials ? {
				authorization: "Basic "
				+ btoa(credentials.username + ":"
					+ credentials.password)
			} : {};

			console.log("Do login call");

			$http.get(ip + '/api/account', {
				headers: headers
			}).then(function (response) {
				if (response.data.name) {
					console.log("auth succes");
					$rootScope.authenticated = true;
					$rootScope.authData = response.data;
				} else {
					console.log("auth FAIL");
					$rootScope.authenticated = false;
				}
				console.log("respo " + JSON.stringify(response));

				callback && callback($rootScope.authenticated);
			}, function (errorRes) {
				console.log("error res " + JSON.stringify(errorRes));
				$rootScope.authenticated = false;
				callback && callback(false);
			});

		};


		/**
		 * Error handler
		 * @param message error message
		 */
		vm.showAlert=function(message){
			$mdDialog.show(
				$mdDialog.alert()
					.clickOutsideToClose(true)
					.title('Error loging in!')
					.textContent(message)
					.ariaLabel('Error on login')
					.ok('OK')
			);
		};

		/**
		 * Login button
		 */
		vm.login = function () {
			console.log("call login");

			authenticate(vm.credentials, function (authenticated) {
				if (authenticated) {
					console.log("Login succeeded");

					//check if user is admin
					var hasAdmin=$filter('filter')($rootScope.authData.authorities, 'ROLE_ADMIN');

					if(hasAdmin.length >0) {
						$http.get('token').then(function (response) {
							$rootScope.authToken = response.data.token;
							vm.oldToken = $rootScope.authToken;
						});

						//connect to websocket.
						WebsocketService.connect();
						$timeout(function () {

							vm.error = false;
							$rootScope.authenticated = true;
							homeService.setLoggedIn($rootScope.authenticated);
							$state.go("home.dashboard");

						},500);
					}
					else {
						vm.showAlert("You aren't admin!");
						vm.logout();
					}

				} else {
					console.log("Login failed");
					vm.error = true;
					$rootScope.authenticated = false;
					vm.showAlert("Wrong password or username!");
				}
			});
		};

		/**
		 *logout handler
		 */
		vm.logout = function () {
			$http.post(ip + '/logout', {}).finally(function () {
				console.log("LOGGED out");
				WebsocketService.disconnect();
				$rootScope.authenticated = false;
				$rootScope.authToken = {};
				$rootScope.authData = {};
			});
		};


		/**
		 * Cancel loging in.
		 */
		vm.cancelLogin = function () {
			$state.go("home.splash");
		};
		//end of ctrl
	}

})();
