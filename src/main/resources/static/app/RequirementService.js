(function(angular) {
  var RequirementFactory = function($resource) {
    return $resource('/api/goals/addRequirement');
  };
  
  RequirementFactory.$inject = ['$resource'];
  angular.module("myApp.services").factory("RequirementFactory", RequirementFactory);
}(angular));