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

		Splash.$inject = ['$state'];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Splash($state) {
			/*jshint validthis: true */
			var vm = this;

			vm.gotoLogin =function() {
				$state.go("home.dashboard");
			}

		}

})();
