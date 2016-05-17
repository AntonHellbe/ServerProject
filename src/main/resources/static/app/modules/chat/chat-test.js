(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:chatTest
	 * @description
	 * # chatTest
	 * Test of the app
	 */

	describe('chat test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('essence');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('ChatCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
