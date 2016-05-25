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

		return {
			setLoggedIn: setLoggedIn,
			getLoggedIn: getLoggedIn,
			subscribe:subscribe,
			notify:notify

		};


		/**
		 * return true if user is logged in
		 * @returns {boolean} true if user is logged in
		 */
		function getLoggedIn() {
			//console.log("is auth " + isAuth);
			return isAuth;
		}

		/**
		 * Subscribes to listen for changes, used for handling user Authentication
		 * @param scope
		 * @param callback
		 */
		function subscribe(scope, callback) {
			var handler = $rootScope.$on('notifying-service-event', callback);
			//scope.$on('$destroy', handler);
		};

		/**
		 * notifies all views about authenticaiton status
		 */
		function notify() {
			//console.log("sending notify " + isAuth);
			$rootScope.$emit('notifying-service-event',isAuth);
		};

		/**
		 * Set authentication status
		 * @param newAuth
		 */
		function setLoggedIn(newAuth) {

			isAuth = newAuth;
			//console.log("is auth " + isAuth);
			notify();
		};

	}

})();
