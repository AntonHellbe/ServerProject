(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:androidService
	 * @description
	 * # androidService
	 * Service of the app
	 */

  	angular
		.module('android')
		.factory('AndroidService', Android);
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Android.$inject = ['$http'];

		function Android ($http) {

		}

})();
