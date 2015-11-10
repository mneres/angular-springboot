(function(angular) {
  var ListController = function($scope, List) {
    List.query(function(response) {
      $scope.lists = response ? response : [];
    });
    
    $scope.addList = function(name) {
      new List({
        name: name
      }).$save(function(list) {
        $scope.lists.push(list);
      });
      $scope.newList = "";
    };
    
    $scope.updateList = function(list) {
      list.$update();
    };
    
    $scope.deleteList = function(list) {
      list.$remove(function() {
        $scope.lists.splice($scope.lists.indexOf(list), 1);
      });
    };
  };
  
  ListController.$inject = ['$scope', 'List'];
  angular.module("myApp.controllers").controller("ListController", ListController);
}(angular));