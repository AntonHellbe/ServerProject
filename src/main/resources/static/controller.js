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

    $scope.updateUser = function(){
        console.log("update id: "+$scope.currentUser.id);
        /** @namespace $scope.currentUser*/
        var id = $scope.currentUser.id;
        var updatedUser= UserService.get({id:id},function(){
            updatedUser= $scope.currentUser;
            updatedUser.$update(function(){
                console.log("updated user id: "+updatedUser.id);
                $scope.allusers = UserService.query();
            });
        });
    }


    $scope.delUser = function () {
        /** @namespace $scope.dUser */
        var id = $scope.dUser.id;
        var gotUser = UserService.remove({id:id},function(){
            console.log("deleted user " + id);
            $scope.allusers = UserService.query();
            console.log("got1 "+JSON.stringify(gotUser));
            $scope.dUser = gotUser;
        });

    };

    /** @namespace $scope.aUser */
    $scope.aUser = new UserService();
    $scope.addUser= function() {
		$scope.aUser.samples =[];

        $scope.aUser.$save(function(){
            console.log("save user");
            $scope.allusers = UserService.query();
        });


    };




});
