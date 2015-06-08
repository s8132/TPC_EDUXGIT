'use strict';

var subjectsServices = angular.module('edu-admin.subjects.services', []);

subjectsServices.factory('Subjects', ['$resource', function($resource){
    return $resource('/admin/subjects', null, {
        'getAll': {url: '/admin/subjects', method: 'GET', isArray: true},
        'getById': {url: '/admin/subjects/:id', method: 'GET'},
        'save': {url: '/admin/subjects', method: 'POST'},
        'update': {url: '/admin/subjects', method: 'PUT'},
        'delete': {url: '/admin/subjects/:id', method: 'DELETE'},
        'saveUsers': {url: '/admin/subjects/:id/save/users', method: 'POST'},
        'loseUsers': {url: '/admin/subjects/:id/lose/users', method: 'POST'}
    });
}]);