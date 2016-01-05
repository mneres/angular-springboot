(function(angular) {	
	var GoalController = function($scope, $http, GoalFactory) {
		GoalFactory.query(function(response) {
			$scope.requirements = [];
			$scope.goals = response ? response : [];
		});
    
	    $scope.addGoal = function(name, category) {
	    	$http.post('/api/goals/addRequirements', $scope.requirements)
	    		.success(function(data){
	    			$scope.requirements = [];
	    			new GoalFactory({
	    		    	name: name,
	    		    	category: category
	    		    }).$save();
	    		    $scope.newGoalName = "";
	    		    $scope.newGoalCategory = "";
	    	});
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
	        var req = {
	        		 "category": category,
	                 "value": value
	        };
	        $scope.requirements.push(req);
	        $scope.newReqCategory = "";
	        $scope.newReqValue = "";
	    };
	    
	    $scope.deleteRequirement = function(req) {
	    	$scope.requirements.splice($scope.requirements.indexOf(req), 1);
	    };
    
  };
  
  GoalController.$inject = ['$scope', '$http', 'GoalFactory'];
  angular.module("myApp.controllers").controller("GoalController", GoalController);
}(angular));