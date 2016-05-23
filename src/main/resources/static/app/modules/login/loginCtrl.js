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

	Login.$inject = ['$rootScope', '$http', '$cookies', 'LoginService', '$state', 'WebsocketService', '$log', '$timeout', 'homeService'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Login($rootScope, $http, $cookies, LoginService, $state, WebsocketService, $log, $timeout, homeService) {

		/*jshint validthis: true */
		var vm = this;

		//TODO change to correct ip
		var ip = $rootScope.ip;


		vm.credentials = {};

		vm.loginData = {};

		vm.wsGot = [];


		//vm.wsSend=function() {
		//   WebsocketService.send(vm.wstext);
		//   vm.wstext = "";
		//};

		//vm.wsSendAll =function(){
		//   var allstr = {"area":"ACCOUNT","crudType":"ALL","token":$rootScope.authToken};
		//   $log.info("sending > " + JSON.stringify(allstr));
		//   WebsocketService.send(allstr);
		//};

		vm.wsdis = function () {
			WebsocketService.disconnect();
		};

		vm.wscon = function () {
			WebsocketService.connect();
		};

		WebsocketService.receive().then(null, null, function (message) {

			$log.info("got   message " + message);
		});

		//todo move own service
		//do auth service
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

					//vm.oldToken = "new cookie>"+JSON.stringify($cookies.getAll());
				} else {
					console.log("auth FAIL");
					$rootScope.authenticated = false;
					vm.loginData = {};
				}
				console.log("respo " + JSON.stringify(response));
				vm.loginData = response.data;

				callback && callback($rootScope.authenticated);
			}, function (errorRes) {
				console.log("error res " + JSON.stringify(errorRes));
				vm.loginData = {};
				vm.loginData = {};
				$rootScope.authenticated = false;
				callback && callback(false);
			});

		};


		//login button
		vm.login = function () {
			console.log("call login");
			console.log("creds " + JSON.stringify(vm.credentials));

			authenticate(vm.credentials, function (authenticated) {
				if (authenticated) {
					console.log("Login succeeded")

					$http.get('token').then(function (response) {
						console.log("old token> " + response.data.token);
						$rootScope.authToken = response.data.token;
						$log.info("new token> " + $rootScope.authToken);
						//todo for testing
						vm.oldToken = $rootScope.authToken;
						//$http({
						//    url: 'http://localhost:44344/api/users',
						//    method: 'GET',
						//    headers: {
						//        'X-Auth-Token': response.data.token
						//    }
						//}).then(function (response) {
						//
						//});
					});

					//connect to websocket.
					WebsocketService.connect();
					$timeout(function () {

						vm.error = false;
						$rootScope.authenticated = true;
						$log.info("auth: " + $rootScope.authenticated);
						homeService.setLoggedIn($rootScope.authenticated);
						//todo change to dash
						$state.go("home.dashboard");

					},500);



					//$timeout(function () {
					//    //$scope.myHeader = "How are you today?";
					//    $state.go("home.schedule");
					//}, 500);


				} else {
					console.log("Login failed")
					//$location.path("/login");
					vm.error = true;
					$rootScope.authenticated = false;
				}
			});
		};

		//todo remove is just extra

		//logout button
		vm.logout = function () {
			//TODO use service instead of http
			$http.post(ip + '/logout', {}).finally(function () {
				//$http.post(ip+'/api/account', {}).finally(function () {
				$rootScope.authenticated = false;
				console.log("LOGGED out");
				vm.users = {};
				vm.loginData = {};
				WebsocketService.disconnect();
				$rootScope.authToken = {};
			});

			$state.go('home.login', {}, {reload: true});
		};

		//vm.getAll = function () {
		//    $http.get("/api/users/").then(function (response) {
		//        vm.users = response;
		//    }, function (errorRes) {
		//        console.log("error res " + JSON.stringify(errorRes));
		//
		//    });
		//};

		vm.getToken = function () {
			$http.get('token').then(function (response) {
				console.log("token> " + response.data.token);
				$http({
					url: 'http://localhost:44344/api/users',
					method: 'GET',
					headers: {
						'X-Auth-Token': response.data.token
					}
				}).then(function (response) {
					vm.myToken = response.data;
				});
			});
		};

		//vm.getHack = function () {
		//    $http({
		//        url: 'http://localhost:44344/api/users',
		//        method: 'GET',
		//        headers: {
		//            'X-Auth-Token': vm.oldToken
		//        }
		//    }).then(function (response) {
		//        vm.myToken = response.data;
		//    });
		//};
		//
		//vm.clearAll= function(){
		//    vm.myToken ="";
		//    vm.users={};
		//};

		vm.cancelLogin = function () {
			$state.go("home.splash");
		};

		//todo ONLY FOR TESTING
		//todo remove
		//vm.credentials = {"username":"admin","password":"pass"};
		//vm.login();


	}

})();
