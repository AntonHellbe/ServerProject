/**
 * Created by sebadmin on 2016-04-08.
 */
(function(angular) {
    var ItemFactory = function($resource) {
        return $resource('http://localhost:8080/items/:id', {
            id: '@id'
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
    angular.module("myApp.services").factory("Item", ItemFactory);
}(angular));