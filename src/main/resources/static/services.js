
/**
 * Created by sebadmin on 2016-04-08.
 */
/**
 * Item service for CRUD for the Item REST API
 * here is the server address for API
 */
(function(angular) {
    var ItemFactory = function($resource) {
        return $resource('http://localhost:8080/users/:id', {
            id: '@id'
        }, {
            update: {
                method: "PUT"
            },
            remove: {
                method: "DELETE"
            },
            save:{
                method: "POST"
            }
        });
    };

    ItemFactory.$inject = ['$resource'];
    angular.module("myApp.services").factory("User", ItemFactory);
}(angular));

(function(angular) {
    var ItemFactory = function($resource) {
        return $resource('http://localhost:8080/time/:id/:stampId', {
            id: '@id',stampId:'@stampId'
        }, {
            update: {
                method: "PUT"
            },
            remove: {
                method: "DELETE"
            }
        });
    };

    ItemFactory.$inject = ['$resource'];
    angular.module("myApp.services").factory("Time", ItemFactory);
}(angular));

(function(angular) {
    var ItemFactory = function($resource) {
        return $resource('http://localhost:8080/pi/:id', {
            id: '@id'
        }, {
            
            save:{
                method: "POST"
            },
            doStamp:{
                method: "GET"
            }
            
        });
    };

    ItemFactory.$inject = ['$resource'];
    angular.module("myApp.services").factory("Pi", ItemFactory);
}(angular));