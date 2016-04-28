(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:loginService
	 * @description
	 * # loginService
	 * Service of the app
	 */

  	angular
		.module('login')
		.factory('LoginService', Login);
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Login.$inject = ['$http'];

		function Login ($http) {



			return{
				doLogin : doLogin
			};

			function doLogin(creds)
			{

				var headers = creds ? {
					authorization: "Basic "
					+ btoa(creds.username + ":"
						+ creds.password)
				} : {};

				console.log("sending get for login");
				var resp = $http.get('localhost:8080/api/account', {
					headers: headers
				});
				//var resp = $http.post('localhost:8080/api/account', {
				//	headers: headers
				//},creds);
				console.log("got resp "+JSON.stringify(resp));
				return resp;


			}
		}


})();
