(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:accountsCtrl
	* @description
	* # accountsCtrl
	* Controller of the app
	*/

  	angular
		.module('accounts')
		.controller('AccountsCtrl', Accounts);

		Accounts.$inject = [];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Accounts() {
			/*jshint validthis: true */
			var vm = this;

		}

})();
