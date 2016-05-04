(function () {
    'use strict';

    /**
     * @ngdoc function
     * @name app.controller:piCtrl
     * @description
     * # piCtrl
     * Controller of the app
     */

    angular
        .module('pi')
        .controller('PiCtrl', Pi);

    Pi.$inject = ['$http'];

    /*
     * recommend
     * Using function declarations
     * and bindable members up top.
     */

    function Pi($http) {
        /*jshint validthis: true */
        var vm = this;

        vm.doPiStamp = function (rfid) {

            $http.get('/api/pi/' + rfid).then(function (response) {
                console.log("pi succes");
                vm.piResult = response;

            }, function (errorRes) {
                console.log("error res " + JSON.stringify(errorRes));

            });

        };

    }

})();
