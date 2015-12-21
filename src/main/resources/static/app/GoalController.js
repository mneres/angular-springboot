(function(angular) {	
  var GoalController = function($scope, GoalFactory, RequirementFactory) {
	GoalFactory.query(function(response) {
	  $scope.requirements = [];
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
    
    $scope.addRequirement = function(category, value) {
        new RequirementFactory({
          category: category,
          value: value
        }).$save();
        var req = {
        		 "category": category,
                 "value": value
        };
        $scope.requirements.push(req);
        $scope.newReqCategory = "";
        $scope.newReqValue = "";
      };
    
  };
  
  GoalController.$inject = ['$scope', 'GoalFactory', 'RequirementFactory'];
  angular.module("myApp.controllers").controller("GoalController", GoalController);
}(angular));