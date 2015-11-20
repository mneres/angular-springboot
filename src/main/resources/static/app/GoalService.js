(function(angular) {
  var GoalFactory = function($resource) {
    return $resource('/api/goals/:id', {
      id: '@id'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  };
  
  GoalFactory.$inject = ['$resource'];
  angular.module("myApp.services").factory("GoalFactory", GoalFactory);
}(angular));