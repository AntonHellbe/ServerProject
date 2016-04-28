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

		Accounts.$inject = ['$http'];

		function Accounts ($http) {

		}

})();
