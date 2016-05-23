(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:splashTest
	 * @description
	 * # splashTest
	 * Test of the app
	 */

	describe('splash test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('essence');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('SplashCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
