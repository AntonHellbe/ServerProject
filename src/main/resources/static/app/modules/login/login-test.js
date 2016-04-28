(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:loginTest
	 * @description
	 * # loginTest
	 * Test of the app
	 */

	describe('login test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('essence');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('LoginCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
