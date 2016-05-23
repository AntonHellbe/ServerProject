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

		vm.isAuth=homeService.getLoggedIn();

		homeService.subscribe(vm, function somethingChanged(event, data) {
			// Handle notification
			console.log("got update "+data);
			vm.isAuth = data;
		});




	}

})();
