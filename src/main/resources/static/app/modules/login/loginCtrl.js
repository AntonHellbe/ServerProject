(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name app.controller:loginCtrl
     * @description
     * # loginCtrl
     * Controller of the app
     */

    angular
        .module('login')
        .controller('LoginCtrl', Login);

    Login.$inject = ['$rootScope', '$http', '$cookies', 'LoginService', '$state'];

    /*
     * recommend
     * Using function declarations
     * and bindable members up top.
     */

    function Login($rootScope, $http, $cookies, LoginService, $state) {

        //TODO change to correct ip
        var ip = $rootScope.ip;

        /*jshint validthis: true */
        var vm = this;

        vm.credentials = {};
        vm.loginData = {};

        //todo move own service
        //do auth service
        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            console.log("Do login call");

            $http.get(ip + '/api/account', {
                headers: headers
            }).then(function (response) {
                if (response.data.name) {
                    console.log("auth succes");
                    $rootScope.authenticated = true;
                    $rootScope.authData = response.data;

                    //vm.oldToken = "new cookie>"+JSON.stringify($cookies.getAll());
                } else {
                    console.log("auth FAIL");
                    $rootScope.authenticated = false;
                    vm.loginData = {};
                }
                console.log("respo " + JSON.stringify(response));
                vm.loginData = response.data;

                callback && callback($rootScope.authenticated);
            }, function (errorRes) {
                console.log("error res " + JSON.stringify(errorRes));
                vm.loginData = {};
                vm.loginData = {};
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        };


        //vm.credentials = {"username": "user", "password": "pass"};
        vm.credentials = {};

        //login button
        vm.login = function () {
            //TODO use service instead of http
            console.log("call login");
            console.log("creds " + JSON.stringify(vm.credentials));

            authenticate(vm.credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded")

                    $http.get('token').then(function (response) {
                        console.log("token> " + response.data.token);
                        $rootScope.authToken = response.data.token;
                        //todo for testing
                        vm.oldToken =$rootScope.authToken;
                        //$http({
                        //    url: 'http://localhost:44344/api/users',
                        //    method: 'GET',
                        //    headers: {
                        //        'X-Auth-Token': response.data.token
                        //    }
                        //}).then(function (response) {
                        //
                        //});
                    });

                    vm.error = false;
                    $rootScope.authenticated = true;
                    $state.go("home.dashboard");
                } else {
                    console.log("Login failed")
                    //$location.path("/login");
                    vm.error = true;
                    $rootScope.authenticated = false;
                }
            });
        };

        //todo remove is just extra

        //logout button
        vm.logout = function () {
            //TODO use service instead of http
            $http.post(ip + '/logout', {}).finally(function () {
                //$http.post(ip+'/api/account', {}).finally(function () {
                $rootScope.authenticated = false;
                console.log("LOGGED out");
                vm.users = {};
                vm.loginData = {};
            });

            $state.go('home.login', {}, {reload: true});
        }

        vm.getAll = function () {
            $http.get("/api/users/").then(function (response) {
                vm.users = response;
            }, function (errorRes) {
                console.log("error res " + JSON.stringify(errorRes));

            });
        };

        vm.getToken = function () {
            $http.get('token').then(function (response) {
                console.log("token> " + response.data.token);
                $http({
                    url: 'http://localhost:44344/api/users',
                    method: 'GET',
                    headers: {
                        'X-Auth-Token': response.data.token
                    }
                }).then(function (response) {
                    vm.myToken = response.data;
                });
            });
        };

        vm.getHack = function () {
            $http({
                url: 'http://localhost:44344/api/users',
                method: 'GET',
                headers: {
                    'X-Auth-Token': vm.oldToken
                }
            }).then(function (response) {
                vm.myToken = response.data;
            });
        };

        vm.clearAll= function(){
            vm.myToken ="";
            vm.users={};
        }


    }

})();
