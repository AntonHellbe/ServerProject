(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name app.controller:timestampsCtrl
     * @description
     * # timestampsCtrl
     * Controller of the app
     */

    angular
        .module('timestamps')
        .controller('TimestampsCtrl', Timestamps);

    Timestamps.$inject = ['AccountsService', 'TimestampsService', '$mdDialog', '$log'];

    /*
     * recommend
     * Using function declarations
     * and bindable members up top.
     */

    function Timestamps(AccountsService, TimestampsService, $mdDialog,$log) {
        /*jshint validthis: true */
        var vm = this;


        /**
         * default get all users
         */
        vm.getAll = function () {
            AccountsService.query().$promise.then(
                function (success) {
                    console.log("Success");
                    vm.users = success ? success : [];
                    //todo get all users stamps
                    //vm.users.forEach(vm.getStamps)


                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                },
                function (update) {
                    alert("Got notification" + JSON.stringify(update));
                }
            );
        };

        //vm.users = [];
        vm.getAll();

        vm.getStamps = function (user, shouldUpdate) {

            if (user.showBody == undefined) {
                user.showBody = false;
            }
            if (user.stamps == undefined || shouldUpdate == true) {

                if (user.rfidKey != null) {

                    TimestampsService.get({id: user.id}).$promise.then(
                        function (success) {
                            console.log("Success getting times");
                            //stamplist.push(success);
                            user.stamps = success;
                        },
                        function (error) {
                            alert("Failed " + JSON.stringify(error));
                        }
                    );
                }
                if (shouldUpdate == true)
                    return user.showBody;
            }
            return !user.showBody;
        };

        vm.doPrimaryAction = function (ev, user, stamp) {
            console.log("calling event");

            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: '/app/modules/timestamps/dialog1.tmpl.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    locals: {user: user, stamp: stamp}
                })
                .then(function (updateStamp) {
                        console.log("answeard " + updateStamp);
                        if (updateStamp == undefined) {
                            console.log("del " + stamp.id);
                            //del
                            TimestampsService.remove({id: user.id, stampId: stamp.id}).$promise.then(
                                function (success) {
                                    console.log("Success delete");

                                },
                                function (error) {
                                    alert("Failed " + JSON.stringify(error));
                                }
                            );
                        }
                        else {
                            //update
                            console.log("updated " + updateStamp.date);
                            TimestampsService.update({id: user.id, stampId: stamp.id}, updateStamp).$promise.then(
                                function (success) {
                                    console.log("Success getting times");
                                    $log.info("success " + JSON.stringify(success));
                                    //stamp = success;
                                    var idx =user.stamps.indexOf(stamp);
                                    user.stamps[idx] = success;
                                },
                                function (error) {
                                    alert("Failed " + JSON.stringify(error));
                                }
                            );
                        }
                    }, function () {
                        vm.status = 'You cancelled the dialog.';
                        console.log("You cancelled the dialog.");
                    }
                );
        };

        vm.addNowStamp = function (user) {
            console.log("add NOW timestamp");
            TimestampsService.save({id: user.id}).$promise.then(
                function (success) {
                    console.log("Success update time");
                    vm.status = "update" + JSON.stringify(success);
                    user.stamps.push(success);
                },
                function (error) {
                    alert("Failed " + JSON.stringify(error));
                }
            );
        };

        vm.addStamp = function (ev, user) {
            $mdDialog.show({
                    controller: AddTimeController,
                    templateUrl: '/app/modules/timestamps/addTime.tmpl.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    locals: {user: user}
                })
                .then(function (newStamp) {
                        console.log("ADDSTAMP IN CTRL " + JSON.stringify(newStamp));
                        vm.status = 'You said the information was "' + newStamp + '".';
                        //update
                        console.log("ADDSTAMP " + newStamp.date);
                        TimestampsService.save({id: user.id, stampId: true}, newStamp).$promise.then(
                            function (success) {
                                $log.info("Success Adding time");
                                vm.status = "ADD" + JSON.stringify(success);
                                user.stamps.push(success);
                            },
                            function (error) {
                                alert("Failed " + JSON.stringify(error));
                            }
                        );
                    }, function () {
                        vm.status = 'You cancelled the dialog.';
                        console.log("You cancelled the dialog.");
                    }
                );
        }
    }


    function AddTimeController($scope, $mdDialog, user, $log) {
        $scope.user = user;
        $scope.newStamp = {};
        $scope.newStamp.date = new Date();
        $scope.newStamp.checkIn = false;
        $scope.newStamp.rfidkey= $scope.user.rfidKey;
        $scope.dpDate = new Date();

        ////console.log(JSON.stringify(user));
        //$scope.hide = function () {
        //    console.log("calling hide");
        //    $mdDialog.hide();
        //};
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.addStamp = function (newStamp) {
            $log.info("ADDSTAMP called with " + JSON.stringify($scope.newStamp));
            $mdDialog.hide($scope.newStamp);
        };

        $scope.updateDate = function (datep) {
            var dp = $scope.dpDate;
            var current = $scope.newStamp.date;
            current.setMonth(dp.getMonth());
            current.setDate(dp.getDate());
        };

    }

    function DialogController($scope, $mdDialog, user, stamp) {
        $scope.user = user;
        $scope.stamp = angular.copy(stamp);
        $scope.userDate = new Date(stamp.date);
        $scope.userTime = new Date(stamp.date);

        $scope.updateDate = function (type) {
            if (type == 'time') {
                if ($scope.userTime != undefined) {
                    $scope.stamp.date = $scope.userTime;
                }
            }
            if (type == 'date') {
                if ($scope.userDate != undefined) {
                    var tempDate = new Date($scope.stamp.date);
                    $scope.userDate.setHours(tempDate.getHours());
                    $scope.userDate.setMinutes(tempDate.getMinutes());
                    $scope.userDate.setSeconds(tempDate.getSeconds());
                    $scope.userDate.setMilliseconds(tempDate.getMilliseconds());
                    $scope.stamp.date = $scope.userDate;
                }
            }
        };

        //console.log(JSON.stringify(user));
        $scope.hide = function () {
            $mdDialog.hide();
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.answer = function (updateStamp) {
            var temp = new Date(updateStamp.date);
            updateStamp.date = temp.getTime();
            console.log("answer called with " + JSON.stringify(updateStamp));
            stamp = updateStamp;
            $mdDialog.hide(updateStamp);
        };
    }

})();
