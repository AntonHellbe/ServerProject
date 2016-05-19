'use strict';

/**
 * @ngdoc function
 * @name app.route:scheduleRoute
 * @description
 * # scheduleRoute
 * Route of the app
 */

angular.module('schedule')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.schedule', {
				url:'/schedule',
				templateUrl: 'app/modules/schedule/schedule.html',
				controller: 'ScheduleCtrl',
				controllerAs: 'vm',
				needAuth: true
			});

		
	}]);
