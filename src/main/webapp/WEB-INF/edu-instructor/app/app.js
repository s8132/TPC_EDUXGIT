'use strict';

angular.module('edu-instructor', [
    'ngRoute',
    'ui.router',
    'ui.bootstrap',
    'ui.bootstrap.datetimepicker',
    'ui.utils.masks',
    'cgBusy',
    'toaster',
    'edu-instructor.controllers',
    'edu-instructor.services',
    'ncy-angular-breadcrumb',
    'xeditable'
]).config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise("/instructor");

    $stateProvider.state('main', {
        /*url: '/instructor',*/
        abstract: true,
        resolve: {
            subjectResource: 'Subjects',
            subject: function (subjectResource) {
                return subjectResource.query().$promise;
            }
        }
    });

    $stateProvider.state('main.home', {
        url: '/instructor',
        views:{
            "menu@":{
                templateUrl: '/edu-instructor/app/views/menu.html',
                controller: 'MenuCtrl',
                resolve: {
                    subjects: function(subject){
                        return subject;
                    }
                }
            },
            "@": {
                templateUrl: '/edu-instructor/app/views/home.html',
                controller: 'HomeCtrl',
                resolve: {
                    subjects: function(subject){
                        return subject;
                    }
                }
            }

        }
    });

    $stateProvider.state('main.task', {
        url: '/instructor/:subjectId/task',
        views:{
            "menu@":{
                templateUrl: '/edu-instructor/app/views/menu.html',
                controller: 'MenuCtrl',
                resolve: {
                    subjects: function(subject){
                        return subject;
                    }
                }
            },
            "@":{
                templateUrl: '/edu-instructor/app/views/task.html',
                controller: 'TaskCtrl',
                resolve: {
                    tasksResource: 'Tasks',
                    tasks: function(tasksResource, $stateParams){
                        return tasksResource.getBySubject({subjectId: $stateParams.subjectId}).$promise;
                    }
                }
            }
        }

    });

    $stateProvider.state('main.task.details', {
        url: '/:taskId/details',
        views: {
            "task":{
                templateUrl: '/edu-instructor/app/views/taskDetails.html',
                controller: 'TaskDetailsCtrl',
                resolve:{
                    tasksResource: 'Tasks',
                    task: function(tasksResource, $stateParams){
                        return tasksResource.getById({taskId: $stateParams.taskId}).$promise;
                    },
                    studentResource: 'Students',
                    students: function(studentResource, $stateParams){
                        return studentResource.query({taskId: $stateParams.taskId}).$promise;
                    }
                }
            }
        }
    });

    $stateProvider.state('main.task.details.student', {
        url: '/:studentId/student',
        views: {
            "student":{
                templateUrl: '/edu-instructor/app/views/student.html',
                controller: 'StudentCtrl',
                resolve:{
                    studentResource: 'Students',
                    student: function(studentResource, $stateParams){
                        return studentResource.getOne({studentId: $stateParams.studentId}).$promise;
                    }
                }
            }
        }
    });

    $stateProvider.state('main.task.details.student.commits', {
        url: '/:user/:repo/commits',
        views: {
            "repo":{
                templateUrl: '/edu-instructor/app/views/commits.html',
                controller: 'CommitsCtrl',
                resolve:{
                    githubResource: 'GitHub',
                    commits: function(githubResource, $stateParams){
                        return githubResource.getCommits({repo: $stateParams.repo, user: $stateParams.user}).$promise;
                    }
                }
            }
        }
    });

    $stateProvider.state('main.task.details.student.branches', {
        url: '/:user/:repo/branches',
        views: {
            "repo":{
                templateUrl: '/edu-instructor/app/views/branches.html',
                controller: 'BranchesCtrl',
                resolve:{
                    githubResource: 'GitHub',
                    branches: function(githubResource, $stateParams){
                        return githubResource.getBranches({repo: $stateParams.repo, user: $stateParams.user}).$promise;
                    }
                }
            }
        }
    });

    $stateProvider.state('main.task.details.student.releases', {
        url: '/:user/:repo/releases',
        views: {
            "repo":{
                templateUrl: '/edu-instructor/app/views/releases.html',
                controller: 'ReleasesCtrl',
                resolve:{
                    githubResource: 'GitHub',
                    releases: function(githubResource, $stateParams){
                        return githubResource.getReleases({repo: $stateParams.repo, user: $stateParams.user}).$promise;
                    }
                }
            }
        }
    });

    $stateProvider.state('main.task.details.student.languages', {
        url: '/:user/:repo/languages',
        views: {
            "repo":{
                templateUrl: '/edu-instructor/app/views/languages.html',
                controller: 'LanguagesCtrl',
                resolve:{
                    githubResource: 'GitHub',
                    languages: function(githubResource, $stateParams){
                        return githubResource.getLanguages({repo: $stateParams.repo, user: $stateParams.user}).$promise;
                    }
                }
            }
        }
    });

    $stateProvider.state('main.task.details.student.issues', {
        url: '/:user/:repo/issues',
        views: {
            "repo":{
                templateUrl: '/edu-instructor/app/views/issues.html',
                controller: 'IssuesCtrl',
                resolve:{
                    githubResource: 'GitHub',
                    issues: function(githubResource, $stateParams){
                        return githubResource.getIssues({repo: $stateParams.repo, user: $stateParams.user}).$promise;
                    }
                }
            }
        }
    });

    $stateProvider.state('main.task.add', {
        url: '/add',
        views: {
            "task":{
                templateUrl: '/edu-instructor/app/views/addTask.html',
                controller: 'AddTaskCtrl'
            }
        }
    });

    $stateProvider.state('main.task.edit', {
        url: '/:taskId/edit',
        views: {
            "task":{
                templateUrl: '/edu-instructor/app/views/addTask.html',
                controller: 'EditTaskCtrl',
                resolve: {
                    tasksResource: 'Tasks',
                    task: function(tasksResource, $stateParams){
                        return tasksResource.getById({taskId: $stateParams.taskId}).$promise;
                    }
                }
            }
        }
    });



    $stateProvider.state('error', {
        url: '/admin/error',
        templateUrl: '/edu-admin/app/views/home.html',
        controller: 'ErrorCtrl'
    });

}]).run(['$rootScope', '$modal', 'editableOptions', function ($rootScope, $modal, editableOptions){
    editableOptions.theme = 'bs3';
    var modal = null;
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
        modal = $modal.open({
            templateUrl: '/edu-instructor/app/views/modal.html',
            size: 'lg',
            backdrop: 'static'
        });
    });
    $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
        if(modal!=null){
            modal.close();
        }
    });
    $rootScope.$on('$stateChangeError', function(event, toState, toParams, fromState, fromParams) {
        if(modal!=null){
            modal.close();
        }
    });
}]);