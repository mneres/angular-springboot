(function(angular) {
  var GoalController = function($scope, GoalFactory) {
	GoalFactory.query(function(response) {
      $scope.goals = response ? response : [];
    });
    
    $scope.addGoal = function(name, category) {
      new GoalFactory({
        name: name,
        category: category
      }).$save();
      $scope.newGoalName = "";
      $scope.newGoalCategory = "";
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