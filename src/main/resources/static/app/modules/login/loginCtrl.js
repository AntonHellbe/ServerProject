(function() {
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

		Login.$inject = ['$rootScope', '$http', '$location','$cookieStore','LoginService','$state'];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Login($rootScope, $http, $location,$cookieStore,LoginService,$state) {

			//TODO change to correct ip
			var ip =$rootScope.ip;

			/*jshint validthis: true */
			var vm = this;

			vm.credentials = {};
			vm.loginData={};

            //todo move own service
			//do auth service
			var authenticate = function (credentials, callback) {

				var headers = credentials ? {
					authorization: "Basic "
					+ btoa(credentials.username + ":"
						+ credentials.password)
				} : {};

				console.log("Do login call");

				$http.get(ip+'/api/account', {
					headers: headers
				}).then(function (response) {
					if (response.data.name) {
						console.log("auth succes");
						$rootScope.authenticated = true;
                        $rootScope.authData = response.data;
					} else {
						console.log("auth FAIL");
						$rootScope.authenticated = false;
                        vm.loginData = {};
					}
					console.log("respo "+JSON.stringify(response));
					vm.loginData = response.data;

					callback && callback($rootScope.authenticated);
				}, function (errorRes) {
					console.log("error res "+JSON.stringify(errorRes));
                    vm.loginData={};
                    vm.loginData = {};
					$rootScope.authenticated = false;
					callback && callback(false);
				});

			};


			vm.credentials = {"username":"user","password":"pass"};

			//login button
			vm.login = function () {
                //TODO use service instead of http
				console.log("call login");
				console.log("creds "+JSON.stringify(vm.credentials));

				authenticate(vm.credentials, function (authenticated) {
					if (authenticated) {
						console.log("Login succeeded")
						//$location.path("/");

						vm.error = false;
						$rootScope.authenticated = true;
                        $state.go("home.dashboard");
					} else {
						console.log("Login failed")
						//$location.path("/login");
						vm.error = true;
						$rootScope.authenticated = false;
					}
				});
			};

			//logout button
			vm.logout = function () {
                //TODO use service instead of http
				$http.get(ip+'/logout', {}).finally(function () {
					$rootScope.authenticated = false;
					console.log("LOGGED out");
                    vm.users={};
                    vm.loginData={};
				});

                $state.go('home.login', {}, {reload: true});
			}



		}

})();
