(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.module:loginModule
	 * @description
	 * # loginModule
	 * Module of the app
	 */

  	angular.module('login', []).config(function ( $httpProvider) {

	    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    });

})();
