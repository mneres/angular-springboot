(function(angular) {	
	var GoalController = function($scope, $http, $routeParams, GoalFactory) {
		GoalFactory.query(function(response) {
			$scope.requirements = [];
			$scope.actions = [];
			$scope.goals = response ? response : [];
		});

		/*
		 * Conditional to verify which parameter was activated
		 */
		if($routeParams.goalID){
			//Selection of the goal
			$scope.selectedGoal = GoalFactory.get({id: $routeParams.goalID});
		}else if($routeParams.editGoalID){
			//Enable editing of the goal
			GoalFactory.get({id: $routeParams.editGoalID}, function(editGoal){
				$scope.selectedEditGoal = editGoal;
				$scope.editGoalName = editGoal.name;
				$scope.editGoalCategory = editGoal.category;
			});
		}else{
			$scope.selectedGoal = {};
			$scope.selectedEditGoal = {};
		}

	    $scope.addGoal = function(name, category) {
	    	$http.post('/api/goals/addRequirements', $scope.requirements)
	    		.success(function(data){
	    			$http.post('/api/goals/addActions', $scope.actions)
	    			.success(function(data){
	    				$scope.requirements = [];
	    				$scope.actions = [];
		    			new GoalFactory({
		    		    	name: name,
		    		    	category: category
		    		    }).$save();
		    		    $scope.newGoalName = "";
		    		    $scope.newGoalCategory = "";
	    			});
	    	});
	    };
	    
	    $scope.updateGoal = function(goal, name, category) {
	    	goal.name = name;
	    	goal.category = category;
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
	    
	    $scope.showRequirementForm = function(){
	    	$("#openReqForm").fadeOut();
	    	$("#reqForm").fadeIn();
	    }
	    
	    $scope.hideRequirementForm = function(){
	    	$("#reqForm").fadeOut();
	    	$("#openReqForm").fadeIn();
	    }
	    
	    $scope.addAction = function(action, resource, planningDate) {
	        var act = {
	        		 "action": action,
	        		 "resource": resource,
	                 "planningDate": planningDate
	        };
	        $scope.actions.push(act);
	        $scope.newActAction = "";
	        $scope.newActResource = "";
	        $scope.newActDate = "";
	    };
	    
	    $scope.deleteAction = function(act) {
	    	$scope.actions.splice($scope.actions.indexOf(act), 1);
	    };
	    
	    $scope.showActionForm = function(){
	    	$("#openActForm").fadeOut();
	    	$("#actForm").fadeIn();
	    }
	    
	    $scope.hideActionForm = function(){
	    	$("#actForm").fadeOut();
	    	$("#openActForm").fadeIn();
	    }   
  };
  
  GoalController.$inject = ['$scope', '$http', '$routeParams', 'GoalFactory'];
  angular.module("myApp.controllers").controller("GoalController", GoalController);
}(angular));