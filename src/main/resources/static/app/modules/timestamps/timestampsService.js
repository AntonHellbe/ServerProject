(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:timestampsService
	 * @description
	 * # timestampsService
	 * Service of the app
	 */

  	angular
		.module('timestamps')
		.factory('TimestampsService', Timestamps);
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Timestamps.$inject = ['$http','$resource','$rootScope'];

		function Timestamps ($http,$resource,$rootScope) {
            var tempUrl = $rootScope.ip;
            console.log("tempUrl "+tempUrl);

            return $resource(tempUrl+'/api/time/:id/:stampId', {
                id: '@id',stampId:'@stampId'
            }, {
                get:{
                    method: "GET",
                    isArray: true
                },
                update: {
                    method: "PUT"
                },
                remove: {
                    method: "DELETE"
                },
                save:{
                    method: "POST"
                }
            });

		}

})();

