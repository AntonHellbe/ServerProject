(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.controller:LayoutCtrl
	 * @description
	 * # LayoutCtrl
	 * Controller of the app
	 */

	angular
		.module('essence')
		.controller('LayoutCtrl', Layout);

	//Layout.$inject = ['$mdSidenav', '$state', '$mdToast', '$mdDialog', '$http', '$rootScope','WebsocketService','homeService'];
	Layout.$inject = ['$mdSidenav', '$state', '$rootScope','WebsocketService','homeService','$http'];

	/*
	 * recommend
	 * Using function declarations
	 * and bindable members up top.
	 */

	function Layout($mdSidenav, $state, $rootScope,WebsocketService,homeService,$http) {
		/*jshint validthis: true */
		var vm = this;

		/**
		 * Handles sidenavbar
		 * @param menuId
		 */
		vm.toggleSidenav = function (menuId) {
			$mdSidenav(menuId).toggle();
		};

		/**
		 * Handles logout call
		 */
		vm.logOut = function () {

			console.log("loggout " + $rootScope.authenticated);

			$http.post($rootScope.ip+'/logout', {}).finally(function () {
				$rootScope.authenticated = false;
                $rootScope.authData={};
				//console.log("LOGED out");
				homeService.setLoggedIn($rootScope.authenticated);
				WebsocketService.disconnect();
				$rootScope.authToken = {};
			});

			$state.go('home.splash', {}, {reload: true});

		};

		var originatorEv;
		/**
		 * Show menu
		 * @param $mdOpenMenu
		 * @param ev
		 */
		vm.openMenu = function ($mdOpenMenu, ev) {
			originatorEv = ev;
			$mdOpenMenu(ev);
		};

	}

})();
