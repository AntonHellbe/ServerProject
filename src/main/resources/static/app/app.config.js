(function () {
	'use strict';

	/**
	 * @ngdoc configuration file
	 * @name app.config:config
	 * @description
	 * # Config and run block
	 * Configutation of the app
	 */


	angular
		.module('essence')
		.config(configure)
		.run(runBlock)
		.provider('myCSRF',[function(){
			var headerName = 'X-CSRFToken';
			var cookieName = 'csrftoken';
			var allowedMethods = ['GET'];

			this.setHeaderName = function(n) {
				headerName = n;
			}
			this.setCookieName = function(n) {
				cookieName = n;
			}
			this.setAllowedMethods = function(n) {
				allowedMethods = n;
			}
			this.$get = ['$cookies', function($cookies){
				return {
					'request': function(config) {
						if(allowedMethods.indexOf(config.method) === -1) {
							// do something on success
							config.headers[headerName] = $cookies[cookieName];
						}
						return config;
					}
				}
			}];
		}]).config(function($httpProvider) {
		$httpProvider.interceptors.push('myCSRF');
	});


	configure.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider'];

	function configure($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {

		$locationProvider.hashPrefix('!');


		// This is required for Browser Sync to work poperly
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
		$httpProvider.defaults.withCredentials = true;



		$urlRouterProvider
			.otherwise('/dashboard');

	}

	runBlock.$inject = ['$rootScope','$http', '$cookies'];

	function runBlock($rootScope,$http, $cookies) {
		'use strict';
		$http.defaults.headers.post['X-CSRFToken'] = $cookies.csrftoken;
		console.log('AngularJS run() function...');

	}


})();
