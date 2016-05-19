(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:scheduleTest
	 * @description
	 * # scheduleTest
	 * Test of the app
	 */

	describe('schedule test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('essence');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('ScheduleCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
