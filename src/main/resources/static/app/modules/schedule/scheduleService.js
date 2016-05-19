(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:scheduleService
	 * @description
	 * # scheduleService
	 * Service of the app
	 */

  	angular
		.module('schedule')
		.factory('ScheduleService', Schedule);
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Schedule.$inject = ['$http'];

		function Schedule ($http) {

		}

})();
