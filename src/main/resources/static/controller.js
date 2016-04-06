/**
 * Created by seb on 2016-04-06.
 */


angular.module('app.controllers',[]).controller('AppCtrl',function($scope,$http,UserService){

    var users = [{name:"hello"},{name:"lalal"}];
    //$scope.allusers = users;
    $http.get('http://localhost:8080/api/time').then(function(response){
        $scope.allusers = response;
    })
//    $scope.allusers = UserService.query();
//    $scope.getUser = function () {
//
//    }

    //$scope.userTimes
});
