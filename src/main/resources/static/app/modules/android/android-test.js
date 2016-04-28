(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:androidTest
	 * @description
	 * # androidTest
	 * Test of the app
	 */

	describe('android test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('essence');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('AndroidCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
