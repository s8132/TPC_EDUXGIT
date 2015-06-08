'use strict';

angular.module('edu-admin', [
    'ngRoute',
    'ngAnimate',
    'edu-admin.controllers',
    'edu-admin.users.controllers',
    'edu-admin.users.services',
    'edu-admin.subjects.controllers',
    'edu-admin.subjects.services',
    'ui.router',
    'ui.bootstrap',
    'ngTable',
    'anim-in-out',
    'toggle-switch',
    'validation.match',
    'toaster',
    'cgBusy',
    'ui.select'
]).config(['$stateProvider', '$urlRouterProvider', 'uiSelectConfig', function($stateProvider, $urlRouterProvider, uiSelectConfig){
    uiSelectConfig.theme = "bootstrap";
    uiSelectConfig.resetSearchInput = true;

    $urlRouterProvider.otherwise("/home");

    $stateProvider.state('home', {
        url: '/home',
        templateUrl: '/edu-admin/app/views/home.html',
        controller: 'HomeCtrl'
    });

    /* Users */
    $stateProvider.state('users',{
        url: '/users',
        templateUrl: '/edu-admin/app/users/views/home.html',
        controller: 'UserHomeCtrl',
        resolve:{
            usersResource: "Users",
            users: function(usersResource){
                return usersResource.getAll().$promise;
            }
        }
    });

    $stateProvider.state('userAdd',{
        url: '/user/add',
        templateUrl: '/edu-admin/app/users/views/add.html',
        controller: 'UserAddCtrl'
    });

    /* Subjects */
    $stateProvider.state('subjects',{
        url: '/subjects',
        templateUrl: '/edu-admin/app/subjects/views/home.html',
        controller: 'SubjectHomeCtrl',
        resolve:{
            subjectsResource: "Subjects",
            subjects: function(subjectsResource){
                return subjectsResource.getAll().$promise;
            }
        }
    });

    $stateProvider.state('subjectAdd',{
        url: '/subject/add',
        templateUrl: '/edu-admin/app/subjects/views/add.html',
        controller: 'SubjectAddCtrl',
        resolve:{
            usersResource: "Users",
            teachers: function(usersResource){
                return usersResource.getTeachers().$promise;
            }
        }
    });

    $stateProvider.state('subjectAddStudent',{
        url: '/subject/:id/add/student',
        templateUrl: '/edu-admin/app/subjects/views/addStudent.html',
        controller: 'SubjectAddStudentCtrl',
        resolve:{
            subjectsResource: "Subjects",
            subject: function(subjectsResource, $stateParams){
                return subjectsResource.getById({id: $stateParams.id}).$promise;
            }
        }
    });

    $stateProvider.state('error', {
        url: '/admin/error',
        templateUrl: '/edu-admin/app/views/home.html',
        controller: 'ErrorCtrl'
    });
}]).run([function (){

}]);