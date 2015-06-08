'use strict';

var appControllers = angular.module('edu-instructor.controllers', []);

appControllers.controller('MenuCtrl', ['$scope', 'subject', 'toaster', function($scope, subjects, toaster){
    $scope.subjects = subjects;
   /* var ws = new SockJS("/test");
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
            $scope.$apply(function(){
                toaster.info(msgJson.title, msgJson.message);
            });

        });
    });*/
}]);

appControllers.controller('HomeCtrl', ['$scope', 'subjects', function($scope, subjects){
    $scope.subjects = subjects;
}]);

appControllers.controller('TaskCtrl', ['$scope', 'tasks', function($scope, tasks){
    $scope.tasks = tasks;
    $scope.currentDate = new Date();
    $scope.indexMin = -1;
    $scope.tasks.forEach(function(task, index){
        console.log($scope.currentDate.getTime());
        task.stopDate = new Date(task.stopDate);
        task.startDate = new Date(task.startDate);
        var min = 0;
        var diff =  $scope.currentDate.getTime() - task.stopDate.getTime();
        if(min>diff){
            min = diff;
            $scope.indexMin = index;
        }
    });
}]);

appControllers.controller('TaskDetailsCtrl', ['$scope', 'task', 'GitHub', 'toaster', 'Tasks', '$state', function($scope, task, GitHub, toaster, Tasks, $state){
    $scope.task = task;
    $scope.repositories = [];
    $scope.showChangeRepoForm = false;

    $scope.changeRepo = function(){
        getRepositories();
        $scope.showChangeRepoForm = true;
    };

    $scope.updateRepository = function(){
        $scope.loadRepo = Tasks.updateRepository({taskId: task.taskId}, $scope.task).$promise.then(function(){
            toaster.pop('success', 'Zmiana repozytorium', 'Repozytorium zmienione!');
            $scope.showChangeRepoForm = false;
        }, function(){
            toaster.pop('error', 'Zmiana repozytorium', 'Upsss!!! Coś poszło nie tak spróbuj później.');
            $state.go($state.current, {}, {reload: true});
        });
    };

    $scope.refreshRepositories = function(){
        $scope.loadRepo = GitHub.getUserRepositories({username: task.githubAccount}).$promise.then(function(repos){
            $scope.repositories = [];
            repos.forEach(function(repo){
                $scope.repositories.push(repo.name);
            });
            toaster.pop('success', 'Pobieranie listy repozytoriów', 'Repozytoria odświeżone!');
        }, function(){
            toaster.pop('error', 'Pobieranie listy repozytoriów', 'Upsss!!! Coś poszło nie tak spróbuj później.');
        })
    };

    function getRepositories(){
        $scope.loadRepo = GitHub.getUserRepositories({username: task.githubAccount}).$promise.then(function(repos){
            $scope.repositories = [];
            repos.forEach(function(repo){
                $scope.repositories.push(repo.name);
            });
        }, function(){
            toaster.pop('error', 'Pobieranie listy repozytoriów', 'Upsss!!! Coś poszło nie tak spróbuj później.');
        })
    }
}]);


appControllers.controller('ErrorCtrl', ['$scope', function($scope){

}]);