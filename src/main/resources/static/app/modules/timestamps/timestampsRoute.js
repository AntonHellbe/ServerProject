'use strict';

/**
 * @ngdoc function
 * @name app.route:timestampsRoute
 * @description
 * # timestampsRoute
 * Route of the app
 */

angular.module('timestamps')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.timestamps', {
				url:'/timestamps',
				templateUrl: 'app/modules/timestamps/timestamps.html',
				controller: 'TimestampsCtrl',
				controllerAs: 'vm'
			});

		
	}]);
