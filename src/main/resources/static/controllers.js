/**
 * Created by sebadmin on 2016-04-08.
 */
/**
 * Controller for the Angular project
 */
(function (angular) {
    var AppController = function ($scope, $http, $q, User, Time, Pi, Android) {

        //on init all the items are fetched from server
        //with a promise onsuccess update list
        //otherwise shows error
        User.query().$promise.then(
            function (success) {
                console.log("Success");
                $scope.users = success ? success : [];
            },
            function (error) {
                alert("Failed " + JSON.stringify(error));
            },
            function (update) {
                alert("Got notification" + JSON.stringify(update));
            });

        /**
         * Add a new item
         * @param description the description in the new item
         */
        $scope.addUser = function (inputs) {
            console.log("add user");
            var newUser = new User({
                firstName: inputs.firstName,
                lastName: inputs.lastName,
                rfid: inputs.rfid
            });
            //server call for add
            User.save(newUser)
                .$promise.then(
                function (success) {
                    console.log("Successfuly Added");
                    $scope.users.push(success);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });

            //clear fields
            $scope.newItem = "";
        };

        /**
         * Update a item
         * @param item the item that should be updated
         */
        $scope.updateUser = function (user) {

            //server call for update
            User.update(user)
                .$promise.then(
                function (success) {
                    console.log("Successfuly updated");
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        };

        /**
         * Delete a item
         * @param item the item that should be deleted
         */
        $scope.deleteUser = function (user) {
            User.remove(user)
                .$promise.then(
                function (success) {
                    console.log("Successfuly Removed User");
                    $scope.users.splice($scope.users.indexOf(user), 1);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        };

        /**
         * Get 1 item
         * @param id the id of the item
         */
        $scope.getUser = function (getUser) {
            console.log("get item on id: " + getUser.id);
            var id = getUser.id;
            User.get({id:id})
                .$promise.then(
                function (success) {
                    console.log("Successfuly Removed User");
                    $scope.oldUser = success;
                },
                function (error) {
                    console.log("error getting User");
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        };


        $scope.getStamps = function () {
            var rfid = $scope.oldUser.rfid;
			var id = $scope.oldUser.id;
            console.log("get all stamps for user " + rfid.id);
            Time.query({id: id}).$promise.then(
                function (success) {
                    console.log("Success");
                    $scope.oldUser.stamps = success ? success : [];
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });


        };

        $scope.addStamp = function () {
            var id = $scope.oldUser.id;
            console.log("get all stamps for user " + id);
            Time.save({id: id}).$promise.then(
                function (success) {
                    console.log("Success");

                    $scope.oldUser.stamps.push(success);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        };

        $scope.deleteStamp = function (stampId, idx) {
            console.log("remove id " + stampId);
            //$scope.oldUser.stamps.splice(stampId,stampId);
            var userid = $scope.oldUser.id;
            // $http.delete("localhost:8080/"+userid,stampId);
            Time.remove({id: userid}, {stampId: stampId})
                .$promise.then(
                function (success) {
                    console.log("Successfuly Removed Stamp");
                    $scope.oldUser.stamps.splice(idx, 1);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        };

        $scope.getPiStamp = function (piUser) {
            console.log("do Pi Stamp for id " + piUser.rfid.id);

            $http.get('http://localhost:8080/pi/' + piUser.rfid.id)
                .then(function successCallback(success) {
                    console.log("Successfuly sent to pi service!");
                    $scope.piUser.answear = success.data;
                    $scope.piUser.firstName = success.data.firstName;
                    $scope.piUser.lastName = success.data.lastName;
                    $scope.piUser.checkIn = success.data.checkIn;
                    $scope.piUser.date = success.data.date;

//                    Time.query({id: piUser.rfid}).$promise.then(
//                        function (success) {
//                            console.log("Success");
//                            $scope.piUser.stamps = success ? success : [];
//                        },
//                        function (error) {
//                            alert("Failed " + JSON.stringify(error));
//                        },
//                        function (update) {
//                            alert("Got notification" + JSON.stringify(update));
//                        });


                }, function errorCallback(error) {
                    alert("Failed " + JSON.stringify(error));
                });
        }


        $scope.getAndyAll = function (user) {

            console.log("param " + user.rfid);
            $http.post('http://localhost:8080/android/all/', {id: user.rfid}).then(function (response) {
                $scope.andyUser.stamps = response.data;

            }, function errorCallback(error) {
                alert("Failed " + JSON.stringify(error));
            });

        };
        $scope.getAndyBetween = function (user) {
            console.log("param " + user.rfid);

            user.fromdt = new Date(user.from);
            user.todt = new Date(user.to);

            console.log("param " + user.from);
            console.log("param " + user.to);


            $http.post('http://localhost:8080/android/between/', {
                id: user.rfid,
                from: user.fromdt.getTime(),
                to: user.todt.getTime()
            }).then(function (response) {
                $scope.andyUser.stamps = response.data;
                $scope.andyUser.resp = response;

            }, function errorCallback(error) {
                alert("Failed " + JSON.stringify(error));
            });
        };


        //end of controller
    };

    //setup the controller injects needed Libs
    AppController.$inject = ['$scope', '$http', '$q', 'User', 'Time', 'Pi', 'Android'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));


