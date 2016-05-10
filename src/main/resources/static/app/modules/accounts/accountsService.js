(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:accountsService
	 * @description
	 * # accountsService
	 * Service of the app
	 */

  	angular
		.module('accounts')
		.factory('AccountsService', Accounts);
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Accounts.$inject = ['$http','$resource','$rootScope'];

		function Accounts ($http,$resource,$rootScope) {


			var tempUrl = $rootScope.ip;
			console.log("tempUrl "+tempUrl);


			return $resource(tempUrl+'/api/users/:id', {
				id: '@id'
			}, {
				update: {
					method: "PUT"
				},
				remove: {
					method: "DELETE"
				},
				save:{
					method: "POST"
				}
            });



		}

})();
