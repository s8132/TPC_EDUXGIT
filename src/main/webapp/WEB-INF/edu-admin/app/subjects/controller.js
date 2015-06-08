'use strict';

var subjectsControllers = angular.module('edu-admin.subjects.controllers', []);

subjectsControllers.controller('SubjectHomeCtrl', ['$scope', 'subjects', 'ngTableParams', '$filter', 'Subjects', 'toaster', function($scope, subjects, ngTableParams, $filter, Subjects, toaster){
    $scope.subjects = subjects;

    $scope.tableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            id: 'asc'
        }
    }, {
        total: function(){
            return $scope.subjects.length;
        },
        getData: function($defer, params){
            var filteredData = params.filter() ? $filter('filter')($scope.subjects, params.filter()) : $scope.subjects;
            var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
            params.total(orderedData.length);
            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });

    $scope.updateSubject = function(subject){
        $scope.loadPromise = Subjects.update(subject).$promise.then(function(){
            subject.$edit = false;
            toaster.pop('success', 'Edytowanie przedmiotu', 'Wszystko ok');
        }, function(){
            toaster.pop('error', 'Edytowanie przedmiotu', 'Coś nie tak');
        });
    };

    $scope.deleteSubject = function(subject, index){
        $scope.loadPromise = Subjects.delete({id: subject.id}).$promise.then(function(){
            $scope.subjects.splice(index, 1);
            $scope.tableParams.reload();
            toaster.pop('success', 'Usuwanie przedmiotu', 'Wszystko ok');
        }, function(){
            toaster.pop('error', 'Usuwanie przedmiotu', 'Coś nie tak');
        });
    };
}]);

subjectsControllers.controller('SubjectAddCtrl', ['$scope', 'teachers', 'Subjects', '$state', 'toaster', function($scope, teachers, Subjects, $state, toaster){
    $scope.teachers = teachers;
    $scope.subject = {status: 'ACTIVE'};

    $scope.generateCode = function(){
        var matches = $scope.subject.name.match(/\b(\w)/g);
        $scope.subject.code = matches.join('').toUpperCase();
    };

    $scope.saveSubject = function(){
        $scope.promiseLoad = Subjects.save($scope.subject).$promise.then(function(result){
            $state.go('subjects');
            toaster.pop('success', 'Zapis przedmiot', 'Akcja zakończona pomyślnie');
        }, function(error){
            toaster.pop('error', 'Zapis przedmiot', 'Upppsss coś nie tak');
        });
    }
}]);

subjectsControllers.controller('SubjectAddStudentCtrl', ['$scope', 'Subjects', '$state', 'toaster', 'subject', 'Users', 'ngTableParams', '$filter', function($scope, Subjects, $state, toaster, subject, Users, ngTableParams, $filter){
    $scope.subject = subject;
    $scope.savedUser = [];
    $scope.unsavedUser = [];
    $scope.userToSave = [];
    $scope.userToLose = [];

    $scope.addUserToSave = function(user){
        var index = $scope.userToSave.indexOf(user.id);
        if(index > -1){
            $scope.userToSave.splice(index, 1);
        }else{
            $scope.userToSave.push(user.id);
        }
    };

    $scope.addUserToLose = function(user){
        var index = $scope.userToLose.indexOf(user.id);
        if(index > -1){
            $scope.userToLose.splice(index, 1);
        }else{
            $scope.userToLose.push(user.id);
        }
    };

    var fetchUsers = function(){
        $scope.fetchSavedUser = Users.getSavedStudents({subjectId: $state.params.id}).$promise.then(function(result){
            $scope.savedUser = result;
            $scope.savedTableParams.reload();
        }, function(){

        });

        $scope.fetchUnsavedUser = Users.getUnsavedStudents({subjectId: $state.params.id}).$promise.then(function(result){
            $scope.unsavedUser = result;
            $scope.unsavedTableParams.reload();
        }, function(){

        });
        $scope.userToSave = [];
        $scope.userToLose = [];
    };

    fetchUsers();

    $scope.addUsers = function(){
        $scope.fetchSavedUser = Subjects.saveUsers({id: $state.params.id}, $scope.userToSave).$promise.then(function(){
            toaster.pop('success', 'Dodawanie studentów', 'Wszystko ok');
            fetchUsers();
        }, function(){
            toaster.pop('error', 'Dodawanie studentów', 'Coś nie tak');
        });
    };

    $scope.removeUsers = function(){
        $scope.fetchUnsavedUser = Subjects.loseUsers({id: $state.params.id}, $scope.userToLose).$promise.then(function(){
            toaster.pop('success', 'Usuwanie studentów', 'Wszystko ok');
            fetchUsers();
        }, function(){
            toaster.pop('error', 'Usuwanie studentów', 'Coś nie tak');
        });
    };

    $scope.savedTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            id: 'asc'
        }
    }, {
        total: function(){
            return $scope.savedUser.length;
        },
        getData: function($defer, params){
            var filteredData = params.filter() ? $filter('filter')($scope.savedUser, params.filter()) : $scope.savedUser;
            var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
            params.total(orderedData.length);
            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });

    $scope.unsavedTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            id: 'asc'
        }
    }, {
        total: function(){
            return $scope.unsavedUser.length;
        },
        getData: function($defer, params){
            var filteredData = params.filter() ? $filter('filter')($scope.unsavedUser, params.filter()) : $scope.unsavedUser;
            var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
            params.total(orderedData.length);
            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });
}]);