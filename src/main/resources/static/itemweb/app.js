/**
 * Created by sebadmin on 2016-04-08.
 */
(function(angular) {
    angular.module("myApp.controllers", []);
    angular.module("myApp.services", []);
    angular.module("myApp", ["ngResource", "myApp.controllers", "myApp.services"]);
}(angular));