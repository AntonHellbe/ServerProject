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

	Home.$inject = ['homeService'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Home(homeService) {
		/*jshint validthis: true */
		var vm = this;
		vm.title = "Hello, essence!";
		vm.version = "1.0.0";
		vm.listFeatures = homeService.getFeaturesList();

	}

})();
