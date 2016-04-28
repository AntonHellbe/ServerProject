'use strict';

/**
 * @ngdoc function
 * @name app.route:piRoute
 * @description
 * # piRoute
 * Route of the app
 */

angular.module('pi')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.pi', {
				url:'/pi',
				templateUrl: 'app/modules/pi/pi.html',
				controller: 'PiCtrl',
				controllerAs: 'vm'
			});

		
	}]);
