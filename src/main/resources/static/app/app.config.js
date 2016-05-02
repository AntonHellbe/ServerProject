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
		.run(runBlock);
	////TODO need to know if its needed
		//.provider('myCSRF',[function(){
		//	var headerName = 'X-CSRFToken';
		//	var cookieName = 'csrftoken';
		//	var allowedMethods = ['GET'];
		//
		//	this.setHeaderName = function(n) {
		//		headerName = n;
		//	}
		//	this.setCookieName = function(n) {
		//		cookieName = n;
		//	}
		//	this.setAllowedMethods = function(n) {
		//		allowedMethods = n;
		//	}
		//	this.$get = ['$cookies', function($cookies){
		//		return {
		//			'request': function(config) {
		//				if(allowedMethods.indexOf(config.method) === -1) {
		//					// do something on success
		//					config.headers[headerName] = $cookies[cookieName];
		//				}
		//				return config;
		//			}
		//		}
		//	}];
		//}]).config(function($httpProvider) {
		//$httpProvider.interceptors.push('myCSRF');
	//});



	configure.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider'];

	function configure($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {

		$locationProvider.hashPrefix('!');



		// This is required for Browser Sync to work poperly
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
		$httpProvider.defaults.withCredentials = true;



		$urlRouterProvider
			.otherwise('/login');

	}

	runBlock.$inject = ['$rootScope','$http', '$cookies','$state'];

	function runBlock($rootScope,$http, $cookies,$state) {
		'use strict';
		$http.defaults.headers.post['X-CSRFToken'] = $cookies.csrftoken;

        //$rootScope.ip ="http://localhost:8080";
        $rootScope.ip ="";
        //auth stuff
        $rootScope.authenticated = false;
        $rootScope.authData = {};

		console.log('AngularJS run() function...');

        $rootScope.$on("$stateChangeStart", function(event, toState, toParams, fromState, fromParams){
            //if (toState.authenticate && !AuthService.isAuthenticated()){
            //handle if not auth. send to login
            if (toState.authenticate && !$rootScope.authenticated){
                //TODO add a service for auth
                // User isn’t authenticated
                console.log("not auth go to login");
                $state.transitionTo("home.login");
                event.preventDefault();
            }
            else {
                console.log("Logged in");
            }
        });

	}


})();
