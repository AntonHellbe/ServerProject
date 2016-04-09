/**
 * Created by sebadmin on 2016-04-08.
 */
//Init the hole Angular project connects the controller, services, and injects
//needed libs.
(function(angular) {
    angular.module("myApp.controllers", []);
    angular.module("myApp.services", []);
    angular.module("myApp", ["ngResource", "myApp.controllers", "myApp.services"]);
}(angular));