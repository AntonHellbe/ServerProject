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
        var id = $scope.currentUser.rfid;
        var test = $scope.currentUser.rfid;
        console.log("sending rfid: " + JSON.stringify(test));

        $http.post('http://localhost:8080/android/all/',test).then(function (response) {
            $scope.allStamps = response;
        });

        //$scope.currentUser = AndroidService.get({id:id});
    };
    
    $scope.getBetween=function(){
        
        var id = $scope.currentUser.id;
        $scope.currentUser = AndroidService.get({id:id});
//         Calendar from= new GregorianCalendar(2014, 1, 06,10,00);
//	    Calendar to= new GregorianCalendar(2014, 1, 06,16,00);
//         var d = new Date(year, month, day, hours, minutes, seconds, milliseconds); 
        var from = new Date(2014,1,06,10,00).getTime();
        var to = new Date(2014,1,06,11,00).getTime();
        var betweenData ={id,from,to};
        $http.post('http://localhost:8080/android/between/',betweenData).then(function (response) {
            $scope.betweenStamps = response;
        });
        
        
    }

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
