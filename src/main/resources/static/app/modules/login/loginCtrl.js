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

		Login.$inject = ['$rootScope', '$http', '$location','$cookieStore','LoginService'];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Login($rootScope, $http, $location,$cookieStore,LoginService) {
			/*jshint validthis: true */
			var vm = this;

			vm.credentials = {};
			vm.loginData={};

			//do auth service
			var authenticate = function (credentials, callback) {

				var headers = credentials ? {
					authorization: "Basic "
					+ btoa(credentials.username + ":"
						+ credentials.password)
				} : {};

				console.log("Do login call");

				$http.get('/api/account', {
					headers: headers
				}).then(function (response) {
					if (response.data.name) {
						console.log("auth succes");
						$rootScope.authenticated = true;
					} else {
						console.log("auth FAIL");
						$rootScope.authenticated = false;
					}
					console.log("respo "+JSON.stringify(response));
					vm.loginData = response.data;

					callback && callback($rootScope.authenticated);
				}, function (errorRes) {
					console.log("error res "+JSON.stringify(errorRes));
                    vm.loginData={};
					$rootScope.authenticated = false;
					callback && callback(false);
				});

			};


			vm.users ="lalala";


			vm.credentials = {"username":"user","password":"pass"};

			//login button
			vm.login = function () {
				console.log("call login");
				console.log("creds "+JSON.stringify(vm.credentials));

				authenticate(vm.credentials, function (authenticated) {
					if (authenticated) {
						console.log("Login succeeded")
						//$location.path("/");
						vm.error = false;
						$rootScope.authenticated = true;
					} else {
						console.log("Login failed")
						//$location.path("/login");
						vm.error = true;
						$rootScope.authenticated = false;
					}
				});

				////FUNKAR
				//var credentials = vm.credentials;
                //
				//	var headers = {
				//		authorization: "Basic "
				//		+ btoa(credentials.username + ":"
				//			+ credentials.password)
				//	};
                //
                //
				//var resp = $http.get('https://localhost/api/account', {
				//	headers: headers
				//}).then(function (resp){
				//	console.log("resp "+JSON.stringify(resp));
				//	vm.credentials = resp;
                //
				//	$http.get('https://localhost/api/users').then(function(resp){
				//		vm.users = resp;
				//	});
				//});


				//var success = function (data) {
				//	var token = data.token;
                //
				//	api.init(token);
                //
				//	$cookieStore.put('token', token);
				//	$location.path('/');
				//};
                //
				//var error = function () {
				//	// TODO: apply user notification here..
				//	console.log("Error login in");
				//};
                //
				//LoginService.doLogin(vm.credentials).success(success).error(error);


				//	if (authenticated) {
				//		console.log("Login succeeded")
				//		//$location.path("/");
				//		vm.error = false;
				//		$rootScope.authenticated = true;
				//	} else {
				//		console.log("Login failed")
				//		//$location.path("/login");
				//		vm.error = true;
				//		$rootScope.authenticated = false;
				//	}
				//})
			};

			vm.getAll=function(){
				console.log("get all");
				$http.get('/api/users').then(function(resp){
					vm.users = resp;
				}, function (errorRes) {
                    console.log("error res "+JSON.stringify(errorRes));
                    vm.users={};
                });
			};

			//logout button
			vm.logout = function () {
				$http.post('/logout', {}).finally(function () {
					$rootScope.authenticated = false;
					console.log("LOGED out");
					//$location.path("/");
                    vm.users={};
                    vm.loginData={};
				});
			}



		}

})();
