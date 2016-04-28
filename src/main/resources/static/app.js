angular.module('hello', ['ngRoute']).config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'home',
        controllerAs: 'controller'
    }).when('/login', {
        templateUrl: 'login.html',
        controller: 'navigation',
        controllerAs: 'controller'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',

    function ($rootScope, $http, $location, $route) {

        var self = this;

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            $http.get('api/account', {
                headers: headers
            }).then(function (response) {
                if (response.data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }, function () {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        }

        authenticate();

        self.credentials = {};
        self.login = function () {
            authenticate(self.credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded")
                    $location.path("/");
                    self.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed")
                    $location.path("/login");
                    self.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        self.logout = function () {
            $http.post('logout', {}).finally(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            });
        }

    }).controller('home', function ($http) {
    var self = this;

    $http.get('/resource/').success(function(data) {
        self.greeting = data;
    });

    $http.get('/api/users/').success(function(data) {
        self.users = data;
    });

    self.sendData= function(){
	    // $http.delete("localhost:8080/"+userid,stampId);

	    //$http.post('http://localhost:8080/android/all/', {id: user.rfid}).then(function (response) {

	    $http.get('/api/users/5720bb7ecbcb3e71d7e6f715').success(function(data) {
		    data.firstName = "Lolo";
		    $http.put('/api/users/5720bb7ecbcb3e71d7e6f715',data).success(function(data) {
			    self.dataU = data;
			    console.log("got answear "+JSON.stringify(data));

		    });

	    });
    };

    //$http.get('resource').then(function (response) {
    //    $http({
    //        url: '',
    //        method: "GET",
    //        headers: {
    //            'X-Auth-Token' : response.data.token
    //        }
    //    }).then(function (response) {
    //        self.greeting = response.data;
    //    });
    //});
    //$http.get('http://localhost:9000').then(function(response) {
    //    self.greeting = response.data;
    //});
});