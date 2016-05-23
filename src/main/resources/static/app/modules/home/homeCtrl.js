(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.controller:HomeCtrl
	 * @description
	 * # HomeCtrl
	 * Controller of the app
	 */

	angular
		.module('essence')
		.controller('HomeCtrl', Home);

	Home.$inject = ['homeService','$rootScope'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Home(homeService,$rootScope) {
		/*jshint validthis: true */
		var vm = this;
		vm.title = "Essence";
		vm.version = "1.0.0";
		vm.listFeatures = homeService.getFeaturesList();
		vm.isAuth = $rootScope.authenticated;

		vm.isAuth=homeService.getAuth();

		vm.setLoggedIn = function(isAuth) {
			console.log("new Auth "+isAuth);
			vm.isAuth = isAuth;
		};

	}

})();
