'use strict';

/**
 * @ngdoc function
 * @name app.route:chatRoute
 * @description
 * # chatRoute
 * Route of the app
 */

angular.module('chat')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.chat', {
				url:'/chat',
				templateUrl: 'app/modules/chat/chat.html',
				controller: 'ChatCtrl',
				controllerAs: 'vm',
				needAuth: true
			});

		
	}]);
