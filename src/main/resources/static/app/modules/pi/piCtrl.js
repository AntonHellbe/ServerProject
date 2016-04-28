(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:piCtrl
	* @description
	* # piCtrl
	* Controller of the app
	*/

  	angular
		.module('pi')
		.controller('PiCtrl', Pi);

		Pi.$inject = [];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Pi() {
			/*jshint validthis: true */
			var vm = this;

		}

})();
