(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:timestampsCtrl
	* @description
	* # timestampsCtrl
	* Controller of the app
	*/

  	angular
		.module('timestamps')
		.controller('TimestampsCtrl', Timestamps);

		Timestamps.$inject = [];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Timestamps() {
			/*jshint validthis: true */
			var vm = this;

		}

})();
