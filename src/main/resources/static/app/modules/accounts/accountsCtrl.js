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

        //vm.authorities = [{authority: "ROLE_USER"}, {authority: "ROLE_ADMIN"}];
        //roles list
        vm.items = [{authority: "ROLE_USER"}, {authority: "ROLE_ADMIN"}, {authority:"ROLE_PI"}];

        /**
         * default get all users
         */
        vm.getAll = function () {
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
        };

        vm.getAll();

        vm.cancelAdd = function () {
            console.log("calling cancel add");
            vm.newUser = {};
            vm.showAddUser = !vm.showAddUser;
        };

        //add a new user
        vm.addUser = function (newUser) {
            console.log("add user" + JSON.stringify(newUser));

            if (newUser.authorities != undefined) {
                var size = newUser.authorities.length;
                var auths = newUser.authorities;
                var templist = [];
                for (var i = 0; i < size; i++) {
                    console.log("auth " + JSON.stringify(auths[i]));
                    templist.push({authority: auths[i].authority});
                }
                newUser.authorities = templist;
                console.log("add user" + JSON.stringify(newUser));
            }

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


        //update user
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

        /**
         * Delete user
         * @param user the user to be removed
         */
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

        vm.toggle = function (item, userList) {
            if (userList == undefined) {
                userList = [];
            }
            var idx = userList.indexOf(item);
            if (idx > -1) {
                userList.splice(idx, 1);
            }
            else {
                userList.push(item);
            }
            return userList;

        };
        vm.exists = function (item, userList) {
            if (userList == undefined) {
                userList = [];
            }
            var size = userList.length;
            for (var i = 0; i < size; i++) {
                if (item.authority == userList[i].authority) {
                    return true;
                }
            }
            return false;
            //return list.indexOf(item) > -1;
        };
        vm.isIndeterminate = function (userList) {
            if (userList == undefined) {
                userList = [];
            }
            return (userList.length !== 0 &&
            userList.length !== vm.items.length);
        };
        vm.isChecked = function (userList) {
            if (userList == undefined) {
                userList = [];
            }
            return userList.length === vm.items.length;
        };
        vm.toggleAll = function (userList) {
            if (userList == undefined) {
                userList = [];
            }
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
