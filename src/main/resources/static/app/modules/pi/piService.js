(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:piService
	 * @description
	 * # piService
	 * Service of the app
	 */

  	angular
		.module('pi')
		.factory('PiService', Pi);
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Pi.$inject = ['$http'];

		function Pi ($http) {

		}

})();
