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


    configure.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider'];

    function configure($stateProvider,$urlRouterProvider, $locationProvider, $httpProvider) {

        $locationProvider.hashPrefix('!');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.withCredentials = true;


        $urlRouterProvider
            .otherwise('/login');

    }

    runBlock.$inject = ['$state','$rootScope', '$http'];

    //You have to have $state before $rootScope otherwise $on wont work!!!
    function runBlock($state,$rootScope) {
        'use strict';

        //$rootScope.ip ="http://localhost:8080";
        $rootScope.ip = "";
        //auth stuff
        $rootScope.authenticated = false;
        $rootScope.authData = {};
        $rootScope.authToken = {};

        console.log('AngularJS run() function...');

        //$rootScope.$on("$stateChangeStart",
        //    function(event, toState, toParams, fromState, fromParams) {
        //        if (toState.needAuth && !$rootScope.authenticated) {
        //            event.preventDefault();
        //            $state.transitionTo("home.login");
        //            //$location.path('/login');
        //
        //        }
        //    });

    }


})();
