'use strict';

angular.module('edu-student', [
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
    'xeditable',
    'ui.select'
]).config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){

    /*var ws = new SockJS("/test");
    var client = Stomp.over(ws);

    client.connect({},'aa', function(frame){
        console.log(frame);
        var user = frame.headers['user-name'];
        console.log(user);

        client.subscribe("/user/"+user+"/notification", function(msg){
            console.log("mark5");
            console.log(msg);
            var msgJson = JSON.parse(msg.body);
            console.log(msgJson.title);
            console.log(msgJson.message);
            toaster.pop("info", msgJson.title, msgJson.body);
        });
    });*/

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
                templateUrl: '/edu-student/app/views/menu.html',
                controller: 'MenuCtrl',
                resolve: {
                    subjects: function(subject){
                        return subject;
                    }
                }
            },
            "@": {
                templateUrl: '/edu-student/app/views/home.html',
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
                templateUrl: '/edu-student/app/views/menu.html',
                controller: 'MenuCtrl',
                resolve: {
                    subjects: function(subject){
                        return subject;
                    }
                }
            },
            "@":{
                templateUrl: '/edu-student/app/views/task.html',
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
        url: '/:taskId',
        views:{
            "task":{
                templateUrl: '/edu-student/app/views/taskDetails.html',
                controller: 'TaskDetailsCtrl',
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
        templateUrl: '/edu-student/app/views/home.html',
        controller: 'ErrorCtrl'
    });

}]).run(['$rootScope', '$modal', 'editableOptions', 'toaster', function ($rootScope, $modal, editableOptions, toaster){
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

    var ws = new SockJS("/test");
    var client = Stomp.over(ws);

    client.connect({}, function(frame){
        var user = frame.headers['user-name'];

        client.subscribe("/user/"+user+"/notification", function(msg){
            console.log(msg);
            var msgJson = JSON.parse(msg.body);
            $rootScope.$apply(function(){
                toaster.info(msgJson.title, msgJson.message);
            });
        });
    });
}]);