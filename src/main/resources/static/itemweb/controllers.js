/**
 * Created by sebadmin on 2016-04-08.
 */
/**
 * Controller for the Angular project
 */
(function (angular) {
    var AppController = function ($scope, $q, Item) {

        //on init all the items are fetched from server
        //with a promise onsuccess update list
        //otherwise shows error
        Item.query().$promise.then(
            function (success) {
                console.log("Success");
                $scope.items = success ? success : [];
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
        $scope.addItem = function (description) {
            var newItem = new Item({
                description: description,
                checked: false
            });
            //server call for add
            Item.save(newItem)
                .$promise.then(
                function (success) {
                    console.log("Successfuly Added");
                    $scope.items.push(success);
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
        $scope.updateItem = function (item) {

            //server call for update
            Item.update(item)
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
        $scope.deleteItem = function (item) {
            Item.remove(item)
                .$promise.then(
                function (success) {
                    console.log("Successfuly Removed item");
                    $scope.items.splice($scope.items.indexOf(item), 1);
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
        $scope.getItem = function (id) {
            console.log("get item on id: " + id)
            Item.get({id})
                .$promise.then(
                function (success) {
                    console.log("Successfuly Removed item");
                    $scope.oldItem = success;
                },
                function (error) {
                    console.log("error getting item");
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        }
        //end of controller
    };

    //setup the controller injects needed Libs
    AppController.$inject = ['$scope', '$q', 'Item'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));