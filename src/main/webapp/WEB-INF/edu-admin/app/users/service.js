'use strict';

var usersServices = angular.module('edu-admin.users.services', ['ngResource']);

usersServices.factory('Users', ['$resource', function($resource){
    return $resource('/admin/users', null, {
        'getAll': {method: 'GET', url: '/admin/users', isArray: true},
        'getById': {method: 'GET', url: '/admin/users/:id'},
        'save': {method: 'POST', url: '/admin/users'},
        'update': {method: 'PUT', url: '/admin/users/:id'},
        'delete': {method: 'DELETE', url: '/admin/users/:id'},
        'getTeachers': {method: 'GET', url: '/admin/teachers', isArray: true},
        'getStudents': {method: 'GET', url: '/admin/students', isArray: true},
        'getSavedStudents': {method: 'GET', url: '/admin/students/:subjectId/saved/subject', isArray: true},
        'getUnsavedStudents': {method: 'GET', url: '/admin/students/:subjectId/unsaved/subject', isArray: true}
    });
}]);