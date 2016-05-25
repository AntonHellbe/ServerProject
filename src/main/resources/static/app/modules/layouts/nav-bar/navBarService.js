(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:menuService
	 * @description
	 * # menuService
	 * Service of the app
	 */

	angular
		.module('essence')
		.factory('MenuService', Menu);
	// Inject your dependencies as .$inject = ['$http', 'someSevide'];
	// function Name ($http, someSevide) {...}

	Menu.$inject = ['$http'];

	function Menu($http) {

		var menu = [

			{
				link: 'accounts',
				name: 'Accounts'
			},

			{
				link: 'timestamps',
				name: 'Timestamps'
			},

			{
				link: 'chat',
				name: 'Chat'
			},
		];

		return {
			listMenu: function () {
				return menu;
			}
		}

	}

})();
