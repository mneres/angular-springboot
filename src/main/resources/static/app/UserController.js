(function(angular) {
  var UserController = function($scope, UserFactory) {
	  UserFactory.query(function(response) {
      $scope.users = response ? response : [];
    });
    
    $scope.addUser = function(email, password) {
      new UserFactory({
        email: email,
        password: password
      }).$save();
      $scope.newUserEmail = "";
      $scope.newUserPassword = "";
    };
    
    $scope.updateUser = function(user) {
      user.$update();
    };
    
    $scope.deleteUser = function(user) {
      user.$remove(function() {
        $scope.users.splice($scope.users.indexOf(user), 1);
      });
    };
  };
  
  UserController.$inject = ['$scope', 'UserFactory'];
  angular.module("myApp.controllers").controller("UserController", UserController);
}(angular));