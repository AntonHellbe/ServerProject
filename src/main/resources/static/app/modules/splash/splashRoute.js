'use strict';

/**
 * @ngdoc function
 * @name app.route:splashRoute
 * @description
 * # splashRoute
 * Route of the app
 */

angular.module('splash')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.splash', {
				url:'/',
				//url:'/splash',
				templateUrl: 'app/modules/splash/splash.html',
				controller: 'SplashCtrl',
				controllerAs: 'vm'
			});

		
	}]);
