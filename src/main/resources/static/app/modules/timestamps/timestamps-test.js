(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:timestampsTest
	 * @description
	 * # timestampsTest
	 * Test of the app
	 */

	describe('timestamps test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('essence');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('TimestampsCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
