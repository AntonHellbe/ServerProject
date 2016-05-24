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

		vm.items = ["ROLE_USER", "ROLE_ADMIN", "ROLE_PI"];
	    vm.allusers =[{"id":"5740c564602918eb09171a17","username":"admin","password":"$2a$10$RBnHNhiyXM5eMYalrJZAEuWxLbSLyrCx5t2Nwj3f9jHAUmHN6dELO","firstName":"Lucifer","lastName":"Morningstar","rfidKey":{"id":"34915AEC","enabled":false},"authorities":[{"authority":"ROLE_USER"},{"authority":"ROLE_PI"},{"authority":"ROLE_ADMIN"}],"accountNonExpired":true,"enabled":true,"accountNonLocked":true,"credentialsNonExpired":true},{"id":"5740c564602918eb09171a18","username":"piUser","password":"$2a$10$pD7vNyY9ksrU4Hzv8EsIXujCcFyUoO2oPS2ZSUKFFvfVsgI4WXPI6","firstName":"Pie","lastName":"Rubarb","rfidKey":null,"authorities":[{"authority":"ROLE_PI"}],"accountNonExpired":true,"enabled":true,"accountNonLocked":true,"credentialsNonExpired":true},{"id":"5740c564602918eb09171a19","username":"user2","password":"$2a$10$Q.y84vj//a3/0VA9syO0g.2eqSY4c3dVNPQrqbuqjAEwjZth6trGq","firstName":"Matt","lastName":"Murdock","rfidKey":{"id":"A448182B","enabled":false},"authorities":[{"authority":"ROLE_USER"}],"accountNonExpired":true,"enabled":true,"accountNonLocked":true,"credentialsNonExpired":true},{"id":"5740c564602918eb09171a1a","username":"user3","password":"$2a$10$dPrfOZBK8cVKU8k9BvtPtucRCVtIAo9pA5iWylgjuEInNANW0OgAm","firstName":"John","lastName":"Snow","rfidKey":{"id":"4B79295","enabled":false},"authorities":[{"authority":"ROLE_USER"}],"accountNonExpired":true,"enabled":true,"accountNonLocked":true,"credentialsNonExpired":true},{"id":"5740c564602918eb09171a1b","username":"user4","password":"$2a$10$BjUR85BQUUdQHjAwM8HnF.GFbXEjYdgXeywwUHmUzGAwT8X1ST6yW","firstName":"Lucas","lastName":"Hood","rfidKey":{"id":"247615E","enabled":false},"authorities":[{"authority":"ROLE_USER"}],"accountNonExpired":true,"enabled":true,"accountNonLocked":true,"credentialsNonExpired":true},{"id":"5740c564602918eb09171a16","username":"user","password":"$2a$10$e.qIGmON/msL.wS7kjVwS.rMW4tDVvINdqsQ4w9xilonXs6/F0bsS","firstName":"Doris","lastName":"Popo","rfidKey":{"id":"C48659EC","enabled":false},"authorities":[{"authority":"ROLE_USER"}],"accountNonExpired":true,"enabled":true,"accountNonLocked":true,"credentialsNonExpired":true}]
		

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
