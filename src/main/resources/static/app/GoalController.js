(function(angular) {
  var GoalController = function($scope, GoalFactory) {
	GoalFactory.query(function(response) {
      $scope.goals = response ? response : [];
    });
    
    $scope.addGoal = function(name, description, plannedDate) {
      new GoalFactory({
        name: name,
        description: description,
        plannedDate: plannedDate
      }).$save(function(goal) {
        $scope.goals.push(goal);
      });
      $scope.newGoalName = "";
      $scope.newGoalDescription = "";
      $scope.newGoalPlannedDate = "";
    };
    
    $scope.updateGoal = function(goal) {
      goal.$update();
    };
    
    $scope.deleteGoal = function(goal) {
      goal.$remove(function() {
        $scope.goals.splice($scope.goals.indexOf(goal), 1);
      });
    };
  };
  
  GoalController.$inject = ['$scope', 'GoalFactory'];
  angular.module("myApp.controllers").controller("GoalController", GoalController);
}(angular));