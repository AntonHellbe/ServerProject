'use strict';

/**
 * @ngdoc function
 * @name app.route:accountsRoute
 * @description
 * # accountsRoute
 * Route of the app
 */

angular.module('accounts')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.accounts', {
				url:'/accounts',
				templateUrl: 'app/modules/accounts/accounts.html',
				controller: 'AccountsCtrl',
				controllerAs: 'vm'
			});

		
	}]);
