(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:timestampsService
	 * @description
	 * # timestampsService
	 * Service of the app
	 */

  	angular
		.module('timestamps')
		.factory('TimestampsService', Timestamps);
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Timestamps.$inject = ['$http'];

		function Timestamps ($http) {

		}

})();
