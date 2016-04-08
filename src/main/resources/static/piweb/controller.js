/**
 * Created by seb on 2016-04-06.
 */


angular.module('app.controllers', []).controller('AppCtrl', function ($scope, $http, $log, UserService) {

    var users = [{name: "hello"}, {name: "lalal"}];
    //$scope.allusers = users;
    //$http.get('http://localhost:8080/time').then(function (response) {
    //    $scope.httpGet = response;
    //});
    $scope.allusers = UserService.query();
//    $scope.currentUser={};
    $scope.getUser = function () {
        /** @namespace $scope.currentUser */
        var id = $scope.currentUser.id;
        console.log("hello");
        $scope.currentUser = UserService.get({id: id});
    };

    $scope.getUser = function () {
        /** @namespace $scope.currentUser */
        var id = $scope.currentUser.id;
        console.log("get user with id " + id);
        $scope.currentUser = UserService.get({id: id});

    };


    $scope.addTimestamp = function () {
        console.log("Add time stamp");
        var id = $scope.currentUser.id;
        $http.get('http://localhost:8080/pi/' + id).then(function (response) {
            $scope.resultOfTimestamp = response.data;
            // $scope.allusers = UserService.query();
        });
    };

    $scope.getStamps = function () {
        
        var id = $scope.currentUser.id;
        console.log("get user stamps with id: "+id );
        $http.get('http://localhost:8080/time/1').then(function (response) {
            $scope.currentUser.stamps = response.data;
            // $scope.allusers = UserService.query();
        });
        
        
    };


//end of controller
});
