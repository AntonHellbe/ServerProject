/**
 * Created by seb on 2016-04-06.
 */


angular.module('app.controllers', []).controller('AppCtrl', function ($scope, $http, $log, AndroidService) {

    //$scope.getAll = AndroidService.query();

    $scope.getAll = [{name:"hello",id:"1"},{name:"more",id:"2"}];

    $scope.getUser=function() {
        var id = $scope.currentUser.id;
        console.log("get user with id: " + id);
//        $scope.currentUser = "get user with id "+id;
       $scope.currentUser = AndroidService.get({id:id});
    };

    $scope.getAll=function() {
        var id = $scope.currentUser.id;
        console.log("get all with id: " + id);
        var id = $scope.currentUser.id;
        
//        $scope.allStamps = "get all with id "+id;
        //$http.post('http://localhost:8080/android/all',{id:id}).then(function (response) {
        //    $scope.allStamps = response;
        //});

        //$scope.currentUser = AndroidService.get({id:id});
    };

    //
    //
    //app.controller('controller', function ($scope, $http, $routeParams) {
    //    $http.post('url',data.call1($routeParams.id))
    //        .success(function (response) {
    //            $scope.response = response;
    //        })
    //        .error(function (data, status, headers, config) {
    //        });
    //});




    ///old code

//    $scope.allusers = UserService.query();
////    $scope.currentUser={};
//    $scope.getUser = function () {
//        /** @namespace $scope.currentUser */
//        var id = $scope.currentUser.id;
//        console.log("hello");
//        $scope.currentUser = UserService.get({id: id});
//
//    };
//
//    $scope.updateUser = function(){
//        console.log("update id: "+$scope.currentUser.id);
//        /** @namespace $scope.currentUser*/
//        var id = $scope.currentUser.id;
//        var updatedUser= UserService.get({id:id},function(){
//            updatedUser= $scope.currentUser;
//            updatedUser.$update(function(){
//                console.log("updated user id: "+updatedUser.id);
//                $scope.allusers = UserService.query();
//            });
//        });
//    }
//
//
//    $scope.delUser = function () {
//        /** @namespace $scope.dUser */
//        var id = $scope.dUser.id;
//        var gotUser = UserService.remove({id:id},function(){
//            console.log("deleted user " + id);
//            $scope.allusers = UserService.query();
//            console.log("got1 "+JSON.stringify(gotUser));
//            $scope.dUser = gotUser;
//        });
//
//    };
//
//    /** @namespace $scope.aUser */
//    $scope.aUser = new UserService();
//    $scope.addUser= function() {
//		$scope.aUser.samples =[];
//
//        $scope.aUser.$save(function(){
//            console.log("save user");
//            $scope.allusers = UserService.query();
//        });
//
//
//    };
//
//    $scope.addTimestamp= function(){
//       var id = $scope.currentUser.id; $http.get('http://localhost:8080/time/'+id+'/newTime').then(function (response) {
//        $scope.currentUser = response;
//       // $scope.allusers = UserService.query();
//    });
//    }




});
