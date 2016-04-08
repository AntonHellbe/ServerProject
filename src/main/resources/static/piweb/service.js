/**
 * Created by seb on 2016-04-06.
 */

angular.module('app.services', []).factory('UserService', ['$resource', function ($resource) {

    return $resource(
        'http://localhost:8080/users/:id',
        {id: '@id'},
        {
            update:{
                method: "PUT"
            }
        }
    );

}]);
