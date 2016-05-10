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

    Timestamps.$inject = ['AccountsService', 'TimestampsService', '$mdDialog', '$mdMedia'];

    /*
     * recommend
     * Using function declarations
     * and bindable members up top.
     */

    function Timestamps(AccountsService, TimestampsService, $mdDialog, $mdMedia) {
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
                    vm.users.forEach(vm.getStamps)


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

        vm.getStamps = function (user) {
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
            return !user.showBody;
        };

        vm.doPrimaryAction = function (ev, user,stamp) {
            console.log("calling event");

            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: '/app/modules/timestamps/dialog1.tmpl.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    locals: {user: user,stamp :stamp}
                })
                .then(function (updateStamp) {
                    console.log("answeard " + updateStamp);
                    vm.status = 'You said the information was "' + updateStamp + '".';
                    if(updateStamp == undefined) {
                        console.log("del " + stamp.id);
                        //del
                        TimestampsService.remove({id: user.id,stampId:stamp.id}).$promise.then(
                            function (success) {
                                console.log("Success delete");
                                vm.status = "delete " + JSON.stringify(success);
                            },
                            function (error) {
                                alert("Failed " + JSON.stringify(error));
                            }
                        );
                    }
                    else {
                        //update
                        TimestampsService.update({id: user.id,stampId:stamp.id},updateStamp).$promise.then(
                            function (success) {
                                console.log("Success getting times");
                                vm.status = "update" + JSON.stringify(success);
                                stamp = success;
                            },
                            function (error) {
                                alert("Failed " + JSON.stringify(error));
                            }
                        );
                    }
                }, function () {
                    vm.status = 'You cancelled the dialog.';
                    console.log("You cancelled the dialog.");
                });


            //$mdDialog.show(
            //    $mdDialog.alert()
            //        .title('Primary Action')
            //        .textContent('Primary actions can be used for one click actions')
            //        .ariaLabel('Primary click demo')
            //        .ok('Awesome!')
            //        .targetEvent(event)
            //);
        };
    }

    function DialogController($scope, $mdDialog, user,stamp) {
        $scope.user = user;
        $scope.stamp= angular.copy(stamp);
        $scope.userDate= new Date(stamp.date);
        $scope.userTime= new Date(stamp.date);

        $scope.updateDate = function (type) {
            if(type == 'time'){
                if($scope.userTime != undefined) {
                    $scope.stamp.date = $scope.userTime;
                }
            }
            if(type == 'date'){
                if($scope.userDate!= undefined) {
                    var tempDate = new Date($scope.stamp.date);
                    tempDate.setDate($scope.userDate.getDate());
                    tempDate.setYear($scope.userDate.getYear());
                    tempDate.setMonth($scope.userDate.getMonth());
                    $scope.stamp.date = tempDate.getTime();
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
            console.log("answer called with "+JSON.stringify(updateStamp));
            stamp = updateStamp;
            $mdDialog.hide(updateStamp);
        };
    }

})();
