(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:piTest
	 * @description
	 * # piTest
	 * Test of the app
	 */

	describe('pi test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('essence');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('PiCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
