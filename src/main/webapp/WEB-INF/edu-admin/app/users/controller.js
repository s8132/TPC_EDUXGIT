'use strict';

var usersControllers = angular.module('edu-admin.users.controllers', []);

usersControllers.controller('UserHomeCtrl', ['$scope', 'ngTableParams', '$filter', 'users', 'Users', 'toaster', function($scope, ngTableParams, $filter, users, Users, toaster){
    $scope.groupBy = null;
    $scope.users = users;
    $scope.tableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            id: 'asc'
        }
    }, {
        total: function(){
            return $scope.users.length;
        },
        getData: function($defer, params){
            var filteredData = params.filter() ? $filter('filter')($scope.users, params.filter()) : $scope.users;
            var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
            params.total(orderedData.length);
            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });

    $scope.reloadGroupBy = function(){
        if($scope.groupBy===null){
            delete $scope.tableParams.settings().groupBy;
            $scope.tableParams.reload();
        }else{
            $scope.tableParams.settings().groupBy = $scope.groupBy;
            $scope.tableParams.reload();
        }
    };

    $scope.saveUser = function(user){
        $scope.promiseLoad = Users.update(user).$promise.then(function(result){
            toaster.pop('success', 'Edytowanie użytkownika', 'Zmiany został zapisane');
            user.$edit = false;
        }, function(error){
            toaster.pop('error', 'Edytowanie użytkownika', 'Uppss coś nie tak :/');
        });
    };

    $scope.removeUser = function(user, index){
        $scope.promiseLoad = Users.delete({id: user.id}).$promise.then(function(result){
            toaster.pop('success', 'Usuwanie użytkownika', 'Zmiany został zapisane');
            $scope.users.splice(index, 1);
            $scope.tableParams.reload();
        }, function(error){
            toaster.pop('error', 'Usuwanie użytkownika', 'Uppss coś nie tak :/');
        });
    };
}]);

usersControllers.controller('UserAddCtrl', ['$scope', 'Users', 'toaster', '$state', function($scope, Users, toaster, $state){
    $scope.user = {
        enabled: true,
        authority: 'ROLE_STUDENT',
        password: ''
    };

    $scope.saveNewUser = function(){
        $scope.promiseLoad = Users.save($scope.user).$promise.then(function(result){
            $state.go('users');
            toaster.pop('success', 'Zapis nowego użytkownika', 'Nowy użytkownik został zapisany');
        }, function(error){
            $state.go('users');
            toaster.pop('error', 'Zapis nowego użytkownika', 'Wystąpił problem podczas zapisu nowego użytkownika');
        });
    };
}]);