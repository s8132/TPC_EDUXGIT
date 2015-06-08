'use strict';

var appControllers = angular.module('edu-instructor.controllers', []);

appControllers.controller('MenuCtrl', ['$scope', 'subject', function($scope, subjects){
    $scope.subjects = subjects;
}]);

appControllers.controller('HomeCtrl', ['$scope', 'subjects', function($scope, subjects){
    $scope.subjects = subjects;
}]);

appControllers.controller('TaskCtrl', ['$scope', 'tasks', '$state', function($scope, tasks, $state){
    $scope.tasks = tasks;
    $scope.subjectId = $state.params.subjectId;
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

appControllers.controller('TaskDetailsCtrl', ['$scope', 'task', 'students', 'TaskShare', function($scope, task, students, TaskShare){
    TaskShare.setTask(task);
    $scope.task = task;
    $scope.students = students;
    $scope.students.forEach(function(student){
        var atIndex = student.email.indexOf('@');
        student.s = student.email.substring(0, atIndex);
    });
}]);

appControllers.controller('StudentCtrl', ['$scope', 'student', 'TaskShare', 'Students', 'toaster', function($scope, student, TaskShare, Students, toaster){
    $scope.student = student;
    $scope.task = TaskShare.getTask();
    var pointsCache = null;

    $scope.beforeUpdatePoints = function(){
        pointsCache = $scope.student.points;
    };

    $scope.afterUpdatePoints = function(){
        Students.updatePoints($scope.student).$promise.then(function(){
            toaster.pop('success', 'Edycja punktów', 'OK');
            pointsCache = null;
        }, function(){
            toaster.pop('error', 'Edycja punktów', 'Error');
            if(pointsCache!=null){
                $scope.student.points = pointsCache;
            }else{
                $scope.student.points = 0;
            }
            pointsCache = null;
        });
    };
}]);

appControllers.controller('CommitsCtrl', ['$scope', 'commits', function($scope, commits){
    $scope.commits = commits;
}]);

appControllers.controller('BranchesCtrl', ['$scope', 'branches', function($scope, branches){
    $scope.branches = branches;
}]);

appControllers.controller('ReleasesCtrl', ['$scope', 'releases', function($scope, releases){
    $scope.releases = releases;
}]);

appControllers.controller('LanguagesCtrl', ['$scope', 'languages', function($scope, languages){
    $scope.languages = languages;
}]);

appControllers.controller('IssuesCtrl', ['$scope', 'issues', function($scope, issues){
    $scope.issues = issues;
}]);

appControllers.controller('AddTaskCtrl', ['$scope', 'Tasks', '$state', 'toaster', function($scope, Tasks, $state, toaster){
    $scope.task = {subjectId: $state.params.subjectId, startDate: new Date(), stopDate: new Date(), maxPoints: 1.0};
    $scope.edit = false;
    $scope.startDateParams = {
        minDate: new Date(),
        showWeeks: true
    };

    $scope.saveTask = function(){
        $scope.promiseLoad = Tasks.save($scope.task).$promise.then(function(){
            toaster.pop('success', 'Zapis zadania', 'Wszystko ok');
            $state.go('main.task', {subjectId: $state.params.subjectId}, {reload: true});
        }, function(){
            toaster.pop('error', 'Zapis zadania', 'Coś nie tak');
        });
    };
}]);

appControllers.controller('EditTaskCtrl', ['$scope', 'Tasks', '$state', 'toaster', 'task', function($scope, Tasks, $state, toaster, task){
    $scope.task = task;
    $scope.edit = true;
    task.stopDate = new Date(task.stopDate);
    /*$scope.startDateParams = {
        minDate: new Date(),
        showWeeks: true
    };*/

    $scope.saveTask = function(){
        $scope.promiseLoad = Tasks.save($scope.task).$promise.then(function(){
            toaster.pop('success', 'Edycja zadania', 'Wszystko ok');
            $state.go('main.task', {subjectId: $state.params.subjectId}, {reload: true});
        }, function(){
            toaster.pop('error', 'Edycja zadania', 'Coś nie tak');
        });
    };
}]);

appControllers.controller('ErrorCtrl', ['$scope', function($scope){

}]);