/**
 * Created by seb on 2016-04-06.
 */

angular.module('app.services', []).factory('AndroidService', ['$resource', function ($resource) {

    return $resource(
        'http://localhost:8080/time/:id',
        {id: '@id'},
        {
            update:{
                method: "PUT"
            }
        }
    );

}]);
