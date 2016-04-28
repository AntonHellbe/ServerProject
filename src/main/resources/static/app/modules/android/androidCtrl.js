(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:androidCtrl
	* @description
	* # androidCtrl
	* Controller of the app
	*/

  	angular
		.module('android')
		.controller('AndroidCtrl', Android);

		Android.$inject = [];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Android() {
			/*jshint validthis: true */
			var vm = this;

		}

})();
