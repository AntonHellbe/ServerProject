(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:accountsTest
	 * @description
	 * # accountsTest
	 * Test of the app
	 */

	describe('accounts test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('essence');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('AccountsCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
