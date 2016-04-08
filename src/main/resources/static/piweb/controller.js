/**
 * Created by seb on 2016-04-06.
 */


angular.module('app.controllers', []).controller('AppCtrl', function ($scope, $q, $http, $log, UserService) {


    //$scope.allusers = UserService.query();

    UserService.query().$promise.then(
        function (success) {
            console.log("Success on get all");
            $scope.allusers = success;
        },
        function (error) {
            alert("Failed " + JSON.stringify(error));
        },
        function (update) {
            alert("Got notification" + JSON.stringify(update));
        });


    $scope.getUser = function () {
        /** @namespace $scope.currentUser */
        var id = {id:$scope.currentUser.id};
        //$scope.currentUser = UserService.get(id);

        UserService.get(id)
            .$promise.then(
            function (success) {
                console.log("Successfuly got");
                // $scope.items.push(success);
                $scope.currentUser = success;
            },
            function (error) {
                alert("Failed " + JSON.stringify(error));
            },
            function (update) {
                alert("Got notification" + JSON.stringify(update));
            });


    };

    $scope.addTimestamp = function () {
        console.log("Add time stamp");
        var id = $scope.currentUser.id;

        $http.get('http://localhost:8080/pi/' + id).then(function successCallback(success){
            $scope.resultOfTimestamp = success.data;
        },function errorCallback(error){
            alert("Failed " + JSON.stringify(error));
        });

        //$http.get('http://localhost:8080/pi/' + id).then(function (response) {
        //    $scope.resultOfTimestamp = response.data;
        //    // $scope.allusers = UserService.query();
        //});
    };

    $scope.getStamps = function () {
        
        var id = $scope.currentUser.id;
        console.log("get user stamps with id: "+id );
        $http.get('http://localhost:8080/time/'+id).then(function (success) {
            $scope.currentUser.stamps = success.data;

        },function(error){
            alert("Failed " + JSON.stringify(error));
        });
        
        
    };


//end of controller
});
