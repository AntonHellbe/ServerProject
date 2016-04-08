/**
 * Created by sebadmin on 2016-04-08.
 */
(function (angular) {
    var AppController = function ($scope, $q, Item) {


//        Item.query(function(response) {
//            $scope.items = response ? response : [];
//        });

        Item.query().$promise.then(
            function (success) {
                console.log("Success");
                $scope.items = success;
            },
            function (error) {
                alert("Failed " + JSON.stringify(error));
            },
            function (update) {
                alert("Got notification" + JSON.stringify(update));
            });

        $scope.addItem = function (description) {
//			
            var newItem=new Item({
                    description: description,
                    checked: false
                });
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

            //    new Item({
            //    description: description,
            //    checked: false
            //}).$save(function(item) {
            //    $scope.items.push(item);
            //});

            $scope.newItem = "";
        };

        $scope.updateItem = function (item) {
            ///item.$update();
            Item.update(item)
            .$promise.then(
                function (success) {
                    console.log("Successfuly updated");
                   // $scope.items.push(success);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        };

        $scope.deleteItem = function (item) {
            //item.$remove(function () {
            //    $scope.items.splice($scope.items.indexOf(item), 1);
            //});

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
		$scope.getItem = function(id){
			console.log("get item on id: "+id)
			 Item.get({id:id})
                .$promise.then(
                function (success) {
                    console.log("Successfuly Removed item");
                    $scope.oldItem = success;
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
		}
		//end of controller
    };

    AppController.$inject = ['$scope', '$q', 'Item'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));