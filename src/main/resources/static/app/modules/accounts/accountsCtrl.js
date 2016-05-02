(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name app.controller:accountsCtrl
     * @description
     * # accountsCtrl
     * Controller of the app
     */

    angular
        .module('accounts')
        .controller('AccountsCtrl', Accounts);

    Accounts.$inject = ['AccountsService', '$q'];

    /*
     * recommend
     * Using function declarations
     * and bindable members up top.
     */

    function Accounts(AccountsService, $q) {
        /*jshint validthis: true */
        var vm = this;

        vm.authorities = [{authority: "ROLE_USER"}, {authority: "ROLE_ADMIN"}];

        //vm.allusers="all users";

        vm.addUser = function (newUser) {


            //var aur = {authority: newUser.authorities.authority};
            //newUser.authorities = [aur];
            console.log("add user" + JSON.stringify(newUser));

            AccountsService.save(newUser)
                .$promise.then(
                function (success) {
                    console.log("Successfuly Added");
                    vm.allusers.push(success);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        };

        vm.updateUser = function (user) {

            AccountsService.update(user)
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

        vm.deleteUser = function (user) {
            AccountsService.remove(user)
                .$promise.then(
                function (success) {
                    console.log("Successfuly Removed User");
                    vm.allusers.splice(vm.allusers.indexOf(user), 1);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                });
        };

        //default get all users
        AccountsService.query().$promise.then(
            function (success) {
                console.log("Success");
                vm.allusers = success ? success : [];
            },
            function (error) {
                alert("Failed " + JSON.stringify(error));
            },
            function (update) {
                alert("Got notification" + JSON.stringify(update));
            }
        );

        //vm.items = [1,2,3,4,5];
        vm.items = [{authority: "ROLE_USER"}, {authority: "ROLE_ADMIN"}];

        vm.toggle = function (item, list) {
            var idx = list.indexOf(item);
            if (idx > -1) {
                list.splice(idx, 1);
            }
            else {
                list.push(item);
            }

        };
        vm.exists = function (item, list) {
            var size = list.length;
            for (var i=0; i<size; i++) {
                if(item.authority == list[i].authority) {
                    return true;
                }
            }
            return false;
            //return list.indexOf(item) > -1;
        };
        vm.isIndeterminate = function (userList) {
            return (userList.length !== 0 &&
            userList.length !== vm.items.length);
        };
        vm.isChecked = function (userList) {
            return userList.length === vm.items.length;
        };
        vm.toggleAll = function (userList) {
            console.log('toogle all');
            if (userList.length === vm.items.length) {
                console.log('clear list');
                userList = [];
            } else if (userList.length === 0 || userList.length > 0) {
                userList = vm.items.slice(0);
                //userList = vm.items;
                console.log('add all');
            }
            return userList;
        };
    }
})();
