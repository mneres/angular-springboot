(function(angular) {
  var ListFactory = function($resource) {
    return $resource('/mylists/:id', {
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
  
  ListFactory.$inject = ['$resource'];
  angular.module("myApp.services").factory("List", ListFactory);
}(angular));