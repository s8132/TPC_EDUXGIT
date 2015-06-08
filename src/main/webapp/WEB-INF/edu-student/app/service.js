'use strict';

var appService = angular.module('edu-instructor.services', ['ngResource']);

appService.factory('Subjects', ['$resource', function($resource){
    return $resource('/student/subjects', null, {});
}]);

appService.factory('Tasks', ['$resource', function($resource){
    return $resource('/instructor/tasks', null, {
        'getBySubject': {method: 'GET', url: '/student/subjects/:subjectId/tasks', isArray: true},
        'getById': {method: 'GET', url: '/student/:taskId/tasks'},
        'updateRepository': {method: 'PUT', url: '/student/:taskId/tasks'}
    });
}]);

appService.factory('GitHub', ['$resource', function($resource){
    return $resource('', null, {
        'getCommits': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/commits', isArray: true},
        'getBranches': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/branches', isArray: true},
        'getReleases': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/releases', isArray: true},
        'getLanguages': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/languages'},
        'getIssues': {method: 'GET', url: 'http://api.github.com/repos/:user/:repo/issues', isArray: true},
        'getUserRepositories': {method: 'GET', url: 'http://api.github.com/users/:username/repos', isArray: true}
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