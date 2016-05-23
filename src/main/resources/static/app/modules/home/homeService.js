(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:homeService
	 * @description
	 * # homeService
	 * Service of the app
	 */

	angular.module('essence')
		.factory('homeService', homeService);

	homeService.$inject = ['$http','$rootScope'];

	function homeService($http,$rootScope) {

		var isAuth = false;

		var list = [
			{"feature": "Implemented Best Practices, following: John Papa's Guide"},
			{"feature": "Using Controller AS syntax"},
			{"feature": "Wrap Angular components in an Immediately Invoked Function Expression (IIFE)"},
			{"feature": "Declare modules without a variable using the setter syntax"},
			{"feature": "Using named functions"},
			{"feature": "Including Unit test with Karma"},
			{"feature": "Including UI options for Bootstrap or Angular-Material"},
			{"feature": "Including Angular-Material-Icons for Angular-Material UI"},
			{"feature": "Dynamic Menu generator for both themes"},
			{"feature": "Grunt task for Production and Development"}
		];

		return {
			getFeaturesList: getFeaturesList,
			setLoggedIn: setLoggedIn,
			getLoggedIn: getLoggedIn,
			subscribe:subscribe,
			notify:notify

		};



		function getLoggedIn() {
			console.log("is auth " + isAuth);
			return isAuth;
		}

		function subscribe(scope, callback) {
			var handler = $rootScope.$on('notifying-service-event', callback);
			//scope.$on('$destroy', handler);
		};

		function notify() {
			console.log("sending notify " + isAuth);
			$rootScope.$emit('notifying-service-event',isAuth);
		};

		function setLoggedIn(newAuth) {

			isAuth = newAuth;
			console.log("is auth " + isAuth);
			notify();
		};


		function getFeaturesList() {
			return list
		};

	}

})();
