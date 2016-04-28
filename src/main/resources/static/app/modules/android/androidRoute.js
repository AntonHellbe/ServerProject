'use strict';

/**
 * @ngdoc function
 * @name app.route:androidRoute
 * @description
 * # androidRoute
 * Route of the app
 */

angular.module('android')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.android', {
				url:'/android',
				templateUrl: 'app/modules/android/android.html',
				controller: 'AndroidCtrl',
				controllerAs: 'vm'
			});

		
	}]);
