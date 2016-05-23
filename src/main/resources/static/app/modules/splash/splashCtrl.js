(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:splashCtrl
	* @description
	* # splashCtrl
	* Controller of the app
	*/

  	angular
		.module('splash')
		.controller('SplashCtrl', Splash);

		Splash.$inject = [];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Splash() {
			/*jshint validthis: true */
			var vm = this;

		}

})();
