/**
 * Created by sebadmin on 2016-04-08.
 */
/**
 * Controller for the Angular project
 */
(function (angular) {
    var AppController = function ($scope, $q, User, Time) {

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
        $scope.addUser= function (inputs) {
            console.log("add user");
            var newUser = new User({
                firstName : inputs.firstName,
                lastName : inputs.lastName,
                rfid: {id:inputs.rfid}
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
            console.log("get item on id: " + getUser.rfid.id);
			var id = getUser.rfid;
            User.get(id)
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
		
		
		$scope.getStamps= function()
		{
            var rfid = $scope.oldUser.rfid;
			console.log("get all stamps for user "+rfid.id);
            Time.query({id: rfid.id}).$promise.then(
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
			
			
		}
		
		$scope.addStamp= function()
		{
			var rfid = $scope.oldUser.rfid;
			console.log("get all stamps for user "+rfid.id);
            Time.save({id: rfid.id}).$promise.then(
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
		}
		
		$scope.deleteStamp=function(stampId){
			console.log("remove id "+stampId);
            //$scope.oldUser.stamps.splice(stampId,stampId);
            var userid = $scope.oldUser.rfid.id;
           // $http.delete("localhost:8080/"+userid,stampId);
			Time.remove({id: userid},{stampId:stampId})
                .$promise.then(
                function (success) {
                    console.log("Successfuly Removed User");
                    $scope.oldUser.stamps.splice(stampId,stampId);
                    //$scope.oldUser.stamps.splice($scope.users.indexOf(stampId), 1);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
		};
		
		
        //end of controller
    };

    //setup the controller injects needed Libs
    AppController.$inject = ['$scope', '$q', 'User', 'Time'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));


