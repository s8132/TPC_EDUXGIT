'use strict';

var appService = angular.module('edu-instructor.services', ['ngResource']);

appService.factory('Subjects', ['$resource', function($resource){
    return $resource('/instructor/subjects', null, {});
}]);

appService.factory('Tasks', ['$resource', function($resource){
    return $resource('/instructor/tasks', null, {
        'getBySubject': {method: 'GET', url: '/instructor/tasks/:subjectId/subject', isArray: true},
        'save': {method: 'POST', url: '/instructor/tasks'},
        'getById': {method: 'GET', url: '/instructor/tasks/:taskId'}
    });
}]);

appService.factory('Students', ['$resource', function($resource){
    return $resource('/instructor/students/:taskId/task', null, {
        'getOne': {method: 'GET', url: '/instructor/students/:studentId'},
        'updatePoints': {method: 'PUT', url: '/instructor/students'}
    });
}]);

appService.factory('GitHub', ['$resource', function($resource){
    return $resource('', null, {
        'getCommits': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/commits', isArray: true},
        'getBranches': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/branches', isArray: true},
        'getReleases': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/releases', isArray: true},
        'getLanguages': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/languages'},
        'getIssues': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/issues', isArray: true}
    });
}]);

appService.factory('TaskShare', [function(){
    var task = null;
    return{
        setTask: function(t){
            task = t;
        },
        getTask: function(){
            return task;
        }
    }
}]);